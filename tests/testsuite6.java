
class testsuitepriv1 {
  public int x;

  private static void offTestStaticInit(int x) {
    System.out.println("testclass init: " + x);
  }

  static {
    for(int i = 0; i < 10; i++) {
      offTestStaticInit(i);
    }
  }
}

public class testsuite6 {
  public static void main(String[] args) {
    testsuitepriv1 obj = new testsuitepriv1();
    obj.x = 555;
    offTestStatic(obj);
  }

  public static void offTestStatic(testsuitepriv1 obj) {
    System.out.println(obj.x);
  }
}
