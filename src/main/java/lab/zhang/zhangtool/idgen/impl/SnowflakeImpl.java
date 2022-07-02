package lab.zhang.zhangtool.idgen.impl;

import lab.zhang.zhangtool.idgen.Idgen;

/**
 * @author zhangrj
 */
public class SnowflakeImpl implements Idgen {

    /**
     * 2020-01-01 00:00:00
     */
    static private final long START_TIME = 1577836800000L;

    static private final long RAND_MAX = 16;

    private final static long DATACENTER_BIT = 4;
    private final static long MACHINE_BIT = 4;
    private final static long REBOOT_BIT = 4;
    private final static long SEQUENCE_BIT = 10;

    private final static long DATACENTER_MAX = ~(-1L << DATACENTER_BIT);
    private final static long MACHINE_MAX = ~(-1L << MACHINE_BIT);
    private final static long REBOOT_MAX = ~(-1L << REBOOT_BIT);
    private final static long SEQUENCE_MAX = ~(-1L << SEQUENCE_BIT);

    private final static long REBOOT_SHIFT = SEQUENCE_BIT;
    private final static long MACHINE_SHIFT = REBOOT_SHIFT + REBOOT_BIT;
    private final static long DATACENTER_SHIFT = MACHINE_SHIFT + MACHINE_BIT;
    private final static long TIMESTMP_SHIFT = DATACENTER_SHIFT + DATACENTER_BIT;

    private final long datacenterId;
    private final long machineId;
    private final long rebootId;
    private Long sequence;
    private long lastTime = -1L;

    public SnowflakeImpl(long datacenterId, long machineId, long rebootId) {
        if (datacenterId > DATACENTER_MAX || datacenterId < 0) {
            throw new IllegalArgumentException("The datacenterId should be in [0, " + DATACENTER_MAX + "]");
        }
        if (machineId > MACHINE_MAX || machineId < 0) {
            throw new IllegalArgumentException("The machineId should be in [0, " + MACHINE_MAX + "]");
        }
        if (rebootId > REBOOT_MAX || rebootId < 0) {
            throw new IllegalArgumentException("The rebootId should be in [0, " + REBOOT_MAX + "]");
        }
        this.datacenterId = datacenterId;
        this.machineId = machineId;
        this.rebootId = rebootId;
    }

    @Override
    public synchronized Long genId() {
        long currentTime = getCurrentTime();
        sequence = getSequence(currentTime);
        if (sequence == null) {
            return null;
        }

        return (currentTime - START_TIME) << TIMESTMP_SHIFT
                | datacenterId << DATACENTER_SHIFT
                | machineId << MACHINE_SHIFT
                | rebootId << REBOOT_SHIFT
                | sequence;
    }

    private Long getSequence(long currentTime) {
        if (currentTime < lastTime) {
            throw new RuntimeException("Clock moved backwards. Refuse to generate id");
        }

        if (currentTime > lastTime) {
            lastTime = currentTime;
            return 0L;
        }

        long incrSequence = incr(sequence);
        if (incrSequence > SEQUENCE_MAX) {
            return null;
        }
        return incrSequence;
    }

    private long getCurrentTime() {
        return System.currentTimeMillis();
    }

    private long incr(long sequence) {
        return sequence + +(long) (Math.random() * RAND_MAX);
    }
}
