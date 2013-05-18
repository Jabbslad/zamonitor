package zamonitor.process;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created with IntelliJ IDEA.
 * User: jabbslad
 * Date: 09/05/2013
 * Time: 22:15
 * To change this template use File | Settings | File Templates.
 */
public class ProcessMonitor implements Runnable {

    private int frequency;
    private ConcurrentHashMap<String, ArrayList<ProcessInfo>> stats;
    private Date tickTime;

    /**
     * Hide default constructor so it can never be called
     */
    private ProcessMonitor() {
        super();
    }

    public ProcessMonitor(ConcurrentHashMap<String, ArrayList<ProcessInfo>> stats) {
        this(1000, stats);
    }

    public ProcessMonitor(int frequency, ConcurrentHashMap<String, ArrayList<ProcessInfo>> stats) {
        this.frequency = frequency;
        this.stats = stats;
        this.tickTime = null;
    }

    @Override
    public void run() {

        try {
            while(true) {
                Process ps = Runtime.getRuntime().exec("ps -ec -o %cpu,%mem,comm");
                InputStreamReader sr = new InputStreamReader(ps.getInputStream());
                BufferedReader br = new BufferedReader(sr);
                String line;
                tickTime = new Date();
                while((line = br.readLine()) != null) {
                    parseLine(line);
                }
                Thread.sleep(frequency);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void parseLine(String line) {

        if (line.indexOf("%") != -1) {
            return;
        }

        ProcessInfo pi = new ProcessInfo();
        String val = "";

        val = line.substring(0, 5).trim();
        pi.setCpu(Double.parseDouble(val));

        val = line.substring(5, 10).trim();
        pi.setMemory(Double.parseDouble(val));

        val = line.substring(10, line.length()).trim();
        pi.setName(val);

        pi.setTime(tickTime);

        addToMap(pi);
    }

    private void addToMap(ProcessInfo pi) {

        String key = pi.getName();

        ArrayList<ProcessInfo> existingPiList = stats.get(key);

        if (existingPiList == null) {
            existingPiList = new ArrayList<>();
            existingPiList.add(pi);
            stats.putIfAbsent(key, existingPiList);
        } else {
            ProcessInfo piMultiple;
            piMultiple = existingPiList.get(existingPiList.size() - 1);
            if (piMultiple.getTime() == pi.getTime()) {
                piMultiple.setMemory(pi.getMemory() + piMultiple.getMemory());
                piMultiple.setCpu(pi.getCpu() + piMultiple.getCpu());
            } else {
                existingPiList.add(pi);
            }
        }

    }
}
