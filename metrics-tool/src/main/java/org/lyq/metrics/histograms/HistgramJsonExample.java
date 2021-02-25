package org.lyq.metrics.histograms;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.Histogram;
import com.codahale.metrics.MetricFilter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.json.MetricsModule;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class HistgramJsonExample {

    private static final MetricRegistry registry = new MetricRegistry();

    private static final ObjectMapper mapper = new ObjectMapper().registerModule(
            new MetricsModule(TimeUnit.SECONDS, TimeUnit.MILLISECONDS, false, MetricFilter.ALL));

    private static final Histogram histogram = registry.histogram("searc-result");


    public static void main(String[] args)  {

        new Thread(){
            @Override
            public void run() {
               while (true){
                   try{
                       String s = mapper.writeValueAsString(histogram);
                       System.out.println(s);

                       TimeUnit.SECONDS.sleep(10);
                   }catch (Exception e){

                   }
               }
            }
        }.start();


        while (true){
            doSearch();
            randomSleep();
        }

    }

    private static void doSearch(){
        histogram.update(ThreadLocalRandom.current().nextInt(10));
    }


    private static void randomSleep(){
        try {
            TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(5));
        } catch (InterruptedException e) {
        }
    }
}
