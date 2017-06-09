package bitcamp.java93.domain;

public class Croom {
  int crmno;
  String name;
  
  
  public int getCrmno() {
    return crmno;
  }


  public void setCrmno(int crmno) {
    this.crmno = crmno;
  }


  public String getName() {
    return name;
  }


  public void setName(String name) {
    this.name = name;
  }


  @Override
  public String toString() {
    return "Croom [no=" + crmno + ", name=" + name + "]";
  }
  
  
}
