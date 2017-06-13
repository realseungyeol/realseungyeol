package bitcamp.java93.service;

import java.util.List;

import bitcamp.java93.dao.CroomDao;
import bitcamp.java93.dao.TeacherDao;
import bitcamp.java93.domain.Croom;
import bitcamp.java93.domain.Teacher;

public class CroomService {
  CroomDao croomDao;
  
  
  
  public void setCroomDao(CroomDao croomDao) {
    this.croomDao = croomDao;
  }
  
  public List<Croom> list(int pageNo,int pageSize) throws Exception{
    return croomDao.selectList(pageNo, pageSize);
  }
  
  public Croom get(int no) throws Exception {
    return croomDao.selectOne(no);
  }
  
  public void add(Croom croom) throws Exception {
    croomDao.insert(croom);
    
  }
  
  public void update(Croom croom) throws Exception {
    int count = croomDao.update(croom);
    
    count = croomDao.update(croom);
    if (count < 1) {
      throw new Exception(croom.getCrmno() + "찾지못함");
    }
  }

  public void remove(int no) throws Exception {
    int count = croomDao.delete(no);
    if (count < 1) {
      throw new Exception(no + "찾지못함");
    }
    
    try {
      count = croomDao.delete(no);
    } catch (Exception e) {}
  }
}
