//22bcs13276_Anshul Gaur

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/EmployeeServlet")
public class EmployeeServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h2>Employee List</h2>");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/company", "root", "password");
            PreparedStatement ps = con.prepareStatement("SELECT * FROM employees");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                out.println("<p>ID: " + rs.getInt("id") + ", Name: " + rs.getString("name") + ", Department: " + rs.getString("department") + "</p>");
            }
            out.println("<h3>Search Employee</h3>");
            out.println("<form action='EmployeeServlet' method='post'><label>ID:</label><input type='text' name='id'><input type='submit' value='Search'></form>");
            con.close();
        } catch (Exception e) {
            out.println("Error: " + e.getMessage());
        }
        out.println("</body></html>");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String id = request.getParameter("id");
        out.println("<html><body>");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/company", "root", "password");
            PreparedStatement ps = con.prepareStatement("SELECT * FROM employees WHERE id=?");
            ps.setInt(1, Integer.parseInt(id));
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                out.println("<h2>Employee Details</h2>");
                out.println("<p>ID: " + rs.getInt("id") + ", Name: " + rs.getString("name") + ", Department: " + rs.getString("department") + "</p>");
            } else {
                out.println("<p>No employee found.</p>");
            }
            con.close();
        } catch (Exception e) {
            out.println("Error: " + e.getMessage());
        }
        out.println("</body></html>");
    }
}
