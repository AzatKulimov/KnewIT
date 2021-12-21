import model.Car;
import model.Users;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/EditServlet")
public class EditServlet extends HttpServlet {
    private  int id;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        id=Integer.parseInt(request.getParameter("id"));
        Car c=null;
        try {
             c=DBSmanager.getCar(id) ;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        request.setAttribute("car", c);

        String user_data = null;
        Cookie[] cookies = request.getCookies();
        if (cookies!= null){
            for (Cookie cookie : cookies){
                if (cookie.getName().equals("isSignIn")){
                    user_data = cookie.getValue();
                    break;
                }
            }
        }
        try {
            for (Users u: DBSmanager.getUsers()){
                if (u.getUsername().equals(user_data)){
                    request.setAttribute("user", u);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        request.getRequestDispatcher("/Edit.jsp").forward(request,response);
    }

}
