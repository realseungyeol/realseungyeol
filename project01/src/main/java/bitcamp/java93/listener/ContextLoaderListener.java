package bitcamp.java93.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import bitcamp.java93.dao.CroomDao;
import bitcamp.java93.dao.LectDao;
import bitcamp.java93.dao.ManagerDao;
import bitcamp.java93.dao.MemberDao;
import bitcamp.java93.dao.TeacherDao;
import bitcamp.java93.service.CroomService;
import bitcamp.java93.service.ManagerService;
import bitcamp.java93.service.MemberService;
import bitcamp.java93.service.TeacherService;
import bitcamp.java93.util.DBConnectionPool;


@WebListener
public class ContextLoaderListener implements ServletContextListener{

  @Override
  public void contextInitialized(ServletContextEvent sce) {
    String jdbcDriver = "com.mysql.jdbc.Driver";
    String jdbcUrl = "jdbc:mysql://localhost:3306/webappdb"; 
    String jdbcUsername = "webapp";
    String jdbcPassword = "1111";
    
    try {
      DBConnectionPool conPool = new DBConnectionPool(jdbcDriver, jdbcUrl, jdbcUsername, jdbcPassword);
      
      LectDao lectDao = new LectDao(conPool);
     
      
      CroomDao croomDao = new CroomDao(conPool);
      CroomService croomService = new CroomService();
      croomService.setCroomDao(croomDao);
      
      MemberDao memberDao = new MemberDao(conPool);
      MemberService memberService = new MemberService();
      memberService.setMemberDao(memberDao);
      
      ManagerDao managerDao = new ManagerDao(conPool);
      ManagerService managerService = new ManagerService();
      managerService.setMemberDao(memberDao);
      managerService.setManagerDao(managerDao);
      
      TeacherDao teacherDao = new TeacherDao(conPool);
      TeacherService teacherService = new TeacherService();
      teacherService.setMemberDao(memberDao);
      teacherService.setTeacherDao(teacherDao);
      
      ServletContext sc = sce.getServletContext();
//      sc.setAttribute("croomDao", croomDao);
//      sc.setAttribute("memberDao", memberDao);
//      sc.setAttribute("managerDao", managerDao);
      sc.setAttribute("lectDao", lectDao);
      
      sc.setAttribute("managerService", managerService);
      sc.setAttribute("teacherService", teacherService);
      sc.setAttribute("croomService", croomService);
      sc.setAttribute("memberService", memberService);
      
    } catch (Exception e) {
      e.printStackTrace();
    }
    
  }

  @Override
  public void contextDestroyed(ServletContextEvent sce) {
    // TODO Auto-generated method stub
    
  }
  
  

}
