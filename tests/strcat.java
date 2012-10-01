// $Id: strcat.java2.java,v 1.4 2001/01/31 03:38:54 doug Exp $
// http://www.bagley.org/~doug/shootout/

import java.io.*;
import java.util.*;

public class strcat {
    public static void main(String args[]) throws IOException {
	int n = Integer.parseInt(args[0]);
	StringBuffer str = new StringBuffer();

	for (int i=0; i<n; i++) {
	    str.append("hello\n");
	}

	System.out.println(str.length());
    }
}
