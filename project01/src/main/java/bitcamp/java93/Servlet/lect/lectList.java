/* 게시판 만들기 : 게시물 목록 출력하기
 * =>MemberDao Member  클래스 준비
 * =>MemberDao를 이용하여 
 */
    
package bitcamp.java93.Servlet.lect;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.GenericServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

import bitcamp.java93.dao.LectDao;
import bitcamp.java93.domain.Lect;
import bitcamp.java93.domain.Member;

@WebServlet(urlPatterns="/lect/list")
public class lectList extends GenericServlet {
  private static final long serialNerstionUID = 1L;
  
  @Override
  public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
    res.setContentType("text/html;charset=UTF-8");
    PrintWriter out = res.getWriter();
    
    int pageNo = 1;
    int pageSize = 5;
    
    try {
      pageNo = Integer.parseInt(req.getParameter("pageNo"));
    } catch (Exception e) {}
    
    try {
      pageSize = Integer.parseInt(req.getParameter("pageSize"));
    } catch (Exception e) {}
    
    
    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<title>강의관리</title>");
    RequestDispatcher rd = req.getRequestDispatcher("/style/core");
    rd.include(req, res);
    out.println("</head>");
    out.println("<body>");
    
    
    
    try {
      LectDao lectDao = (LectDao)this.getServletContext().getAttribute("lectDao");
      List<Lect> list = lectDao.selectList(pageNo, pageSize);
      
      out.println("<a href='add'>강의개설</a>");
      out.println("<a href='/project01/com'>처음으로</a>");
      
      out.println("<table border='1'>");
      out.println("<thead>");
      out.println("<tr><th>강의번호</th><th>강의실일련번호</th><th>매니저일련번호</th><th>강의명</th><th>설명</th><th>시작일</th><th>종료일</th><th>수강가능인원</th><th>수업료</th><th>총시간</th></tr>");
      out.println("</thead>");
      out.println("<tbody>");
      
      
      for (Lect l : list) {
        out.println("<tr>");
        out.printf("<td>%d</td>\n", l.getNo());
        out.printf("<td>%d</td>\n", l.getCrmno());
        out.printf("<td>%d</td>\n", l.getMrno());
        out.printf("<td><a href='detail?no=%d'>%s</a></td>", l.getNo() ,l.getTitl());
        out.printf("<td>%s</td>\n", l.getDscp());
        out.printf("<td>%s</td>\n", l.getSdt());
        out.printf("<td>%s</td>\n", l.getEdt());
        out.printf("<td>%d</td>\n", l.getQty());
        out.printf("<td>%d</td>\n", l.getPric());
        out.printf("<td>%d</td>\n", l.getThrs());
        out.println("</tr>");
      }
      out.println("<tbody>");
      out.println("<table>");
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
