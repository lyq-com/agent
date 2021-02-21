package oshi.tool.test.operatingsystem;

import org.junit.Test;
import oshi.software.os.OSProcess;

import java.util.List;

public class OSProcessTest extends OperaingBaseTest{

    @Test
    public void test(){
        List<OSProcess> processes = operatingSystem.getProcesses();

        for (OSProcess process : processes) {
            int processID = process.getProcessID();
            int parentProcessID = process.getParentProcessID();
            int threadCount = process.getThreadCount();

        }

    }
}
