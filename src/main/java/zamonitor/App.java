package zamonitor;

import zamonitor.process.ProcessInfo;
import zamonitor.process.ProcessMonitor;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        ConcurrentHashMap<String, ArrayList<ProcessInfo>> stats = new ConcurrentHashMap<>();
        ProcessMonitor monitor = new ProcessMonitor(stats);
        Thread thread = new Thread(monitor);
        thread.start();
    }
}
