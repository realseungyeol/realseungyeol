package bitcamp.java93.Servlet;

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

@WebServlet(urlPatterns="/auth/login")
public class LoginServlet extends HttpServlet {
  private static final long serialNerstionUID = 1L;
  
  @Override
  public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    String email = req.getParameter("email");
    String password = req.getParameter("password");
    
    
    
    try {
      

      
      res.setContentType("text/html;charset=UTF-8");
      PrintWriter out = res.getWriter();
      
      out.println("<!DOCTYPE html>");
      out.println("<html>");
      out.println("<head>");
      out.println("<meta charset='UTF-8'>");
      out.println("<title>로그인</title>");
      RequestDispatcher rd = req.getRequestDispatcher("/style/core");
      rd.include(req, res);
      out.println("</head>");
      out.println("<body>");
      
      MemberDao memberDao = (MemberDao)this.getServletContext().getAttribute("memberDao");
      Member member = memberDao.selectOneByEmailPassword(email, password);
      if (member != null) {
//        res.setHeader("Refresh", "5;url=../member/list");
        
        long sessionId = System.currentTimeMillis();
        this.getServletContext().setAttribute("id_" + sessionId, member);
        
        out.println("<h1>대성공</h1>");
        out.println("<p>다음 번호를 잘 보관해 두었다가 서버에 요청할 떄마다"); 
        out.println("   sessionId 라는 이름으로 파라미터를 추가해 보내기 바랍니다</p>");
        out.printf("<p>%d</p>", sessionId);
        out.println("<p>맘껏 돌아다녀요</p>");
        return;
      } else {
        res.setHeader("Refresh", "2;url=login.html");
        out.println("<h1>로그인 5류</h1>");
        out.println("<p>틀렸어</p>");
      }
      rd = req.getRequestDispatcher("/footer");
      rd.include(req, res);
      
      out.println("</html>");
      out.println("</body>");
      
      
    } catch (Exception e) {
      req.setAttribute("error", e);
      RequestDispatcher rd = req.getRequestDispatcher("/error");
      rd.forward(req, res);
      return;
    }
    
   
    
  }
}
