public class testsuite4 {
  public static void main(String[] args) {
    try {
      Class dexClassLoader = Class.forName("dalvik.system.DexClassLoader");
      ClassLoader classLoader = (ClassLoader)dexClassLoader
          .getConstructor(String.class, String.class,
                          String.class, ClassLoader.class)
          .newInstance("primestest.jar", "data/dalvik-cache", "system/lib",
                       ClassLoader.getSystemClassLoader());
      Class testClass = classLoader.loadClass("testsuiteprime");
      System.out.println(
          (long)(Long)testClass.getDeclaredMethod("offNextPrime", Long.class)
          .invoke(null, new Long(123345)));
      System.out.println(testClass);

/* Investigate this bug later
      testsuite4.class.getDeclaredMethod("test", long.class).invoke(null, 4294967296L);
      testsuite4.class.getDeclaredMethod("test", long.class).invoke(null, 1);
      testsuite4.class.getDeclaredMethod("test", long.class).invoke(null, 1L);
*/
    } catch(Exception e) {
      e.printStackTrace();
    }
  }

  public static void test(long x) {
    System.out.println(x);
  }
}
