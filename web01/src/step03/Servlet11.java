/* 게시판 만들기 : 회원삭제
 * =>MemberDao를 이용하여 클라이언트로 부터 받은 회원정보를 삭제한다
 */
    
package step03;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

@WebServlet(urlPatterns="/step03/Servlet11")
public class Servlet11 extends GenericServlet {
  private static final long serialNerstionUID = 1L;
  
  @Override
  public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
    res.setContentType("text/html;charset=UTF-8");
    PrintWriter out = res.getWriter();
    
    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<title>강의실관리</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>강의실삭제</h1>");
    
    String jdbcDriver = "com.mysql.jdbc.Driver";
    String jdbcUrl = "jdbc:mysql://localhost:3306/webappdb"; 
    String jdbcUsername = "webapp";
    String jdbcPassword = "1111";
    
    try {
      DBConnectionPool conPool = new DBConnectionPool(jdbcDriver, jdbcUrl, jdbcUsername, jdbcPassword);
      
      CroomDao croomDao = new CroomDao(conPool);

      
      int no = Integer.parseInt(req.getParameter("no"));
      int count = croomDao.delete(no);
      if (count < 1) {
        throw new Exception(no + "찾지못함");
      }
      out.println("<p>삭제'/끝</p>");
      
      
    }catch (Exception e) {
      out.println("오류바ㅣㄹ생");
      out.println("<pre>");
      e.printStackTrace(out);
      out.println("</pre>");
    }
    out.println("<a href='Servlet07'>목록</a>");
    
    
    out.println("</body>");
    out.println("</html>");
  }
}
