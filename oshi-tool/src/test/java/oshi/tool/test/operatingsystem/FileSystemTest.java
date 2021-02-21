package oshi.tool.test.operatingsystem;

import org.junit.Test;
import oshi.SystemInfo;
import oshi.software.os.FileSystem;
import oshi.software.os.OSFileStore;
import oshi.software.os.OperatingSystem;

import java.util.List;

public class FileSystemTest extends OperaingBaseTest{

    @Test
    public void fileSystemtest01(){

        FileSystem fileSystem = operatingSystem.getFileSystem();

        List<OSFileStore> fileStores = fileSystem.getFileStores(true);

        System.out.println("name \t\t" +
                "volume \t\t" + "label \t\t" + "logicalVolume \t\t" + "mount \t\t" + "description \t\t" + "type \t\t" + "options \t\t" +
                "uuid \t\t" + "freeSpace \t\t" + "usableSpace \t\t" + "totalSpace \t\t" + "freeInodes \t\t" + "totalInodes \t\t");
        for (OSFileStore fileStore : fileStores) {
            String name = fileStore.getName();
            String volume = fileStore.getVolume();
            String label = fileStore.getLabel();
            String logicalVolume = fileStore.getLogicalVolume();
            String mount = fileStore.getMount();
            String description = fileStore.getDescription();
            String type = fileStore.getType();
            String options = fileStore.getOptions();
            String uuid = fileStore.getUUID();
            long freeSpace = fileStore.getFreeSpace();
            long usableSpace = fileStore.getUsableSpace();
            long totalSpace = fileStore.getTotalSpace();
            long freeInodes = fileStore.getFreeInodes();
            long totalInodes = fileStore.getTotalInodes();
            System.out.println(name + " \t\t" + volume + " \t\t" + label + " \t\t" + logicalVolume + " \t\t" + mount + " \t\t" + description + " \t\t" + type + " \t\t"
                    + options + " \t\t" + uuid + " \t\t" + freeSpace + " \t\t" + usableSpace + " \t\t" + totalSpace + " \t\t" + freeInodes + " \t\t" + totalInodes + " \t\t");
        }

    }
}
