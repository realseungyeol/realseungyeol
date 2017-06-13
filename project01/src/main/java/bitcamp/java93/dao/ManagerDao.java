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
import bitcamp.java93.domain.Teacher;
import bitcamp.java93.util.DBConnectionPool;

public class ManagerDao {
  DBConnectionPool conPool;
  
  public ManagerDao(DBConnectionPool conPool) {
    this.conPool = conPool;
  }
  
  public List<Manager> selectList(int pageNo, int pageSize) throws Exception {
    Connection con  = conPool.getConnection();
    try ( 
      PreparedStatement stmt = con.prepareStatement(
          "select m.mno, m.name, m.tel, m.email, mr.posi, mr.path, mr.fax" +
          " from mgr mr inner join memb m on mr.mrno=m.mno" +
          " order by m.name asc" +
          " limit ?, ?");) {
      
      stmt.setInt(1, (pageNo-1)*pageSize);
      stmt.setInt(2, pageSize);
      

      ArrayList<Manager> list = new ArrayList<>();
      
      try (ResultSet rs = stmt.executeQuery();) {
        Manager manager = null;
        while (rs.next()) { 
          manager = new Manager();
          manager.setNo(rs.getInt("mno"));
          manager.setName(rs.getString("name"));
          manager.setTel(rs.getString("tel"));
          manager.setEmail(rs.getString("email"));
          manager.setPosi(rs.getString("posi"));
          manager.setPath(rs.getString("path"));
          manager.setFax(rs.getString("fax"));
          
          list.add(manager);
        }
      }
        return list;
        
        
    }finally {
      conPool.returnConnection(con);
    }
  }
  public Manager selectOne(int no) throws Exception {
    Connection con  = conPool.getConnection();
    
    try ( 
      PreparedStatement stmt = con.prepareStatement(
          "select m.mno, m.name, m.tel, m.email, mr.posi, mr.path, mr.fax" + 
          " from mgr mr inner join memb m on mr.mrno=m.mno" + 
          " where mr.mrno=?");) {
      
      stmt.setInt(1, no);
      
      try (ResultSet rs = stmt.executeQuery();) {
        if (!rs.next()) {
          return null;
        }
        
        Manager manager = new Manager();
        manager.setNo(rs.getInt("mno"));
        manager.setName(rs.getString("name"));
        manager.setTel(rs.getString("tel"));
        manager.setEmail(rs.getString("email"));
        manager.setPosi(rs.getString("posi"));
        manager.setPath(rs.getString("path"));
        manager.setFax(rs.getString("fax"));
        return manager;
      }
        
    }finally {
      conPool.returnConnection(con);
    }
  }
  
  public Manager selectOneByEmailPassword(String email, String password) throws Exception {
    Connection con  = conPool.getConnection();
    
    try ( 
      PreparedStatement stmt = con.prepareStatement(
          "select m.mno, m.name, m.tel, m.email, mr.posi, mr.path, mr.fax" +  
              " from mgr mr inner join memb m on mr.mrno=m.mno" + 
              " where email=? and pwd=password(?)");) {
      
      stmt.setString(1, email);
      stmt.setString(2, password);
      
      try (ResultSet rs = stmt.executeQuery();) {
        if (!rs.next()) {
          return null;
        }
        
        Manager manager = new Manager();
        manager.setNo(rs.getInt("mno"));
        manager.setName(rs.getString("name"));
        manager.setTel(rs.getString("tel"));
        manager.setEmail(rs.getString("email"));
        manager.setPosi(rs.getString("posi"));
        manager.setPath(rs.getString("path"));
        manager.setFax(rs.getString("fax"));
        return manager;
      }
        
    }finally {
      conPool.returnConnection(con);
    }
  }
  public int insert(Manager manager) throws Exception {
    Connection con  = conPool.getConnection();
    try (
        PreparedStatement stmt = con.prepareStatement(
           "insert into mgr(mrno,posi,path,fax) values(?,?,?,?)");) {
      
        stmt.setInt(1, manager.getNo());
        stmt.setString(2, manager.getPosi());
        stmt.setString(3, manager.getPath());
        stmt.setString(4, manager.getFax());
        
        return stmt.executeUpdate();
        
    } finally {
      conPool.returnConnection(con);
    }
  }
  
  public int delete(int no) throws Exception {
    Connection con  = conPool.getConnection();
    try (
      PreparedStatement stmt = con.prepareStatement(
        "delete from mgr where mrno=?");) {
      
      stmt.setInt(1, no);
      return stmt.executeUpdate();
    
    } finally {
      conPool.returnConnection(con);
    }
  }
    
  public int update(Manager manager) throws Exception{
    Connection con  = conPool.getConnection();
    try (
        PreparedStatement stmt = con.prepareStatement(
            "update tcher set posi=?, path=?, fax=? where mrno=?");
      ) {
        stmt.setString(1, manager.getPosi());
        stmt.setString(2, manager.getPath());
        stmt.setString(3, manager.getFax());
        stmt.setInt(4, manager.getNo());
        return stmt.executeUpdate();
        
    }finally {
      conPool.returnConnection(con);
    }
  }
}