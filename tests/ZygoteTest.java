import java.lang.reflect.*;

public class ZygoteTest {
  public static void main(String[] args) {
    try {
      Class zygoteClazz = Class.forName("dalvik.system.Zygote");
      Method forkMeth = zygoteClazz.getMethod("fork");
      System.out.println("Zygote looping");

      for(int i = 0; i < 1; i++) {
        int pid = (Integer)forkMeth.invoke(null);
        System.out.println("Got pid: " + pid);
        if(pid == 0) {
          return;
        }
      }
      Thread.currentThread().sleep(1000);
    } catch(Exception e) {
      e.printStackTrace();
    }
  }
}
