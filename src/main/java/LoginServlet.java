import model.Car;
import model.Users;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Users> usersList=null;
        List<Car> carArrayList=null;
        try {
            usersList=DBSmanager.getUsers();
            carArrayList=DBSmanager.getCars();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        String login=request.getParameter("username");
        String password=request.getParameter("password");
        password=DBSmanager.encryptPassword(password);
        boolean sing_in = false;

        for (Users u: usersList) {
            if(login.equals(u.getUsername())&&password.equals(u.getPassword())) {
                //request.setAttribute("cars", carArrayList);
                sing_in=true;
//                Cookie c = new Cookie("isSignIn", login);
//                response.addCookie(c);
                HttpSession session = request.getSession();
                session.setAttribute("CURRENT_USER", u);
                response.sendRedirect("/home");
                //request.getRequestDispatcher("/home.jsp").forward(request, response);
                //azik=777
            }
        }
        if(!sing_in) {
            response.sendRedirect("/LoginServlet?error");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*Cookie[] cookies = request.getCookies();
        if (cookies!= null) {
            for (Cookie c : cookies) {
                if (c.getName().equals("isSignIn")){
                    Cookie cookie = new Cookie(c.getName(), "");
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                }
            }
        }*/
        request.getSession().removeAttribute("CURRENT_USER");
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}
