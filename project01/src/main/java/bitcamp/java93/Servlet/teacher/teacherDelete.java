/* 게시판 만들기 : 회원삭제
 * =>MemberDao를 이용하여 클라이언트로 부터 받은 회원정보를 삭제한다
 */
    
package bitcamp.java93.Servlet.teacher;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.java93.dao.MemberDao;
import bitcamp.java93.dao.TeacherDao;
import bitcamp.java93.service.TeacherService;

@WebServlet(urlPatterns="/teacher/delete")
public class teacherDelete extends HttpServlet {
  private static final long serialNerstionUID = 1L;
  
  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    res.setContentType("text/html;charset=UTF-8");
    PrintWriter out = res.getWriter();
    
    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<title>회원관리</title>");
    RequestDispatcher rd = req.getRequestDispatcher("/style/core");
    rd.include(req, res);
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>강사삭제</h1>");
    
    
    try {
      int no = Integer.parseInt(req.getParameter("no"));
      TeacherService teacherService = (TeacherService)this.getServletContext().getAttribute("teacherService");
      
      teacherService.remove(no);
      
      out.println("<p>삭제끝</p>");
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
