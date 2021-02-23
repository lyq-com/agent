package org.lyq.metrics.meter;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.Meter;
import com.codahale.metrics.MetricRegistry;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * Meter
 */
public class MeterExample {

    private final static MetricRegistry registry = new MetricRegistry();

    private final static Meter requestMeter = registry.meter("tqs");
    private final static Meter sizeMeter = registry.meter("volume");
    private final static Meter errMeter = registry.meter("error");

    /**
     * psvm、main
     * @param args
     */
    public static void main(String[] args) {

        ConsoleReporter reporter = ConsoleReporter.forRegistry(registry)
                .convertRatesTo(TimeUnit.MINUTES)
                .convertDurationsTo(TimeUnit.MINUTES).build();

        reporter.start(10,TimeUnit.SECONDS);

        for (;;){
            handleRequest(new byte[ThreadLocalRandom.current().nextInt(1000)]);
            randomSleep();
        }
    }

    private static void handleRequest(byte[] request){
        try{
        requestMeter.mark();
        sizeMeter.mark(request.length);
        int x = 10/ThreadLocalRandom.current().nextInt(6);
        randomSleep();
        }catch (Exception e){
            System.out.println("error");
            errMeter.mark();
        }
    }

    public static void randomSleep(){
        try {
            TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(10));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
