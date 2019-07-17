package org.eric.sessionclient.util;

import java.util.concurrent.atomic.AtomicLong;

public class IDUtil {
    private static AtomicLong count = new AtomicLong();

    public static long getId(){
         return count.incrementAndGet();
    }
}
