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

    // 创建一个注册表
    private final static MetricRegistry registry = new MetricRegistry();

    // 向注册表中注册一个 meter，名字“tqs”。
    private final static Meter requestMeter = registry.meter("tqs");
    // 向注册表中注册一个 meter，名字"volume"
    //private final static Meter sizeMeter = registry.meter("volume");
    // 向注册表中注册一个 meter，名字“error”
    private final static Meter errMeter = registry.meter("error");


    public static void main(String[] args) {

        //定义 reporter，用于报告结果。
        ConsoleReporter reporter = ConsoleReporter.forRegistry(registry)
                .convertRatesTo(TimeUnit.SECONDS)
                .convertDurationsTo(TimeUnit.MINUTES).build();

        //每 10 秒想控制台打印一次。
        reporter.start(10,TimeUnit.SECONDS);

        for (;;){
            handleRequest(new byte[ThreadLocalRandom.current().nextInt(1000)]);
            randomSleep();
        }
    }

    private static void handleRequest(byte[] request){
        try{
        requestMeter.mark();
        //sizeMeter.mark(request.length);
        int x = 10/ThreadLocalRandom.current().nextInt(6);
        randomSleep();
        }catch (Exception e){
            System.out.println("error");
            errMeter.mark();
        }
    }

    public static void randomSleep(){
        try {
            TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(5));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
