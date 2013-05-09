package zamonitor;

import zamonitor.process.ProcessInfo;
import zamonitor.process.ProcessMonitor;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        ConcurrentHashMap<String, ProcessInfo> stats = new ConcurrentHashMap<String, ProcessInfo>();
        ProcessMonitor monitor = new ProcessMonitor(stats);
        Thread thread = new Thread(monitor);
        thread.start();

    }
}
