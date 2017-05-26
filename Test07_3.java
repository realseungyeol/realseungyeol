package step18;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

import com.google.gson.Gson;

import step18.Test07_1.CalculatorJob;

public class Test07_3 {
  class Value {
    String comework;
    String fname;
  }

  class talkJob implements Runnable {
    Socket socket ;
    
    public talkJob(Socket socket) {
      this.socket = socket;
      System.out.println("=> 클라이언트와 연결되었음!");
    }
    
    public void run() {
      
      try (
        Socket socket = this.socket;
        BufferedReader in = new BufferedReader(
                              new InputStreamReader(socket.getInputStream()));
        PrintStream out = new PrintStream(socket.getOutputStream());
      ) {
        System.out.println("여기까지됨");
        String a = in.readLine();
        System.out.println("여기까지됨22");
        System.out.println(a);
        out.println(a);
        while (true) {
          String json = in.readLine();
          if (json == null)
            break;

          Gson gson = new Gson();
          Value value = gson.fromJson(json, Value.class);
          
          out.printf("[%s] => " + value.comework, value.fname);
          out.flush();
        }
        System.out.println("=> 클라이언와 연결 끊김!");
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }
  
  public void listen(int port) throws Exception {
    ServerSocket serverSocket = new ServerSocket(port);
    System.out.println("서버 실행 중...");
    
    while (true) {
      new Thread(new talkJob(serverSocket.accept())).start();
    }
  }
  
  public static void main(String[] args) throws Exception {
    Test07_3 server = new Test07_3();
    server.listen(8888);
  }
}







