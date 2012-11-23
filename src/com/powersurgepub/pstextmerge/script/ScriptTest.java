package com.powersurgepub.pstextmerge.script;

  import com.powersurgepub.psutils.*;

/**
   Method to test the other classes in the package.
  
   This code is copyright (c) 2003 by Herb Bowie of PowerSurge Publishing. 
   All rights reserved. <p>
   
   Version History: <ul><li>
    </ul>
  
   @author Herb Bowie (<a href="mailto:herb@powersurgepub.com">
           herb@powersurgepub.com</a>)<br>
           of PowerSurge Publishing (<A href="http://www.powersurgepub.com">
           www.powersurgepub.com</a>)
  
   @version 2003/02/16 - Initially written.
 */

public class ScriptTest {  
  
  /** 
     Tests some of the script classes.
   */
  public static void main (String args[]) {
    
    testRemovePath ("/Users/hbowie/test/test.doc", "/Users/hbowie");
    testRemovePath ("/Users/hbowie/test2.doc", "/Users/hbowie/test");
    testRemovePath ("/Users/hbowie/test2.doc", "/Users/hbowie/test/tdfczar");
    testRemovePath ("c:Users/hbowie/test2.doc", "c:Users/hbowie/test/tdfczar");
    
    System.out.flush();
  } // end of main method
  
  private static void testRemovePath (String f, String p) {
    System.out.println("");
    ScriptAction test = new ScriptAction ("Input", "Open", "", "", f);
    System.out.println ("Original file name: " + f);
    System.out.println ("Path:               " + p);
    test.removePath(p, "");
    System.out.println ("Modified file name: " + test.getValue());
    test.restorePath(p, "");
    System.out.println ("Restored file name: " + test.getValue());
  }
  
} // end of class ScriptTest