/*역할 : memb 테이블의 값을 보관할 때 사용할 클래스
 *  => 복합 데이터를 다룰때, 이렇게 클래스를 정의하여 사용한다.
 *  => 이런 복합 데이터를 보관하는 용도로 사용하는 클래스를
 *     "도메인(domain)" 클래스 또는 "DTO(Data Transfer Object)"라 부른다.
 * 
 */
package step03;

public class Lect {
  int no;
  int crmno;
  int mrno;
  String titl;
  String dscp;
  String sday;
  String eday;
  int qty;
  int pric;
  int thrs;
  
  @Override
  public String toString() {
    return "Lect [no=" + no + ", rmno=" + crmno + ", mrno=" + mrno + ", titl=" + titl + ", dscp=" + dscp + ", sday=" + sday + ", eday=" + eday + ", qty=" + qty + ", pric=" + pric + ", thrs=" + thrs + "]";
  }

  public int getNo() {
    return no;
  }

  public void setNo(int no) {
    this.no = no;
  }

  public int getCrmno() {
    return crmno;
  }

  public void setCrmno(int crmno) {
    this.crmno = crmno;
  }

  public int getMrno() {
    return mrno;
  }

  public void setMrno(int mrno) {
    this.mrno = mrno;
  }

  public String getName() {
    return titl;
  }

  public void setName(String name) {
    this.titl = titl;
  }

  public String getDscp() {
    return dscp;
  }

  public void setDscp(String dscp) {
    this.dscp = dscp;
  }

  public String getSday() {
    return sday;
  }

  public void setSday(String sday) {
    this.sday = sday;
  }

  public String getEday() {
    return eday;
  }

  public void setEday(String eday) {
    this.eday = eday;
  }

  public int getQty() {
    return qty;
  }

  public void setQty(int qty) {
    this.qty = qty;
  }

  public int getPric() {
    return pric;
  }

  public void setPric(int pric) {
    this.pric = pric;
  }

  public int getThrs() {
    return thrs;
  }

  public void setThrs(int thrs) {
    this.thrs = thrs;
  }
  
  
}
