package academy.prog;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;

import javax.print.MultiDocPrintService;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

/*
    POST(json) -> /add -> AddServlet -> MessageList
    GET -> /get?from=x -> GetListServlet <- MessageList
        <- json[...]
 */

public class AddServlet extends HttpServlet {
    private MessageList msgList = MessageList.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        byte[] buf = requestBodyToArray(req); // json
        String bufStr = new String(buf, StandardCharsets.UTF_8);

        Message msg = Message.fromJSON(bufStr);
        if(msg.getTo() != null && ClientSet.checkForClientExist(msg.getTo())){
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        if (msg != null)
            msgList.add(msg);
        else
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST); // 400

    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (OutputStream outputStream = resp.getOutputStream()) {
            outputStream.write(ClientSet.getClientSetLikeAStr().getBytes());
        }
    }

    private byte[] requestBodyToArray(HttpServletRequest req) throws IOException {
        InputStream is = req.getInputStream();
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buf = new byte[10240];
        int r;

        do {
            r = is.read(buf);
            if (r > 0) bos.write(buf, 0, r);
        } while (r != -1);

        return bos.toByteArray();
    }
}
