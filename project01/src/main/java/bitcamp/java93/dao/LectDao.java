/* 역할 : 
 * 
 */
package bitcamp.java93.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bitcamp.java93.domain.Lect;
import bitcamp.java93.util.DBConnectionPool;

public class LectDao {
  DBConnectionPool conPool;
  
  public LectDao(DBConnectionPool conPool) {
    this.conPool = conPool;
  }
  
  public List<Lect> selectList(int pageNo, int pageSize) throws Exception {
    Connection con  = conPool.getConnection();
    try ( 
      PreparedStatement stmt = con.prepareStatement(
          "select lno, crmno, mrno, titl, dscp, sdt, edt, qty, pric, thrs from lect order by titl asc limit ?, ?");) {
      
      stmt.setInt(1, (pageNo-1)*pageSize);
      stmt.setInt(2, pageSize);
      

      ArrayList<Lect> list = new ArrayList<>();
      
      try (ResultSet rs = stmt.executeQuery();) {
        Lect lect = null;
        while (rs.next()) { 
          lect = new Lect();
          lect.setNo(rs.getInt("lno"));
          lect.setCrmno(rs.getInt("crmno"));
          lect.setMrno(rs.getInt("mrno"));
          lect.setTitl(rs.getString("titl"));
          lect.setDscp(rs.getString("dscp"));
          lect.setSdt(rs.getString("sdt"));
          lect.setEdt(rs.getString("edt"));
          lect.setQty(rs.getInt("qty"));
          lect.setPric(rs.getInt("pric"));
          lect.setThrs(rs.getInt("thrs"));
          
          list.add(lect);
        }
      }
        return list;
        
    }finally {
      conPool.returnConnection(con);
    }
  }
  
  public Lect selectOne(int no) throws Exception {
    Connection con  = conPool.getConnection();
    
    try ( 
      PreparedStatement stmt = con.prepareStatement(
          "select lno, crmno, mrno, titl, dscp, sdt, edt, qty, pric, thrs from lect where lno=?");) {
      
      stmt.setInt(1, no);
      
      try (ResultSet rs = stmt.executeQuery();) {
        if (!rs.next()) {
          return null;
        }
        
        Lect lect = new Lect();
        lect.setNo(rs.getInt("lno"));
        lect.setCrmno(rs.getInt("crmno"));
        lect.setMrno(rs.getInt("mrno"));
        lect.setTitl(rs.getString("titl"));
        lect.setDscp(rs.getString("dscp"));
        lect.setSdt(rs.getString("sdt"));
        lect.setEdt(rs.getString("edt"));
        lect.setQty(rs.getInt("qty"));
        lect.setPric(rs.getInt("pric"));
        lect.setThrs(rs.getInt("thrs"));
        return lect;
      }
        
    }finally {
      conPool.returnConnection(con);
    }
  }
  public int insert(Lect lect) throws Exception {
    Connection con  = conPool.getConnection();
    try (
        PreparedStatement stmt = con.prepareStatement(
            "insert into lect(lno, crmno, mrno, titl, dscp, sdt, edt, qty, pric, thrs) values(?,?,?,?,?,?,?,?,?,?)");) {
      
        stmt.setInt(1, lect.getNo());
        stmt.setInt(2, lect.getCrmno());
        stmt.setInt(3, lect.getMrno());
        stmt.setString(4, lect.getTitl());
        stmt.setString(5, lect.getDscp());
        stmt.setString(6, lect.getSdt());
        stmt.setString(7, lect.getEdt());
        stmt.setInt(8, lect.getQty());
        stmt.setInt(9, lect.getPric());
        stmt.setInt(10, lect.getThrs());
            
        return stmt.executeUpdate();
        
    } finally {
      conPool.returnConnection(con);
    }
  }
  
  public int delete(int no) throws Exception {
    Connection con  = conPool.getConnection();
    try (
      PreparedStatement stmt = con.prepareStatement(
        "delete from lect where lno=?");) {
      
      stmt.setInt(1, no);
      return stmt.executeUpdate();
    
    } finally {
      conPool.returnConnection(con);
    }
    
  }

  public int update(Lect lect) throws Exception{
    Connection con  = conPool.getConnection();
    try (
        PreparedStatement stmt = con.prepareStatement(
            "update lect set lno=?, crmno=?, mrno=?, titl=?, dscp=?, sdt=?, edt=?, qty=?, pric=?, thrs=? where lno=?");
      ) {
      
      stmt.setInt(1, lect.getNo());
      stmt.setInt(2, lect.getCrmno());
      stmt.setInt(3, lect.getMrno());
      stmt.setString(4, lect.getTitl());
      stmt.setString(5, lect.getDscp());
      stmt.setString(6, lect.getSdt());
      stmt.setString(7, lect.getEdt());
      stmt.setInt(8, lect.getQty());
      stmt.setInt(9, lect.getPric());
      stmt.setInt(10, lect.getThrs());
      stmt.setInt(11, lect.getNo());
      
        return stmt.executeUpdate();
        
    }finally {
      conPool.returnConnection(con);
    }
  }
}