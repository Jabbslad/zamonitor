package zamonitor.process;

import java.util.Date;

/**
 * User: jabbslad
 * Date: 09/05/2013
 * Time: 23:09
 * Hold process information
 */
public class ProcessInfo {
    private String name;
    private Date time;
    private double memory;
    private double cpu;

    /**
     * Get CPU as a percentage
     * @return
     */
    public double getCpu() {
        return cpu;
    }

    /**
     * Set CPU as a percentage
     * @param cpu
     */
    public void setCpu(double cpu) {
        this.cpu = cpu;
    }

    /**
     * Get Memory as a percentage
     * @return
     */
    public double getMemory() {
        return memory;
    }

    /**
     * Set Memory as a percentage
     * @param memory
     */
    public void setMemory(double memory) {
        this.memory = memory;
    }

    /**
     * Get name of the process
     * @return The process name
     */
    public String getName() {
        return name;
    }

    /**
     * Set name of the process
     * @param name The process name
     */
    public void setName(String name) {
        this.name = name;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
