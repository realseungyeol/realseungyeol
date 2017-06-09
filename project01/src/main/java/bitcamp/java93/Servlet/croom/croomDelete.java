/* 게시판 만들기 : 회원삭제
 * =>MemberDao를 이용하여 클라이언트로 부터 받은 회원정보를 삭제한다
 */
    
package bitcamp.java93.Servlet.croom;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.java93.dao.CroomDao;

@WebServlet(urlPatterns="/croom/delete")
public class croomDelete extends HttpServlet {
  private static final long serialNerstionUID = 1L;
  
  @Override
  public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    res.setContentType("text/html;charset=UTF-8");
    PrintWriter out = res.getWriter();
    
    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<title>강의실관리</title>");
    RequestDispatcher rd = req.getRequestDispatcher("/style/core");
    rd.include(req, res);
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>강의실삭제</h1>");
    
   
    
    try {
      CroomDao croomDao = (CroomDao)this.getServletContext().getAttribute("croomDao");
      
      int no = Integer.parseInt(req.getParameter("no"));
      int count = croomDao.delete(no);
      if (count < 1) {
        throw new Exception(no + "찾지못함");
      }
      out.println("<p>삭제'/끝</p>");
      res.setHeader("Refresh", "1;url=list");
      
    }catch (Exception e) {
      req.setAttribute("error", e);
      rd = req.getRequestDispatcher("/error");
      rd.forward(req, res);
      return;
     }
     rd = req.getRequestDispatcher("/footer");
     rd.include(req, res);
    
    
    out.println("</body>");
    out.println("</html>");
  }
}
