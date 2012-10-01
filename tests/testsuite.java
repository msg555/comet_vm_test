import java.util.*;

public class testsuite {
  public static class ObjFlat implements Runnable {
    public byte a;
    public char b;
    public short c;
    public float d;
    public int e;
    public long f;
    public double g;
    public boolean h;

    public ObjFlat() {
      a = 123;
      b = 'Y';
      c = 12345;
      d = 2.71828f;
      e = 123454321;
      f = 12345678987654321L;
      g = 3.14159265358979323;
      h = true;
    }

    public void run() {
      System.out.println("" + a + b + c + d + e + f + g + h);
    }

    public void offMember() {
      run();
    }
  }

  public static class ObjRef implements Runnable {
    private Runnable r1;
    private Runnable r2;
  
    public ObjRef(Runnable r1, Runnable r2) {
      this.r1 = r1;
      this.r2 = r2;
    }

    public void run() {
      System.out.println("dumping refs");
      if(r1 != null) r1.run();
      if(r2 != null) r2.run();
    }
  }


  public static void main(String[] args) {
    try { Thread.currentThread().sleep(1000); } catch(Exception e) {}

    Thread[] t = new Thread[1];
    for(int i = 0; i < t.length; i++) {
      t[i] = new Thread() {
        public void run() {

      off1(new ObjFlat());
      off2().run();
      off1(new ObjRef(new ObjFlat(), new ObjFlat()));
      off1(new ObjRef(new ObjRef(new ObjFlat(), new ObjFlat()), new ObjFlat()));
      Runnable r = new ObjFlat();
      off3(new ObjRef(r, r), new ObjRef(r, r));

      Runnable[][] arr = new Runnable[4][3];
      arr[0][2] = arr[1][1] = arr[2][0] = new ObjFlat();
      off4(arr);
      off6(3, 1, 4, 1, 5, 9);

      new ObjFlat().offMember();

      try {
        off5();
      } catch(Throwable e) {
        System.out.println("Prining stack trace");
        System.out.println(e.getCause());
        e.printStackTrace();
      }

      ObjFlat x = new ObjFlat();
      off7(x);
      x.run();
        }
      };
      t[i].start();
    }
    for(int i = 0; i < t.length; i++) {
      try {t[i].join(); } catch(InterruptedException e) {}
    }
    System.out.println("done");
  }

  private static void off1(Runnable o) {
    System.out.println("off1()");
    o.run();
  }

  private static Runnable off2() {
    System.out.println("off2()");
    return new ObjFlat();
  }

  private static void off3(Runnable a, Runnable b) {
    System.out.println("off3()");
    a.run();
    b.run();
  }

  private static void off4(Runnable[][] A) {
    System.out.println("off4()");
    for(int i = 0; i < A.length; i++) {
      System.out.println("A[" + i + "].length = " + A[i].length);
      for(int j = 0; j < A[i].length; j++) {
        if(A[i][j] != null) A[i][j].run();
      }
    }
  }

  private static void off5() throws Exception {
    throw new Exception("this is a test", new Exception("I'm the culprit"));
  }

  private static void off6(int ... A) {
    for(int x : A) {
      System.out.println("element: " + x);
    }
  }

  private static void off7(ObjFlat x) {
    x.b = 'M';
    off1(x);
    x.b = 'N';
  }
}
