/*웹 어플리 케이션 컴포넌트 - 서블릿 만들기  GenericServlet
    => 추상 클래스?
      - 인스턴스를 만들지 못하낟.
      - 서브 클래스에게 공통 필드나 메서드를 상속해주는 용도로 사용한다.
      - 추상 메서드를 가질수 있다.
 *  => GenericServlet?
 *     - 인스턴스를 만들지 못한다. 즉 집접 사용할 일이 업삳.
 *     - 서브 클래스에게 Servlet 인터페이스의 메서드 중에서 4개의 메서드를 물려준다.
 *        init, destroy getServletInfo , getServletConfig
 *     
 *     - 나머지 한개의 메서드 service()는 추상 메서드로 내비 둔다.
 *       이유? service()는 서브 클래스에게 구현해야할 문제이다?
 *   
 * 
 */
package step01;

import java.io.IOException;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

@WebServlet(urlPatterns="/step01/Servlet02")
public class Servlet02 extends GenericServlet {
  private static final long serialNerstionUID = 1L;
  
  @Override
  public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
    System.out.println("servlet02.service()");
    
  }


}
