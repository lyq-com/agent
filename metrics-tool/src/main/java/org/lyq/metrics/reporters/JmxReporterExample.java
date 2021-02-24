package org.lyq.metrics.reporters;

import com.codahale.metrics.*;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;


public class JmxReporterExample {

    private static final MetricRegistry registry = new MetricRegistry();

    private static final Counter totalBusiness = new Counter();
    private static final Counter successBusiness = new Counter();
    private static final Counter failBusiness = new Counter();
    private static final Timer timer = new Timer();
    private static final Histogram volumeHisto = new Histogram(new ExponentiallyDecayingReservoir());
    private static final RatioGauge successGauge = new RatioGauge() {
        @Override
        protected Ratio getRatio() {
            return Ratio.of(successBusiness.getCount(),totalBusiness.getCount());
        }
    };

    private static final JmxReporter jmxReporter = JmxReporter.forRegistry(registry)
            .convertRatesTo(TimeUnit.SECONDS)
            .convertDurationsTo(TimeUnit.SECONDS)
            .build();

    private static final ConsoleReporter consoleReporter = ConsoleReporter.forRegistry(registry)
            .convertRatesTo(TimeUnit.SECONDS)
            .convertDurationsTo(TimeUnit.SECONDS)
            .build();

    static {
        registry.register("cloud-disk-uplod-total",totalBusiness);
        registry.register("cloud-disk-uplod-success",successBusiness);
        registry.register("cloud-disk-uplod-fail",failBusiness);
        registry.register("cloud-disk-uplod-timer",timer);
        registry.register("cloud-disk-uplod-volumeHisto",volumeHisto);
        registry.register("cloud-disk-uplod-successRate",successGauge);


    }

    public static void main(String[] args) {

        jmxReporter.start();
        consoleReporter.start(5,TimeUnit.SECONDS);

        while (true){
            upload(new byte[ThreadLocalRandom.current().nextInt(10_000)]);
        }

    }

    private static void upload(byte[] buffer){
        totalBusiness.inc();

        Timer.Context context = timer.time();
        try {
            int x = 1/ ThreadLocalRandom.current().nextInt(5);
            TimeUnit.SECONDS.sleep(2);
            volumeHisto.update(buffer.length);
            successBusiness.inc();
        } catch (Exception e) {
            System.out.println("error....");
            failBusiness.inc();
        }finally {
            context.close();
        }
    }
}
