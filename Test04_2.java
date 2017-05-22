/* 데이터 프로세싱 스트림- BufferedInputStream 도입전
 * => data processing stream 클래스?
 *    - 
 * => 예 :
 *    DataInputStream/ DataOutputStream
 *      - byte, short, int, long. float, double, boolean, char, String 타입의 데이터를
 *        바이트 배열로 가공하여 입출력을 수행.
 *    BufferedIputStream/ BufferedOutputStream
 *      - 데이터를 읽고 쓰는데 중간에 임시 저장소 버퍼(캐시;cache)를 이용한다.
 *      - 그래서 실제 파일로 입출력하는 횟수를 줄임으로써 읽고 쓰기 속돌르 높이는 방법이다.
 *    ObjectIputStream/ ObjectOutputStream
 *      - 객체를 바이트 배열로 만들어 출력하고, 바이트 배열을 다시 객체로 복원한다.
 *      
 * => BufferedIputStream
 *      - 이 객체는 내부에 데이터를 임시 저장할 버퍼(바이트 배열)을 내장하고 있다.
 *      - 데이터를 읽어 들일 때 최대한 버퍼(바이트 배열) 크기만큼 한 번에 읽어 들인다.
 *      - 그리고 그 버퍼에서 조금씩 퍼 스다가, 버퍼가 바닥나면 다시 그 버퍼만큼 읽어 들인다.
 *      - 이렇게 함으로써 읽어 들이는 횟수를 줄이게 되어 읽기 속도가 향상된다. 
 */
package step16;

import java.io.FileInputStream;

public class Test04_2 {
  
  public static void main(String[] args) throws Exception {
    
    FileInputStream in = new FileInputStream("temp/a.pdf");
    
    
    byte[] buf = new byte[8196000];
    int len = 0;
    int countpoint = 0;
    
    long start = System.currentTimeMillis();
    
    while((len = in.read(buf)) != -1) {
      System.out.println(".");
        if((++countpoint % 50) == 0) {
          System.out.println();
          countpoint = 0;
        }
    }
    
    long end = System.currentTimeMillis();
    
    System.out.printf("\n걸린시간 = %d", end - start);
    in.close();
  }
  
}
