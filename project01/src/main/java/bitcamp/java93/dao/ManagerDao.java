/* 역할 : 
 * 
 */
package bitcamp.java93.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bitcamp.java93.domain.Manager;
import bitcamp.java93.util.DBConnectionPool;

public class ManagerDao {
  DBConnectionPool conPool;
  
  public ManagerDao(DBConnectionPool conPool) {
    this.conPool = conPool;
  }
  
  public List<Manager> selectList() throws Exception {
    Connection con  = conPool.getConnection();
    try ( 
      PreparedStatement stmt = con.prepareStatement(
          "select mr.mrno, m.name from memb m inner join mgr mr on m.mno = mr.mrno");) {
      
      

      ArrayList<Manager> list = new ArrayList<>();
      
      try (ResultSet rs = stmt.executeQuery();) {
        Manager manager = null;
        while (rs.next()) { 
          manager = new Manager();
          manager.setMrno(rs.getInt("mrno"));
          manager.setName(rs.getString("name"));
          
          list.add(manager);
        }
      }
        return list;
        
        
    }finally {
      conPool.returnConnection(con);
    }
  }
  


  

    

}