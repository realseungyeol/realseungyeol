/* 게시판 만들기 : 회원들옥하기
 * =>MemberDao를 이용하여 클라이언트로 부터 받은 회원정보를 저장한다.
 */
    
package bitcamp.java93.Servlet.teacher;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.java93.domain.Teacher;
import bitcamp.java93.service.TeacherService;

@WebServlet(urlPatterns="/teacher/detail")
public class teacherDatail extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    res.setContentType("text/html;charset=UTF-8");
    PrintWriter out = res.getWriter();
    
 
    
    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<title>회원조회</title>");
    RequestDispatcher rd = req.getRequestDispatcher("/style/core");
    rd.include(req, res);
    out.println("</head>");
    out.println("<body>");
    rd = req.getRequestDispatcher("/header");
    rd.include(req, res);
    out.println("<h1>강사조회</h1>");
    
    
    try {
      
    TeacherService teacherService = (TeacherService)this.getServletContext().getAttribute("teacherService");

      
      int no = Integer.parseInt(req.getParameter("no"));
      
      Teacher teacher = teacherService.get(no);
      if (teacher == null) {
        throw new Exception(no + "번 회원이 없아요ㅕ");
      }
      out.printf("<form action='update' method='POST'>\n");
      out.printf("번호 : <input type='text' name='no' value= '%d'readonly><br>\n", teacher.getNo());
      out.printf("이름 : <input type='text' name='name' value= '%s'><br>\n", teacher.getName());
      out.printf("전화 : <input type='text' name='tel'value= '%s'><br>\n", teacher.getTel());
      out.printf("이메일 : <input type='text' name='email'value= '%s'><br>\n", teacher.getEmail());
      out.println("암호 : <input type='password' name='password'><br>\n");
      out.printf("홈페이지 : <input type='text' name='homepage'value= '%s'><br>\n", teacher.getHomepage());
      out.printf("페이스북 : <input type='text' name='facebook'value= '%s'><br>\n", teacher.getFacebook());
      out.printf("트위터 : <input type='text' name='twiter'value= '%s'><br>\n", teacher.getTwiter());
      out.println("<button>변경</button>");
      out.println("<button type='button' onclick='doDelete()'>삭제</button>");
      out.println("<button type='button' onclick='doList()'>목록</button>");
      out.println("</form>");
      
      
    } catch (Exception e) {
      req.setAttribute("error", e);
      rd = req.getRequestDispatcher("/error");
      rd.forward(req, res);
      return;
    }
    
    out.println("<script>");
    out.println("function doDelete() { ");
    out.printf(" location.href = 'delete?no=%s'\n", req.getParameter("no"));
    out.println("}");
    out.println("function doList() { ");
    out.println(" location.href = 'list'\n");
    out.println("}");
    out.println("</script>");
    rd = req.getRequestDispatcher("/footer");
    rd.include(req, res);
    
    
    out.println("</body>");
    out.println("</html>");
  }
}
