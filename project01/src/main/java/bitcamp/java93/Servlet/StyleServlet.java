/* 예외처리 서블릿
 * => 다른 서블릿을 실행하는 중에 예외가 발생하면 이 서블릿으로 위임할 것이다.
 *    방법?
 *    포워딩을 사용하여 위임할 것이다.
 * => 포워딩을 활용하는 한가지 예이다
 */
    
package bitcamp.java93.Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns="/style/core")
public class StyleServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;
  
  @Override
  public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    PrintWriter out = res.getWriter();
    out.println("<link rel='stylesheet' href='../css/common.css'>");
  }
}
