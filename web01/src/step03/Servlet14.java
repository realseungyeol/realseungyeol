/* 게시판 만들기 : 회원들옥하기
 * =>MemberDao를 이용하여 클라이언트로 부터 받은 회원정보를 저장한다.
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

@WebServlet(urlPatterns="/step03/Servlet14")
public class Servlet14 extends GenericServlet {
  private static final long serialNerstionUID = 1L;
  
  @Override
  public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
    res.setContentType("text/html;charset=UTF-8");
    PrintWriter out = res.getWriter();
    
    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<title>회원조회</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>회원조회</h1>");
    
    String jdbcDriver = "com.mysql.jdbc.Driver";
    String jdbcUrl = "jdbc:mysql://localhost:3306/webappdb"; 
    String jdbcUsername = "webapp";
    String jdbcPassword = "1111";
    
    try {
      DBConnectionPool conPool = new DBConnectionPool(jdbcDriver, jdbcUrl, jdbcUsername, jdbcPassword);
      
      MemberDao memberDao = new MemberDao(conPool);

      
      int no = Integer.parseInt(req.getParameter("no"));
      
      Member member = memberDao.selectOne(no);
      if (member == null) {
        throw new Exception(no + "번 회원이 없아요ㅕ");
      }
      
      out.printf("<form action='Servlet05' method='POST'>\n");
      out.printf("번호 : <input type='text' name='no' value= '%d'readonly><br>\n", member.getNo());
      out.printf("이름 : <input type='text' name='name' value= '%s'><br>\n", member.getName());
      out.printf("전화 : <input type='text' name='tel'value= '%s'><br>\n", member.getTel());
      out.printf("이메일 : <input type='text' name='email'value= '%s'><br>\n", member.getEmail());
      out.println("암호 : <input type='password' name='password'><br>\n");
      out.println("<button>변경</button>");
      out.println("<button type='button' onclick='doDelete()'>삭제</button>");
      out.println("<button type='button' onclick='doList()'>목록</button>");
      out.println("</form>");
      
      
    } catch (Exception e) {
      out.println("오류바ㅣㄹ생");
      out.println("<pre>");
      e.printStackTrace(out);
      out.println("</pre>");
      out.println("<a href='Servlet02'>목록</a>");
    }
    
    out.println("<script>");
    out.println("function doDelete() { ");
    out.printf(" location.href = 'Servlet06?no=%s'\n", req.getParameter("no"));
    out.println("}");
    out.println("function doList() { ");
    out.println(" location.href = 'Servlet02'\n");
    out.println("}");
    out.println("</script>");
    
    out.println("</body>");
    out.println("</html>");
  }
}
