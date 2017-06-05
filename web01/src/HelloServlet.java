

import java.io.IOException; 
import java.io.PrintWriter;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
 


/*
 * 
 * 
 * 
 * 
 * 
 * 
 * 서블릿 생명주기 메서드들 
 * init()
 *  - 딱한번만 호출된다
 *  - 서블릿 컨테이너가 서블릴 객체를 생성한 후 호출한다.
 *  - 서블릿이 작업하는데 필요한 자원을 준비시키는 코드를 넣는다.
 * destroy()
 *  - 딱한번만 호출된다
 *  - 웹 애플리케이션이 실행이 멈출 때,
 *    서블릿이 사용한 자원을 초기화 시킬수 있도록 이 메서드를 호출한다.
 *    서블릿이 사용한 자원을 초기화 시키는 코드를 넣는다.
 * service()
 *  - 해당 서블릿에 대한 요청이 들어올떄 마다 호출된다.
 *  - 클라이언트가 요청을 처리하는 코드를 넣는다.
 *  
 * 웹 애플리케이션이 사용하는 라이브러리의 배치
 * => 웹 애플리케이션이 참조하는 라이브러리는
 *   $temp/wtpwebapps/ web01/WEB_INF/lib/폴더에 복사된다.
 * => 즉 build.gradle의 dependencies {} 블록에 지정된 라이브러리 파일들이 
 *    /lib/폴더에 복사된다는 것이다
 * => 주의!
 *    - servlet-api 라이브 러리는 당연히 서블릿 컨테이너에 포함되어 있다.
 *    - 따라서 배치할 필요가 없다./
 *    - 단지 서블릿을 만들 때 컴파일 단계에서만 사용하면 된다/.
 *    - 그럼, 배치에서 제외하는 방법이 있는가?
 *    dependencies {} 블록에 라이브러리 정보를 등록할 때,
 *    "compile 명령 대신 prividedCompile 명령 사용한다
 *    
 *  javax.servlet.Servlet 인터페이스
 *  => 서블릿 컨테이너가 클라이언트 요청을 처리하기 위해
 *     객체에 대해 호출하는 메서드으 ㅣ규칙!
 *  => 클라이언트 요청을 처리하는 클래스를 만들 때는
 *     반드시 이 규칙에 따라 만들어야 한다.
 *  => 이 규칙에 따라 만든 클래스를 서블릿이라 부른다.
 */
public class HelloServlet implements Servlet {

  public HelloServlet() {
    System.out.println("HelloServlet()");
  }
  
  @Override
  public void init(ServletConfig config) throws ServletException {
    System.out.println("init()");
    
  }

  @Override
  public ServletConfig getServletConfig() {
    System.out.println("getServletConfig()");
    return null;
  }

  @Override
  public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
    System.out.println("service()");
    
    res.setContentType("text/plain;charset=UTF-8");
    PrintWriter out = res.getWriter();
    out.println("Hello, world!");
  }

  @Override
  public String getServletInfo() {
    System.out.println("getServletInfo()");
    return null;
  }

  @Override
  public void destroy() {
    System.out.println("destroy()");
    
  }

}
