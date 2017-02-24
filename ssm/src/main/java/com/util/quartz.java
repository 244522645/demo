package com.util;

import java.util.Date;

/**
 * Created by Administrator on 2017/2/24.
 */
public class quartz {

    private static int counter = 0;
    public void execute()  {
        long ms = System.currentTimeMillis();
        System.out.println("\t\t" + new Date(ms));
        System.out.println("(" + counter++ + ")");
    }
}
