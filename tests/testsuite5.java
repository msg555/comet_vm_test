public class testsuite5 {
  public static void main(String[] args) {
    try { Thread.currentThread().sleep(1000); } catch(Exception e) {}
    offClassTest(testsuite5.class);
  }

  public static void offClassTest(Class cl) {
    System.out.println(cl);
    System.out.println(cl.getName());
    System.out.println(testsuite5.class);
    System.out.println(cl == testsuite5.class);
  }
}
