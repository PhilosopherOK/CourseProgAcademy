package hw.example;

import hw.example.models.Client;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

/*
дз
сделать проект в котором:
пользователь должен зало гиниться ->
на странице задаются 2 вопроса с двумя ответами на каждый ->
пользователь выбирает ответы ->
вконце выдать статистику ответов конкретного пользователя который залогинился.
 */

public class LoginServlet extends HttpServlet {
    List<Client> clientList = List.of(new Client("log1", "pass"),
            new Client("log2", "pass"), new Client("log3", "pass"));

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        boolean checkClient = false;
        for (Client client : clientList) {
            if (client.getLogin().equals(login) && client.getPass().equals(password)) {
                HttpSession session = request.getSession(true);
                System.out.println(client);
                session.setAttribute("user_login", login);
                session.setAttribute("client", client);
                checkClient = true;
                break;
            }
        }
        if (checkClient) {
            response.sendRedirect("questions.html");
        } else {
            response.sendRedirect("index.jsp");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String a = request.getParameter("a");
        HttpSession session = request.getSession(false);

        if ("exit".equals(a) && (session != null))
            session.removeAttribute("user_login");

        response.sendRedirect("index.jsp");
    }
}