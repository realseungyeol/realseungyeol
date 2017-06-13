/* 게시판 만들기 : 회원들옥하기
 * =>MemberDao를 이용하여 클라이언트로 부터 받은 회원정보를 저장한다.
 */
    
package bitcamp.java93.Servlet.manager;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.java93.domain.Manager;
import bitcamp.java93.service.ManagerService;

@WebServlet(urlPatterns="/manager/add")
public class managerAdd extends HttpServlet {
  private static final long serialNerstionUID = 1L;
  
  @Override
  public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    Manager mr = new Manager();
   
    mr.setName(req.getParameter("name"));
    mr.setTel(req.getParameter("tel"));
    mr.setEmail(req.getParameter("email"));
    mr.setPassword(req.getParameter("password"));
    mr.setPosi(req.getParameter("posi"));
    mr.setPath(req.getParameter("path"));
    mr.setFax(req.getParameter("fax"));
    
    
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
    out.println("<h1>매니저등록</h1>");
    
    
    try {
      ManagerService managerService = (ManagerService)this.getServletContext().getAttribute("managerService");
      
      managerService.add(mr);
      
      out.println("<p>메뉴등록완료</p>");
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
