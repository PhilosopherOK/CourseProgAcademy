
import java.io.IOException;

// Req -> (S -> S) -> jsp

//public class LoginServlet extends HttpServlet {
//    static final String LOGIN = "admin";
//    static final String PASS = "admin";
//    static final int AGE = 18;
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        String login = request.getParameter("login");
//        String password = request.getParameter("password");
//        int age = 0;
//        try {
//            age = Integer.parseInt(request.getParameter("age"));
//        }catch (NumberFormatException e){
//            e.printStackTrace();
//        }
//        if (LOGIN.equals(login) && PASS.equals(password) && age >= AGE) {
//            HttpSession session = request.getSession(true);
//            session.setAttribute("user_login", login);
//        }
//
//        response.sendRedirect("index.jsp");
//    }
//
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        String a = request.getParameter("a");
//        HttpSession session = request.getSession(false);
//
//        if ("exit".equals(a) && (session != null))
//            session.removeAttribute("user_login");
//
//        response.sendRedirect("index.jsp");
//    }
//}
