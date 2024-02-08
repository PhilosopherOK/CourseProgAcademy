package hw.example;

import hw.example.models.Client;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class QuestionsServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String answer1 = req.getParameter("answer1");
        String answer2 = req.getParameter("answer2");
        HttpSession session = req.getSession(true);
        Client client = (Client) session.getAttribute("client");
        if (answer1.equals("banana")) {
            client.addedBanana();
        } else {
            client.addedOrange();
        }
        if(answer2.equals("sleep")){
            client.addedSleep();
        }else {
            client.addedRun();
        }

        resp.sendRedirect("index.jsp");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }
}
