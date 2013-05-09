package zamonitor.process;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
    private ConcurrentHashMap<String, ProcessInfo> stats;

    /**
     * Hide default constructor so it can never be called
     */
    private ProcessMonitor() {
        super();
    }

    public ProcessMonitor(ConcurrentHashMap<String, ProcessInfo> stats) {
        this(1000, stats);
    }

    public ProcessMonitor(int frequency, ConcurrentHashMap<String, ProcessInfo> stats) {
        this.frequency = frequency;
        this.stats = stats;
    }

    @Override
    public void run() {

        try {
            while(true) {
                Process ps = Runtime.getRuntime().exec("ps -ec -o %cpu,%mem,comm");
                InputStreamReader sr = new InputStreamReader(ps.getInputStream());
                BufferedReader br = new BufferedReader(sr);
                String line;
                while((line = br.readLine()) != null) {
                    System.out.println(line);
                    /**
                     * Todo: Parse line then stuff results into the 'stats' ConcurrentHashMap
                     */
                }
                Thread.sleep(frequency);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void parseLine(String line) {

        ProcessInfo pi = new ProcessInfo();
        pi.setName("");
    }
}
