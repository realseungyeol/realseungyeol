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

@WebServlet(urlPatterns="/lect/update")
public class lectUpdate extends HttpServlet {
  private static final long serialNerstionUID = 1L;
  
  @Override
  public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    req.setCharacterEncoding("UTF-8");
    Lect l = new Lect();
   
    l.setNo(Integer.parseInt(req.getParameter("no")));
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
    out.println("<title>강의관리</title>");
    RequestDispatcher rd = req.getRequestDispatcher("/style/core");
    rd.include(req, res);    
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>강의등록</h1>");
    
    try {
      LectDao lectDao = (LectDao)this.getServletContext().getAttribute("lectDao");
      
      int count = lectDao.update(l);
      if (count < 1) {
        throw new Exception(l.getNo() + "찾지못함");
      }
      out.println("<p>변경끝</p>");
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
