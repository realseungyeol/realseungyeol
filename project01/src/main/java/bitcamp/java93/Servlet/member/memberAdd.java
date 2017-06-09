/* 게시판 만들기 : 회원들옥하기
 * =>MemberDao를 이용하여 클라이언트로 부터 받은 회원정보를 저장한다.
 */
    
package bitcamp.java93.Servlet.member;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.java93.dao.MemberDao;
import bitcamp.java93.domain.Member;
import bitcamp.java93.util.DBConnectionPool;

@WebServlet(urlPatterns="/member/add")
public class memberAdd extends HttpServlet {
  private static final long serialNerstionUID = 1L;
  
  @Override
  public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    Member m = new Member();
   
    m.setName(req.getParameter("name"));
    m.setTel(req.getParameter("tel"));
    m.setEmail(req.getParameter("email"));
    m.setPassword(req.getParameter("password"));
    
    
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
    out.println("<h1>메뉴등록</h1>");
    
    
    try {
      MemberDao memberDao = (MemberDao)this.getServletContext().getAttribute("memberDao");
      
      memberDao.insert(m);
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
