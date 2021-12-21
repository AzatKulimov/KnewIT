import model.Users;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.registry.infomodel.User;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*String user_data = null;
        Cookie[] cookies = request.getCookies();
        if (cookies!= null){
            for (Cookie c : cookies){
                if (c.getName().equals("isSignIn")){
                    user_data = c.getValue();
                    break;
                }
            }
        }*/
        try {
//            for (Users u: DBSmanager.getUsers()){
//                if (u.getUsername().equals(user_data)){
//                    request.setAttribute("user", u);
                    request.setAttribute("cars", DBSmanager.getCars());
                //}
            //}
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        request.getRequestDispatcher("home.jsp").forward(request, response);

    }
}
