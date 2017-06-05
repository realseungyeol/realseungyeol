/*웹 어플리 케이션 컴포넌트 - 서블릿 만들기  GenericServlet 
 * => HttpServlet?
 *     - GenericServlet 클래스는 Get,Post,HEAN,PUT, DELETE 등의 요청을 구분하지않고 
 *       무조건 service()를 호출한다
 *     - HttpServlet은 클라이언트의 요청을 구분하여
 *       내부에서 미리 추가한 메서드를 호출한다.
 *       GET   --->doGet() 호출
 *       POST  --->doPost() 호출
 *       HEAD  --->doHead() 호출
 *       PUt   --->doPut() 호출
 *        
 * => 개발자 들은 HttpServlet을 상속받을 때 뭘오버라이딩 해야하는가?
 *    - 해당 서블릿에 대해 어떤 Method를 처리하느냐에 따라
 *      오버라이딩 할 메서드가 결정된다.
 *    - 예를들어, 
 *      클라이언트의 GET 요청만 처리하고 싶다면 doGet()오버라이딩하고
 *      클라이언트의 POST 요청만 처리하고 싶다면 doPosy()오버라이딩 한다
 */
package step01;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns="/ step01/Servlet03")
public class Servlet03 extends HttpServlet {
  private static final long serialNerstionUID = 1L;
  
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
   //Servlet03에 대해 GET 요청이 들어온다면 
   // 서블릿 컨테이너는 doGet 을 호출할 것이다.   //
    //
    System.out.println("Servlet.03 get");
    
  }
  
  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    // TODO Auto-generated method stub
    System.out.println("Servlet.03 post");
  }

}
