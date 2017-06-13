package bitcamp.java93.service;

import java.util.List;

import bitcamp.java93.dao.MemberDao;
import bitcamp.java93.domain.Manager;
import bitcamp.java93.domain.Member;

public class MemberService {
  MemberDao memberDao;
  
  public void setMemberDao(MemberDao memberDao) {
    this.memberDao = memberDao;
  }
  
  public List<Member> list(int pageNo,int pageSize) throws Exception{
    return memberDao.selectList(pageNo, pageSize);
  }
  
  public Member get(int no) throws Exception {
    return memberDao.selectOne(no);
  }
  
  public void add(Manager manager) throws Exception {
    memberDao.insert(manager);
    
  }
  
  public void update(Member member) throws Exception {
    int count = memberDao.update(member);
    if (count < 1) {
      throw new Exception(member.getNo() + "찾지못함");
    }
  }

  public void remove(int no) throws Exception {
    int count = memberDao.delete(no);
    if (count < 1) {
      throw new Exception(no + "찾지못함");
    }
    
    try {
      count = memberDao.delete(no);
    } catch (Exception e) {}
  }
}
