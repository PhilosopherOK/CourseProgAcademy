package academy.prog;

import jakarta.servlet.http.*;
import java.io.IOException;

// Req -> (S -> S) -> jsp

/*
дз
зробити проект, на странице задать 2 вопроса с двумя ответами на каждый
пользователь выбирает ответы
пользователь должен зало гиниться
пользователи заполняют по своему
вконце выдать статистику ответов конкретного пользователя который залогинился

 */

public class LoginServlet extends HttpServlet {
    static final String LOGIN = "admin";
    static final String PASS = "admin";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        if (LOGIN.equals(login) && PASS.equals(password)) {
            HttpSession session = request.getSession(true);
            session.setAttribute("user_login", login);
        }

        response.sendRedirect("index.jsp");
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
