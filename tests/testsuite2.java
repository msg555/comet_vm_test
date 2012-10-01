public class testsuite2 {
  public static void main(String[] args) {
    try { Thread.currentThread().sleep(1000); } catch(Exception e) { }

    final Object obj = new Object();
    synchronized(obj) {
      checkLock(obj);
      offInheritSync(obj);
      checkLock(obj);
    }

    for(int i = 0; i < 5; i++) {
      new Thread() { public void run() {
        for(int i = 0; i < 1; i++) {
          offSync(obj);
        }
        System.out.println("Done");
      }}.start();
    }
    for(int i = 0; i < 5; i++) {
      new Thread() { public void run() {
        for(int i = 0; i < 1; i++) {
          synchronized(obj) {
            offSync2(obj);
          }
        }
        System.out.println("Done");
      }}.start();
    }
    try { Thread.currentThread().sleep(1000); } catch(Exception e) { }
    System.out.println("notifiying");
    for(int i = 0; i < 10; i++) {
      new Thread() { public void run() {
        for(int i = 0; i < 1; i++) {
          synchronized(obj) {
            obj.notify();
          }
        }
      }}.start();
    }
  }

  public static void offSync(Object obj) {
    synchronized(obj) {
      try {
        System.out.println("waiting...");
        obj.wait();
      } catch(Exception e) {}
    }
  }

  public static void offSync2(Object obj) {
    try {
      System.out.println("waiting...");
      obj.wait();
    } catch(Exception e) {}
  }

  public static void offInheritSync(Object obj) {
    checkLock(obj);
  }

  public static void checkLock(Object obj) {
    if(Thread.currentThread().holdsLock(obj)) {
      System.out.println("Lock held");
    } else {
      System.out.println("Lock not held");
    }
  }
}
