package oshi.tool.test.operatingsystem;

import org.junit.Before;
import org.junit.Test;
import oshi.SystemInfo;
import oshi.software.os.OperatingSystem;


public class OperatingSystemTest {

    private OperatingSystem operatingSystem = null;

    @Before
    public void init(){
        SystemInfo systemInfo = new SystemInfo();
        operatingSystem = systemInfo.getOperatingSystem();
    }


    @Test
    public void test01(){
        // 获取操作系统族。
        String family = operatingSystem.getFamily();
        //获取操作系统制造商。
        String manufacturer = operatingSystem.getManufacturer();
        //获取操作系统版本信息。
        String version = operatingSystem.getVersionInfo().getVersion();
        //获取当前进程ID
        int processId = operatingSystem.getProcessId();
        //获取当前运行的进程数
        int processCount = operatingSystem.getProcessCount();

        System.out.println("操作系统族：" + family);
        System.out.println("操作系统制造商：" + manufacturer);
        System.out.println("操作系统版本信息：" + version) ;
        System.out.println("当前进程ID：" + processId);
        System.out.println("当前运行的进程数：" + processCount);


    }
    public static void main(String[] args) throws InterruptedException {

        SystemInfo systemInfo = new SystemInfo();
        OperatingSystem operatingSystem = operatingSystem = systemInfo.getOperatingSystem();

        while (true){
            // 获取操作系统族。
            String family = operatingSystem.getFamily();
            //获取操作系统制造商。
            String manufacturer = operatingSystem.getManufacturer();
            //获取操作系统版本信息。
            String version = operatingSystem.getVersionInfo().getVersion();
            //获取当前进程ID
            int processId = operatingSystem.getProcessId();
            //获取当前运行的进程数
            int processCount = operatingSystem.getProcessCount();
            //获取当前运行的线程数
            int threadCount = operatingSystem.getThreadCount();
            //获取操作系统支持的位数（32位或64位）
            int bitness = operatingSystem.getBitness();
            //获取系统启动时间(启动后的时间)。
            long systemUptime = operatingSystem.getSystemUptime();

            System.out.println("操作系统族：" + family);
            System.out.println("操作系统制造商：" + manufacturer);
            System.out.println("操作系统版本信息：" + version) ;
            System.out.println("当前进程ID：" + processId);
            System.out.println("当前运行的进程数：" + processCount);
            System.out.println("当前运行的线程数：" + threadCount);
            System.out.println("操作系统的位数：" + bitness);
            System.out.println("系统启动时长：" + systemUptime);
            // 2036741

            Thread.sleep(1000 * 10);
        }
    }
}
