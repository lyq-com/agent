package org.lyq.metrics.meter;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class ADemo {

    public static void main(String[] args) {
        final String s = TimeUnit.SECONDS.toString().toLowerCase(Locale.US);
        System.out.println(s.substring(0, s.length() - 1));
    }
}
