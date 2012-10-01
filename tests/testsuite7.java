public class testsuite7 {
  public int var = 0;
  public static int glob = 0;

  public static void main(String[] args) {
    try { Thread.currentThread().sleep(1000); } catch(InterruptedException e) {}
    final testsuite7 tst =  new testsuite7();
    tst.var = 1;
    Thread thread;
    synchronized(tst) {
      thread = new Thread() {
        public void run() {
          synchronized(tst) {
            ++glob;
            tst.offTest();
          }
        }
      };
      thread.start();
      try { tst.wait(); } catch(InterruptedException e) {}
      System.out.println("var = " + tst.var);
      tst.var = 3;
      tst.notify();
    }
    try { thread.join(); } catch(InterruptedException e) {}
  }

  public void offTest() {
    System.out.println("var = " + var);
    this.var = 2;
    notify();
    try { wait(); } catch(InterruptedException e) {}
    System.out.println("var = " + var);
    System.out.println("glob = " + glob);
  }
}
