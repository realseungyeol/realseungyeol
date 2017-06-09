/* 게시판 만들기 : 회원들옥하기
 * =>MemberDao를 이용하여 클라이언트로 부터 받은 회원정보를 저장한다.
 */
    
package bitcamp.java93.Servlet.lect;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.java93.dao.CroomDao;
import bitcamp.java93.dao.LectDao;
import bitcamp.java93.dao.ManagerDao;
import bitcamp.java93.domain.Croom;
import bitcamp.java93.domain.Lect;
import bitcamp.java93.domain.Manager;

@WebServlet(urlPatterns="/lect/detail")
public class lectDatail extends HttpServlet {
  private static final long serialNerstionUID = 1L;
  
  @Override
  public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    res.setContentType("text/html;charset=UTF-8");
    PrintWriter out = res.getWriter();
    
    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<title>강의조회</title>");
    RequestDispatcher rd = req.getRequestDispatcher("/style/core");
    rd.include(req, res);
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>강의조회</h1>");
    
    
    try {
      
      LectDao lectDao = (LectDao)this.getServletContext().getAttribute("lectDao");
      CroomDao croomDao = (CroomDao)this.getServletContext().getAttribute("croomDao");
      ManagerDao managerDao = (ManagerDao)this.getServletContext().getAttribute("managerDao");
      
      
      int no = Integer.parseInt(req.getParameter("no"));
      Lect lect = lectDao.selectOne(no);
      ArrayList<Croom> arrayList = (ArrayList<Croom>) croomDao.selectList(1, 30);
      
      
      
      
      if (lect == null) {
        throw new Exception(no + "번 강의가 없아요ㅕ");
      }
      out.printf("<form action='update' method='POST'>\n");
      
      
      out.printf("강의번호 : <input type='text' name='no' value= '%d'readonly><br>\n", lect.getNo());
      
      
      out.printf("강의실 이름 :");
      out.printf("<select name='crmno'>");
      out.printf("<option>강의실을 선택하세요!</option>");
      for (Croom cr : arrayList) {
        if (lect.getCrmno() == cr.getCrmno()) {
          out.printf("<option value='%s' selected>%s</option>", cr.getCrmno(), cr.getName());
        } else {
          out.printf("<option value='%s'>%s</option>", cr.getCrmno(), cr.getName());
        }
      }
      
      out.printf("</select><br>\n");
      out.printf("매니저 이름 :");
      
      out.printf("<select name='mrno'>");
      out.printf("<option>매니저를을 선택하세요!</option>");
      ArrayList<Manager> arrayList1 =  (ArrayList<Manager>)managerDao.selectList();
      
      for (Manager mgr : arrayList1) {
        if (lect.getMrno() == mgr.getMrno()) {
          out.printf("<option value='%s' selected>%s</option>", mgr.getMrno(), mgr.getName());
        } else {
          out.printf("<option value='%s'>%s</option>", mgr.getMrno(), mgr.getName());
        }
      }
      out.printf("</select><br>\n");
      
      
      
      
      out.printf("강의명 : <input type='text' name='titl' value= '%s'><br>\n", lect.getTitl());
      out.printf("설명 : <input type='text' name='dscp' value= '%s'><br>\n", lect.getDscp());
      out.printf("시작일 : <input type='text' name='sdt' value= '%s'><br>\n", lect.getSdt());
      out.printf("종료일 : <input type='text' name='edt' value= '%s'><br>\n", lect.getEdt());
      out.printf("수강가능인원 : <input type='text' name='qty' value= '%d'><br>\n", lect.getQty());
      out.printf("수업료 : <input type='text' name='pric' value= '%d'><br>\n", lect.getPric());
      out.printf("총시간 : <input type='text' name='thrs' value= '%d'><br>\n", lect.getThrs());
      
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
