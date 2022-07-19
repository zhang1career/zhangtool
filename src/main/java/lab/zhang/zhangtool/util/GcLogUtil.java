package lab.zhang.zhangtool.util;

import java.lang.management.*;
import java.util.Map;
import javax.management.openmbean.CompositeData;
import javax.management.*;

import com.sun.management.GarbageCollectionNotificationInfo;
import com.sun.management.GcInfo;

/**
 * http://www.programcreek.com/java-api-examples/index.php?class=javax.management.MBeanServerConnection&method=addNotificationListener
 * https://docs.oracle.com/javase/8/docs/jre/api/management/extension/com/sun/management/GarbageCollectionNotificationInfo.html#GARBAGE_COLLECTION_NOTIFICATION
 *
 * @author zhangrj
 */
public class GcLogUtil {
    static public void startLoggingGc() {
        for (GarbageCollectorMXBean gcMbean : ManagementFactory.getGarbageCollectorMXBeans()) {
            try {
                ManagementFactory
                        .getPlatformMBeanServer()
                        .addNotificationListener(gcMbean.getObjectName(), LISTENER, null, null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    static private final NotificationListener LISTENER = (notification, handback) -> {
        if (notification.getType().equals(GarbageCollectionNotificationInfo.GARBAGE_COLLECTION_NOTIFICATION)) {
            CompositeData cd = (CompositeData) notification.getUserData();
            GarbageCollectionNotificationInfo gcNotificationInfo = GarbageCollectionNotificationInfo.from(cd);
            GcInfo gcInfo = gcNotificationInfo.getGcInfo();
            System.out.println(
                    "[GC] " + gcNotificationInfo.getGcAction() +
                    ", " + gcNotificationInfo.getGcName() +
                    ", duration: " + gcInfo.getDuration() + " ms" +
                    ", used: " + sumUsedMb(gcInfo.getMemoryUsageBeforeGc()) + " MB" +
                    " -> " + sumUsedMb(gcInfo.getMemoryUsageAfterGc()) + " MB");
        }
    };

    static private long sumUsedMb(Map<String, MemoryUsage> memUsages) {
        long sum = 0;
        for (MemoryUsage memoryUsage : memUsages.values()) {
            sum += memoryUsage.getUsed();
        }
        return sum / (1024 * 1024);
    }
}
