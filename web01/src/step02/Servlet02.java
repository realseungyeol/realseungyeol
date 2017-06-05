/* service() 파라미터 : ServletRequest II -  값 전달하고 반기 
 * => GET요청으로 파라미터 값 전달하기
 *    URL에 붙여 전달한다.
 *    http://localhost:8080/web01/step/Servlet02?파라미터명=값&파라미터명=값
 * => POST요청으로 파라미터 값 전달하기
 *    message-body에 붙인다
 *    예)
 *    POST /step02/Servlet02 HTTP1.1
 *    Host: ...
 *    User-Agent : ...
 *    
 *    파라미터명 = 값 & 파라미터명 = 값
 *    
 *    
 *  => 테스트
 *    - http://localhost8080/web01/step02/Servlet02.html
 */

package step02;

import java.io.IOException;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

@WebServlet(urlPatterns="/step02/Servlet02")
public class Servlet02 extends GenericServlet {
  private static final long serialNerstionUID = 1L;
  
  @Override
  public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
    System.out.printf("parameter (name): %s\n", req.getParameter("name"));
    System.out.printf("parameter (age): %s\n", req.getParameter("age"));
  }


}
