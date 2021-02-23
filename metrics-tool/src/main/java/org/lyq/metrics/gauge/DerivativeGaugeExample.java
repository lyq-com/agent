package org.lyq.metrics.gauge;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.DerivativeGauge;
import com.codahale.metrics.Gauge;
import com.codahale.metrics.MetricRegistry;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.CacheStats;
import com.google.common.cache.LoadingCache;

import java.util.concurrent.TimeUnit;

public class DerivativeGaugeExample {

    private static final LoadingCache<String,String> cache = CacheBuilder.newBuilder()
            .maximumSize(10)
            .expireAfterAccess(5, TimeUnit.SECONDS)
            .recordStats()
            .build(new CacheLoader<String, String>() {
                @Override
                public String load(String key) throws Exception {
                    return key.toUpperCase();
                }
            });


    private static final MetricRegistry metricRegistry = new MetricRegistry();
    private static final ConsoleReporter reporter = ConsoleReporter.forRegistry(metricRegistry)
            .convertRatesTo(TimeUnit.SECONDS)
            .convertDurationsTo(TimeUnit.SECONDS)
            .build();

    public static void main(String[] args) throws InterruptedException {

        reporter.start(10,TimeUnit.SECONDS);
        Gauge<CacheStats> cacheGauge = metricRegistry.gauge("cache-stats", () -> cache::stats);

        metricRegistry.register("missCount",new DerivativeGauge<CacheStats,Long>(cacheGauge){
            @Override
            protected Long transform(CacheStats cacheStats) {
                return cacheStats.missCount();
            }
        });
        metricRegistry.register("loadExceptionCount",new DerivativeGauge<CacheStats,Long>(cacheGauge){
            @Override
            protected Long transform(CacheStats cacheStats) {
                return cacheStats.loadExceptionCount();
            }
        });

        while (true){
            business();
            TimeUnit.SECONDS.sleep(1);
        }
    }


    private static void business(){
        cache.getUnchecked("alex");
    }
}
