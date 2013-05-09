package zamonitor.process;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: jabbslad
 * Date: 09/05/2013
 * Time: 23:09
 * To change this template use File | Settings | File Templates.
 */
public class ProcessInfo {
    private String name;
    private Date time;
    private long memory;
    private double cpu;

    public double getCpu() {
        return cpu;
    }

    public void setCpu(double cpu) {
        this.cpu = cpu;
    }

    public long getMemory() {
        return memory;
    }

    public void setMemory(long memory) {
        this.memory = memory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
