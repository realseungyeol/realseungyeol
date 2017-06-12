/* 회원정보를 제거하고 로그인 페이지로 보낸다
 * 
 */

package bitcamp.java93.Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.java93.dao.MemberDao;
import bitcamp.java93.domain.Member;

@WebServlet(urlPatterns="/auth/logout")
public class LooutServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    req.getSession().invalidate();//요청한 클라이언트의 전용 HttpSession 객체를 무효화 시킨다.
    
    
   res.sendRedirect("../auth/login");
    
  }
}
