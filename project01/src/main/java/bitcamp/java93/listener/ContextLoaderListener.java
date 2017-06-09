package bitcamp.java93.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import bitcamp.java93.dao.CroomDao;
import bitcamp.java93.dao.LectDao;
import bitcamp.java93.dao.ManagerDao;
import bitcamp.java93.dao.MemberDao;
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
      
      MemberDao memberDao = new MemberDao(conPool);
      CroomDao croomDao = new CroomDao(conPool);
      LectDao lectDao = new LectDao(conPool);
      ManagerDao managerDao = new ManagerDao(conPool);
      
      ServletContext sc = sce.getServletContext();
      sc.setAttribute("croomDao", croomDao);
      sc.setAttribute("memberDao", memberDao);
      sc.setAttribute("lectDao", lectDao);
      sc.setAttribute("managerDao", managerDao);
      
    } catch (Exception e) {
      e.printStackTrace();
    }
    
  }

  @Override
  public void contextDestroyed(ServletContextEvent sce) {
    // TODO Auto-generated method stub
    
  }
  
  

}
