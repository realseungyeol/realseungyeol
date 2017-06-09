/* 게시판 만들기 : 회원들옥하기
 * =>MemberDao를 이용하여 클라이언트로 부터 받은 회원정보를 저장한다.
 */
    
package bitcamp.java93.Servlet.lect;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.java93.dao.LectDao;
import bitcamp.java93.domain.Lect;

@WebServlet(urlPatterns="/lect/add2")
public class lectAdd2 extends HttpServlet {
  private static final long serialNerstionUID = 1L;
  
  @Override
  public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    req.setCharacterEncoding("UTF-8");
    Lect l = new Lect();

    l.setCrmno(Integer.parseInt(req.getParameter("crmno")));
    l.setMrno(Integer.parseInt(req.getParameter("mrno")));
    l.setTitl(req.getParameter("titl"));
    l.setDscp(req.getParameter("dscp"));
    l.setSdt(req.getParameter("sdt"));
    l.setEdt(req.getParameter("edt"));
    l.setQty(Integer.parseInt(req.getParameter("qty")));
    l.setPric(Integer.parseInt(req.getParameter("pric")));
    l.setThrs(Integer.parseInt(req.getParameter("thrs")));
    
    res.setContentType("text/html;charset=UTF-8");
    PrintWriter out = res.getWriter();
    
    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<title>회원관리</title>");
    RequestDispatcher rd = req.getRequestDispatcher("/style/core");
    rd.include(req, res);
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>강의등록</h1>");
    
    out.println("<form action='/project01/lect/add' method='POST'>");
    out.println("강의이름 :<input type='text' name='no'><br>");
    out.println("강의실일련번호 :<input type='text' name='crmno'><br>");
    out.println("매니저일련번호 :<input type='text' name='mrno'><br>");
    out.println("제목 :<input type='text' name='titl'><br>");
    out.println("설명 :<input type='text' name='dscp'><br>");
    out.println("시작일 :<input type='text' name='sdt'><br>");
    out.println("종료일 :<input type='text' name='edt'><br>");
    out.println("수강인원 :<input type='text' name='qty'<br>");
    out.println("수업료 :<input type='text' name='pric'><br>");
    out.println("총시간 :<input type='text' name='thrs'><br>");
    out.println("<button>등록</button>");
    out.println("<button  type='button'  onclick='location.href='list'>뒤로가기</button>");
     
    out.println("</form>");

    try {
      LectDao lectDao = (LectDao)this.getServletContext().getAttribute("lectDao");
      
      lectDao.insert(l);
      out.println("<p>메뉴등록완료</p>");
      res.setHeader("Refresh", "1;url=list");
      
    }catch (Exception e) {
      req.setAttribute("error", e);
      rd = req.getRequestDispatcher("/error");
      rd.forward(req, res);
      return;
     }
     rd = req.getRequestDispatcher("/footer");
     rd.include(req, res);
    
    out.println("</body>");
    out.println("</html>");
  }
}
