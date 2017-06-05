/*service() 파라미터 
 * 1) ServletRequest
 *   - 클라이언트 요청 정보를 다루는 도구
 * 2) ServletResponse
 *   - 클라이언트 응답 정보를 다루는 도구
 */
package step01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

@WebServlet(urlPatterns="/step01/Servlet01")
public class Servlet01 implements Servlet {
  public Servlet01() {
    System.out.println("servlet01.Servlet()");
  }
  @Override
  public void init(ServletConfig config) throws ServletException {
    System.out.println("servlet01.init()");
    
  }
 
  @Override
  public ServletConfig getServletConfig() {
    System.out.println("servlet01.getServletConfig()");
    return null;
  }

  @Override
  public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
    System.out.println("servlet01.service()");
    
    res.setContentType("text/plain;charset=UTF-8");
    PrintWriter out = res.getWriter();
    out.println("Hello, world!");
  }

  @Override
  public String getServletInfo() {
    System.out.println("servlet01.getServletInfo()");
    return null;
  }

  @Override
  public void destroy() {
    System.out.println("servlet01.destroy()");
    
  }

}
