package oshi.tool.test.operatingsystem;

import org.junit.Before;
import oshi.SystemInfo;
import oshi.software.os.OperatingSystem;

public class OperaingBaseTest {

    public OperatingSystem operatingSystem = null;

    @Before
    public void init(){
        SystemInfo systemInfo = new SystemInfo();
        operatingSystem = systemInfo.getOperatingSystem();
    }

}
