package zamonitor.process;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

/**
 * User: psampson
 * Date: 10/05/2013
 * Time: 22:12
 * Desc: Base class for saving or loading ProcessInfo
 */
public class ProcessSaveLoad {

    private String fileName;
    private ConcurrentHashMap<String, ArrayList<ProcessInfo>> stats;

    /**
     * Get the file name
     * @return The file name
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * Set the file name
     * @param fileName The file name
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Get the map of ProcessInfo
     * @return The map of ProcessInfo
     */
    public ConcurrentHashMap<String, ArrayList<ProcessInfo>> getStats() {
        return stats;
    }

    /**
     * Set the map of ProcessInfo
     * @param stats The map of ProcessInfo
     */
    public void setStats(ConcurrentHashMap<String, ArrayList<ProcessInfo>> stats) {
        this.stats = stats;
    }

    protected ProcessSaveLoad() {
        // Intentionally private and empty
    }

    protected ProcessSaveLoad(ConcurrentHashMap<String, ArrayList<ProcessInfo>> stats) {
        this(stats, "ProcessOutput.txt");
    }

    protected ProcessSaveLoad(ConcurrentHashMap<String, ArrayList<ProcessInfo>> stats, String fileName) {
        this.stats = stats;
        this.fileName = fileName;
    }
}
