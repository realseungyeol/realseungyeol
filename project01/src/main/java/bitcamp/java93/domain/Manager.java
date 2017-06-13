package bitcamp.java93.domain;
/*역할 : memb 테이블의 값을 보관할 때 사용할 클래스
 *  => 복합 데이터를 다룰때, 이렇게 클래스를 정의하여 사용한다.
 *  => 이런 복합 데이터를 보관하는 용도로 사용하는 클래스를
 *     "도메인(domain)" 클래스 또는 "DTO(Data Transfer Object)"라 부른다.
 * 
 */


public class Manager extends Member {
  String posi;
  String path;
  String fax;
  
  
  @Override
  public String toString() {
    return "Manager [posi=" + posi + ", path=" + path + ", fax=" + fax + ", no=" + no + ", name=" + name + ", tel="
        + tel + ", email=" + email + ", password=" + password + "]";
  }

  public String getPath() {
    return path;
  }

  public void setPath(String path) {
    this.path = path;
  }

  public String getFax() {
    return fax;
  }

  public void setFax(String fax) {
    this.fax = fax;
    
  }

  public String getPosi() {
    return posi;
  }

  public void setPosi(String posi) {
    this.posi = posi;
  }
  
}
