/* 게시판 만들기 : 회원들옥하기
 * =>MemberDao를 이용하여 클라이언트로 부터 받은 회원정보를 저장한다.
 */
    
package bitcamp.java93.Servlet.croom;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.GenericServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

import bitcamp.java93.dao.CroomDao;
import bitcamp.java93.domain.Croom;
import bitcamp.java93.service.CroomService;

@WebServlet(urlPatterns="/croom/detail")
public class croomDetail extends GenericServlet {
  private static final long serialNerstionUID = 1L;
  
  @Override
  public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
    res.setContentType("text/html;charset=UTF-8");
    PrintWriter out = res.getWriter();
    
    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<title>강의실조회</title>");
    RequestDispatcher rd = req.getRequestDispatcher("/style/core");
    rd.include(req, res);
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>강의실조회</h1>");
    
   
    
    try {
      CroomService croomService = (CroomService)this.getServletContext().getAttribute("croomService");

      
      int no = Integer.parseInt(req.getParameter("no"));
      Croom croom = croomService.selectOne(no);
      
      if (croom == null) {
        throw new Exception(no + "번 회원이 없아요ㅕ");
      }
      
      out.printf("<form action='update' method='POST'>\n");
      out.printf("번호 : <input type='text' name='no' value= '%d'readonly><br>\n", croom.getCrmno());
      out.printf("이름 : <input type='text' name='name' value= '%s'><br>\n", croom.getName());
      out.println("<button>변경</button>");
      out.println("<button type='button' onclick='doDelete()'>삭제</button>");
      out.println("<button type='button' onclick='doList()'>목록</button>");
      out.println("</form>");
      
      
    } catch (Exception e) {
      req.setAttribute("error", e);
      rd = req.getRequestDispatcher("/error");
      rd.forward(req, res);
      return;
    }
    
    out.println("<script>");
    out.println("function doDelete() { ");
    out.printf(" location.href = 'delete?no=%s'\n", req.getParameter("no"));
    out.println("}");
    out.println("function doList() { ");
    out.println(" location.href = 'list'\n");
    out.println("}");
    out.println("</script>");
    rd = req.getRequestDispatcher("/footer");
    rd.include(req, res);
    
    
    
    
    out.println("</body>");
    out.println("</html>");
  }
}
