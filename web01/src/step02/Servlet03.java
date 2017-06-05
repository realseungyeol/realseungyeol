/* service() 파라미터 : ServletRequest II -  값 전달하고 반기  
 * => GET요청으로 파라미터 값 전달하기
 *     톰캣
 *     <Connector connectionTimeout="20000" 
             *    port="8080" 
             *    protocol="HTTP/1.1" 
             *    redirectPort="8443"
             *    URIEncoding=UTF-8"/>
             *    URIEncoding 속성을 추가한다
 *    즉 GET 요청의 한글 값이 깨지는 경우는 프로그램 코드로 처리할 수 없다.
 *    위와 같이 설정으로 처리해야 한다.
 *      
 * => POST요청으로 파라미터 값 이 깨지는경우
 *    - 프로그램 코ㅡ를 처리할 수 있다.
 *    - 파라미터 값을 꺼내기 전에, 즉getParameter()를 최초로 호출하기 전에
 *      클라이언트의 값이 어떤 문자집합으로 인코딩 되었는지 알려준 후에 꺼낸다.
 *      예) req.setCharacherEncoding("UTF-8");
 *      
 *    - 깨지는 오류?
 *      " 서블릿 컨테이너"는 클라이언트가 보내는 값이 ISO - 8859-1"로 인코딩되어 있을거라 가정한다.
 *       그 가정을 바탕으로 자바에서 사용할 수 있도록" 유니코드"로 변환한다.
 *       예) 클라이언트가 "ABC가각간" 데이터를 보낼떄.
 *            => 실제 보내는 바이트. 41 42 43 EA B0 80 EA B0 81 EA B0 84
 *            서버에서 이 데이터를 유니코드로 바꾸면
 *            => 유니코드로 바꾼 바이트.(각 바이트에 00을 붙여서 2바이트 유니코드로 만든다.) 
 *            41 42 43 00 EA 00 B0 00  EA 00 B0 00 
 *            = > 제대로 유니코드로 바꾼다면 다음과 같아야 한다,(UTF-8 --> Unicode)
 *            00 41 0042 0043 AC00 AC 81 AC 84
 *            
 *            
 *  => 테스트
 *    - http://localhost8080/web01/step02/Servlet03.html
 */
    
package step02;

import java.io.IOException;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

@WebServlet(urlPatterns="/step02/Servlet03")
public class Servlet03 extends GenericServlet {
  private static final long serialNerstionUID = 1L;
  
  @Override
  public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
    req.setCharacterEncoding("UTF-8");
    System.out.printf("parameter (name): %s\n", req.getParameter("name"));
    System.out.printf("parameter (age): %s\n", req.getParameter("age"));
  }


}
