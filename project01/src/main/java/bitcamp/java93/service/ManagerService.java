package bitcamp.java93.service;

import java.util.List;

import bitcamp.java93.dao.ManagerDao;
import bitcamp.java93.dao.MemberDao;
import bitcamp.java93.domain.Manager;
import bitcamp.java93.domain.Teacher;

public class ManagerService {
  MemberDao memberDao;
  ManagerDao managerDao;
  
  public void setMemberDao(MemberDao memberDao) {
    this.memberDao = memberDao;
  }
  
  public void setManagerDao(ManagerDao managerDao) {
    this.managerDao = managerDao;
  }
  
  public List<Manager> list(int pageNo,int pageSize) throws Exception{
    return managerDao.selectList(pageNo, pageSize);
  }
  
  public Manager get(int no) throws Exception {
    return managerDao.selectOne(no);
  }
  
  public void add(Manager manager) throws Exception {
    memberDao.insert(manager);
    managerDao.insert(manager);
    
  }
  
  public void update(Manager manager) throws Exception {
    int count = memberDao.update(manager);
    if (count < 1) {
      throw new Exception(manager.getNo() + "찾지못함");
    }
    count = managerDao.update(manager);
    if (count < 1) {
      throw new Exception(manager.getNo() + "찾지못함");
    }
  }

  public void remove(int no) throws Exception {
    int count = managerDao.delete(no);
    if (count < 1) {
      throw new Exception(no + "찾지못함");
    }
    
    try {
      count = memberDao.delete(no);
    } catch (Exception e) {}
  }
}
