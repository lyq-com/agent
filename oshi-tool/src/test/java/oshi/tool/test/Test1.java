package oshi.tool.test;


import javax.management.ObjectName;
import java.lang.management.*;
import java.util.List;

public class Test1 {

    public static void main(String[] args) throws InterruptedException {

        //demo01();
        //demo02();
//        demo03();
       // demo04();
        // 获取监控主机
    }

    /**
     * 操作系统的信息
     * OperatingSystemMXBean
     */
    public static void demo01(){
        OperatingSystemMXBean operatingSystemMXBean = ManagementFactory.getOperatingSystemMXBean();
        String arch = operatingSystemMXBean.getArch();//操作系统架构
        int availableProcessors = operatingSystemMXBean.getAvailableProcessors(); //可用的java虚拟机的处理器数。
        String name = operatingSystemMXBean.getName();//操作系统名称。
        double systemLoadAverage = operatingSystemMXBean.getSystemLoadAverage();//最后一分钟的系统负载平均值。
        String version = operatingSystemMXBean.getVersion(); //返回操作系统版本。

        //用于唯一识别的操作系统内的MBeanServer MXBean的ObjectName是 java.lang:type=OperatingSystem
        ObjectName objectName = operatingSystemMXBean.getObjectName();

        System.out.println("操作系统架构："+arch);
        System.out.println("可用的java虚拟机的处理器数："+availableProcessors);
        System.out.println("操作系统名称："+name);
        System.out.println("最后一分钟的系统负载平均值："+systemLoadAverage);
        System.out.println("返回操作系统版本："+version);
        System.out.println(" "+objectName);
    }


    /**
     * 类加载情况
     */
    public static void demo02(){
        ClassLoadingMXBean classLoadingMXBean = ManagementFactory.getClassLoadingMXBean();

        int loadedClassCount = classLoadingMXBean.getLoadedClassCount();
        long totalLoadedClassCount = classLoadingMXBean.getTotalLoadedClassCount();
        long unloadedClassCount = classLoadingMXBean.getUnloadedClassCount();
        ObjectName objectName = classLoadingMXBean.getObjectName();

        System.out.println("已加装当前类个数：" + loadedClassCount);
        System.out.println("已加载类总数：" + totalLoadedClassCount);
        System.out.println("已卸载类总数：" + unloadedClassCount);
        System.out.println(" "+objectName);
    }


    /**
     * 编译信息
     */
    public static void demo03(){
        CompilationMXBean compilationMXBean = ManagementFactory.getCompilationMXBean();

        String name = compilationMXBean.getName();
        long totalCompilationTime = -1;
        if (compilationMXBean.isCompilationTimeMonitoringSupported()){
             totalCompilationTime = compilationMXBean.getTotalCompilationTime();
        }

        System.out.println("编译器的名称：" + name);
        System.out.println("编译时花的大致时间（以毫秒计）:" + totalCompilationTime);
    }

    /**
     * 垃圾收集器
     */
    public static void demo04(){
        List<GarbageCollectorMXBean> garbageCollectorMXBeans = ManagementFactory.getGarbageCollectorMXBeans();
        if (garbageCollectorMXBeans.size()>0){
            for (GarbageCollectorMXBean garbageCollectorMXBean : garbageCollectorMXBeans) {
                String name = garbageCollectorMXBean.getName();
                long collectionCount = garbageCollectorMXBean.getCollectionCount();
                long collectionTime = garbageCollectorMXBean.getCollectionTime();
                System.out.println("垃圾回收器名称：" + name + "\t" + "回收数量：" + collectionCount + "\t" + "耗时：" + collectionTime);

            }
        }
    }

}
