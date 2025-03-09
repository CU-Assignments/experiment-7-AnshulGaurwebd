//22bcs13276_Anshul Gaur

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/AttendanceServlet")
public class AttendanceServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String studentId = request.getParameter("studentId");
        String studentName = request.getParameter("studentName");
        String attendance = request.getParameter("attendance");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/school", "root", "password");
            PreparedStatement ps = con.prepareStatement("INSERT INTO attendance (student_id, student_name, status) VALUES (?, ?, ?)");
            ps.setString(1, studentId);
            ps.setString(2, studentName);
            ps.setString(3, attendance);
            ps.executeUpdate();
            out.println("<html><body><h2>Attendance Recorded</h2></body></html>");
            con.close();
        } catch (Exception e) {
            out.println("Error: " + e.getMessage());
        }
    }
}
