public class testsuite3 {
  public static void main(String[] args) {
    for(int i = 0; i < 1000; i++) {
      System.out.println("Iteration: " + i);
      offHeavy(new int[100000]);
    }
  }

  public static void offHeavy(int[] A) {
    A[4] = 7;
    System.out.println("Off...");
  }
}
