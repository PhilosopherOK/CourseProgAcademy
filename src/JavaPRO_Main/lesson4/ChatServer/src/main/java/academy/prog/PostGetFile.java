package academy.prog;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.*;
import java.util.List;

public class PostGetFile extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //If I accept the parameters before accepting the file, then the file does not download.
        //I don’t know what the problem is, i suffered for 3 hours, then I made a crutch, sorry).
        //So I download it first, and then move it to the folder I need
        File file = new File("E:\\projects\\CourseProgAcademy\\src\\JavaPRO_Main\\lesson4\\ChatServer\\saveFiles\\newFile.txt");
        try (InputStream inputStream = req.getInputStream();
             OutputStream outputStream = new FileOutputStream(file)) {

            byte[] buf = new byte[10240];
            int r;
            while ((r = inputStream.read(buf)) != -1) {
                outputStream.write(buf, 0, r);
            }
            resp.setStatus(HttpServletResponse.SC_OK);
        } catch (IOException e) {
            resp.setStatus(HttpServletResponse.SC_CONFLICT);
        }

        String fileName = req.getParameter("fileName");
        String to = req.getParameter("to");
        String [] strings = fileName.split("/");
        fileName = strings[strings.length-1];
        File file2 = (new File("E:/projects/CourseProgAcademy/src/JavaPRO_Main/lesson4/ChatServer/saveFiles/" + to + "/" + fileName));
        file2.getParentFile().mkdirs();
        try (InputStream inputStream = new FileInputStream(file);
             OutputStream outputStream = new FileOutputStream(file2)) {
            byte[] buf = new byte[10240];
            int r;
            while ((r = inputStream.read(buf)) != -1) {
                outputStream.write(buf, 0, r);
            }
        }
        file.delete();
        MapFiles.putFilesInMap(to, fileName, file2.getAbsolutePath());
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String login = req.getParameter("login");
        List<String> paths = MapFiles.getFilePathForSomeTo(login);
        resp.setContentType("application/octet-stream");
        if(paths == null){
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        for(int i = paths.size()-1; i < paths.size(); i++) {
            File file = new File(paths.get(i));
            try (OutputStream outputStream = resp.getOutputStream();
                 InputStream inputStream = new FileInputStream(file)) {

                byte[] buffer = new byte[10240];
                while (true) {
                    int count = inputStream.read(buffer);
                    if (count < 0) {
                        break;
                    }
                    outputStream.write(buffer, 0, count);
                }
                resp.setContentType("application/octet-stream");
                resp.setHeader("Content-Disposition", "attachment; filename=" + paths.get(i-1));
            }
        }
        resp.setStatus(HttpServletResponse.SC_OK);
    }
}

