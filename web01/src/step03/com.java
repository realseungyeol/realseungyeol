/* 게시판 만들기 : 게시물 목록 출력하기
 * =>MemberDao Member  클래스 준비
 * =>MemberDao를 이용하여 
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

@WebServlet(urlPatterns="/step03/com")
public class com extends GenericServlet {
  private static final long serialNerstionUID = 1L;
  
  @Override
  public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
    res.setContentType("text/html;charset=UTF-8");
    PrintWriter out = res.getWriter();
    
    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<title>첫화면</title>");
    out.println("</head>");
    out.println("<body>");
    
    
    
    try {
      out.println("<h1>처음화면</h1>");
      out.println("<a href='Servlet02'>학생</a>");
      out.println("<a href='Servlet07'>강의실</a>");
      out.println("<a href='Servlet12'>강의</a>");
      
      out.println("<tbody>");
      
     
      
      out.println("<tbody>");
      out.println("<table>");
    }catch (Exception e) {
      out.println("오류바ㅣㄹ생");
      out.println("<pre>");
      e.printStackTrace(out);
      out.println("</pre>");
    }

    out.println("</body>");
    out.println("</html>");
  }
}
