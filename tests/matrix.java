// $Id: matrix.java,v 1.3 2001/05/27 14:52:57 doug Exp $
// http://www.bagley.org/~doug/shootout/
// modified to use a little less memory by Thomas Holenstein

import java.io.*;
import java.util.*;

public class matrix {
  private static class Simple {
    public Simple next;

    public Simple(int n) {
      if(n > 0) {
        next = new Simple(n - 1);
      }
    }
  }

    static int SIZE = 30;

    public static void main(String args[]) {
	int n = Integer.parseInt(args[0]);
	int m1[][] = off_mkmatrix(SIZE, SIZE);
	int m2[][] = off_mkmatrix(SIZE, SIZE);
	int mm[][] = new int[SIZE][SIZE];
	for (int i=0; i<n; i++) {
	    off_mmult(SIZE, SIZE, m1, m2, mm);
	}
    }


    public static int[][] off_mkmatrix (int rows, int cols) {
	int count = 1;
	int m[][] = new int[rows][cols];
	for (int i=0; i<rows; i++) {
	    for (int j=0; j<cols; j++) {
		m[i][j] = count++;
	    }
	}
	return(m);
    }

    public static void off_mmult (int rows, int cols, 
	                      int[][] m1, int[][] m2, int[][] m3) {
	for (int i=0; i<rows; i++) {
	    for (int j=0; j<cols; j++) {
		int val = 0;
		for (int k=0; k<cols; k++) {
		    val += m1[i][k] * m2[k][j];
		}
		m3[i][j] = val;
	    }
	}
    }
}
