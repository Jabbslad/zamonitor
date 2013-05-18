package zamonitor;

import zamonitor.process.ProcessInfo;
import zamonitor.process.ProcessLoad;
import zamonitor.process.ProcessMonitor;
import zamonitor.process.ProcessSave;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

public class App 
{
    public static void main( String[] args )
    {
        ConcurrentHashMap<String, ArrayList<ProcessInfo>> stats = new ConcurrentHashMap<>();
        ProcessMonitor monitor = new ProcessMonitor(stats);
        Thread monitorThread = new Thread(monitor);
        monitorThread.start();

        //ProcessSave saveStats = new ProcessSave(stats);
        //Thread saveThread = new Thread(saveStats);
        //saveThread.start();

        //ConcurrentHashMap<String, ArrayList<ProcessInfo>> oldStats = new ConcurrentHashMap<>();
        //ProcessLoad loadStats = new ProcessLoad(oldStats);
        //Thread loadThread = new Thread(loadStats);
        //loadThread.start();
    }
}
