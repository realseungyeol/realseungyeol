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
import bitcamp.java93.domain.Manager;

@WebServlet(urlPatterns="/lect/add")
public class lectAdd extends HttpServlet {
  private static final long serialNerstionUID = 1L;
  
  @Override
  public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    

    LectDao lectDao = (LectDao)this.getServletContext().getAttribute("lectDao");
    CroomDao croomDao = (CroomDao)this.getServletContext().getAttribute("croomDao");
    ManagerDao managerDao = (ManagerDao)this.getServletContext().getAttribute("managerDao");
    
    try {
      
    ArrayList<Croom> arrayList = (ArrayList<Croom>) croomDao.selectList(1, 30);
    
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
    
    out.println("<form action='/project01/lect/add2' method='POST'>");
    
    

    out.printf("강의실 이름 :");
    out.printf("<select name='crmno'>");
    out.printf("<option>강의실을 선택하세요!</option>");
    for (Croom cr : arrayList) {
        out.printf("<option value='%s'>%s</option>", cr.getCrmno(), cr.getName());
    }
    
    out.printf("</select><br>\n");
    out.printf("매니저 이름 :");
    
    out.printf("<select name='mrno'>");
    out.printf("<option>매니저를을 선택하세요!</option>");
    ArrayList<Manager> arrayList1 =  (ArrayList<Manager>)managerDao.selectList();
    
    for (Manager mgr : arrayList1) {
      
        out.printf("<option value='%s'>%s</option>", mgr.getMrno(), mgr.getName());
      
    }
    out.printf("</select><br>\n");
        
    
    
    out.println("제목 :<input type='text' name='titl'><br>");
    out.println("설명 :<input type='text' name='dscp'><br>");
    out.println("시작일 :<input type='date' name='sdt'><br>");
    out.println("종료일 :<input type='date' name='edt'><br>");
    out.println("수강인원 :<input type='text' name='qty'<br>");
    out.println("수업료 :<input type='text' name='pric'><br>");
    out.println("총시간 :<input type='text' name='thrs'><br>");
    out.println("<button>등록</button>");
     
    out.println("</form>");

   
    
    out.println("</body>");
    out.println("</html>");
    }catch (Exception e) {
      
    }
  }
}
