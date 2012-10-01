// $Id: objinst.java,v 1.2 2000/12/24 22:04:57 doug Exp $
// http://www.bagley.org/~doug/shootout/
// Collection class code is from my friend Phil Chu, Thanks Phil!

import java.io.*;
import java.util.*;
import java.text.*;

class Toggle_x {
  boolean state = true;

  public Toggle_x(boolean start_state) {
    state = start_state;
  }

  public boolean value() {
    return state;
  }

  public Toggle_x activate() {
    state = !state;
    return this;
  }
}

class NthToggle_x extends Toggle_x {
  int count_max = 0;
  int counter = 0;

  public NthToggle_x(boolean start_state, int max_counter) {
    super(start_state);
    count_max = max_counter;
    counter = 0;
  }

  public Toggle_x activate() {
    counter += 1;
    if(counter >= count_max) {
      state = !state;
      counter = 0;
    }
    return this;
  }
}

public class objinst {
  public static void main(String args[]) throws IOException {
    int n = Integer.parseInt(args[0]);
    Toggle_x toggle1 = new Toggle_x(true);
    for(int i = 0; i < 5; i++) {
      System.out.println((toggle1.activate().value()) ? "true" : "false");
    }
    for(int i = 0; i < n; i++) {
      Toggle_x toggle = new Toggle_x(true);
    }

    System.out.println("");
  
    NthToggle_x ntoggle1 = new NthToggle_x(true, 3);
    for(int i = 0; i < 8; i++) {
      System.out.println((ntoggle1.activate().value()) ? "true" : "false");
    }
    for(int i = 0; i < n; i++) {
      NthToggle_x toggle = new NthToggle_x(true, 3);
    }
  }
}
