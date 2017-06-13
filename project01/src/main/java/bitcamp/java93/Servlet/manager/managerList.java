/* 게시판 만들기 : 게시물 목록 출력하기
 * =>MemberDao Member  클래스 준비
 * =>MemberDao를 이용하여 
 */
    
package bitcamp.java93.Servlet.manager;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.java93.domain.Manager;
import bitcamp.java93.service.ManagerService;

@WebServlet(urlPatterns="/manager/list")
public class managerList extends HttpServlet {
  private static final long serialNerstionUID = 1L;
  
  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    res.setContentType("text/html;charset=UTF-8");
    PrintWriter out = res.getWriter();
    
    
    int pageNo = 1;
    int pageSize = 5;
    
    try {
      pageNo = Integer.parseInt(req.getParameter("pageNo"));
    } catch (Exception e) {}
    
    try {
      pageSize = Integer.parseInt(req.getParameter("pageSize"));
    } catch (Exception e) {}
    
    
    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<title>회원관리</title>");
    RequestDispatcher rd = req.getRequestDispatcher("/style/core");
    rd.include(req, res);
    out.println("</head>");
    out.println("<body>");
    
    rd = req.getRequestDispatcher("/header");
    rd.include(req, res);
    
    out.println("<h1>매니저목록</h1>");
    
    
    
    try {
      ManagerService managerService = (ManagerService)this.getServletContext().getAttribute("managerService");
      List<Manager> list = managerService.list(pageNo, pageSize);
      
      out.println("<a href='form.html'>신강사</a>");
      out.println("<a href='/project01/com'>처음으로</a>");
      
      out.println("<table border='1'>");
      out.println("<thead>");
      out.println("<tr><th>번호</th><th>이름</th><th>전화</th><th>이메일</th><th>직급</th><th>사진경로</th><th>펙스</th></tr>");
      out.println("</thead>");
      out.println("<tbody>");
      
      for (Manager mr : list) {
        out.println("<tr>");
        out.printf("<td>%d</td>\n", mr.getNo());
        out.printf("<td><a href='detail?no=%d'>%s</a></td>", mr.getNo() ,mr.getName());
        out.printf("<td>%s</td>\n", mr.getTel());
        out.printf("<td>%s</td>\n", mr.getEmail());
        out.printf("<td>%s</td>\n", mr.getPosi());
        out.printf("<td>%s</td>\n", mr.getPath());
        out.printf("<td>%d</td>\n", mr.getFax());
        out.println("</tr>");
      }
      
      out.println("<tbody>");
      out.println("<table>");
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
