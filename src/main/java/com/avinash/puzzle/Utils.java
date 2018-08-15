/**
 * 
 */
package com.avinash.puzzle;

import java.io.IOException;
import java.util.Random;

/**
 * @author avinashsingh
 *
 */
public class Utils {

    public static void sleep (long timeInMs) {
        try {
            Thread.sleep(timeInMs);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    
    public static int readKey() {
        int key = 0;
        try {
            
            int cnt = System.in.available();
            if (cnt > 0) {
                key = System.in.read();
            }
        } catch (IOException e) {
            throw new RuntimeException("Some Exception Occurred", e);
        }
        return key;
    }
    
    public static <T> T random (T t1, T t2) {
        return Math.random() > 0.5 ? t1 : t2;
    }
    
    private static Random random = new Random();
    public static int random (int limit) {
        return random.nextInt(limit);
    }
}
