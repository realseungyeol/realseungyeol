import java.io.File;

public class BIT_ls {
  public static void main(String[] args) throws Exception {
    File f = new File ("./");
    
    File[] files = f.listFiles();
    
    for (File name : files) {
      System.out.printf("%s %12ds %s\n",
          (name.isDirectory() ? "d" : "-"),
          name.length(),
          name.getName());
    }
       
 
  }

  
}
