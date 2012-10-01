public class satest {
  public static int readInt;
  public static int writeInt;
  public static MyObject readObj;
  public static MyObject writeObj;
  public static MyObject indirectWriteObj;

  public static void main(String[] args) {
    readInt = 4; writeInt = 5;
    readObj = new MyObject(6);
    writeObj = new MyObject(7);
    MyObject obj1 = new MyObject(8);
    MyObject obj2 = new MyObject(9);
    MyObject obj3 = new MyObject(10);
    indirectWriteObj = new MyObject(15);
    System.out.println("Result: " + testsa(11, 12, 13, obj1, obj2, obj3));
  }

  /* Expected resutls
   *
   * Call children:    { }
   * Global Write Set: { writeInt, writeObj }
   * Global Read Set:  { readInt, readObj, (maybe writeObj) }
   * Param Write Set:  { paraWriteObj }
   * Param Read Set:   { paraReadInt, paraReadObj, (maybe paraWriteObj) }
   */
  public static int testsa(int paraWriteInt, int paraReadInt, int paraIgnInt,
                            MyObject paraWriteObj, MyObject paraReadObj,
                            MyObject paraIgnObj) {
    writeInt = 0;
    writeObj.x = 1;
    paraWriteInt = 2;
    paraWriteObj.x = 3;
    MyObject obj = indirection(null);
    obj.x = 14;
    return paraWriteInt + paraReadInt + paraWriteObj.x + paraReadObj.x +
           writeInt + readInt + readObj.x + writeObj.x + obj.x;
  }

  public static MyObject indirection(MyObject indirectParam) {
    return indirectParam == null ? indirectWriteObj : indirectParam;
  }

  /* Note C is intentionally a fixed point and should be the only non-written
   * parameter. */
  public static void testNesting(MyObject A, MyObject B, MyObject C, MyObject D,
                          MyObject E, MyObject F, MyObject G, MyObject H) {
    if(H == null) {
      testNesting(H, A, C, B, D, E, F, G);
    } else {
      H.x = 101;
    }
  }

  public static class MyObject {
    public int x;

    public MyObject(int x) {
      this.x = x;
    }
  }
}
