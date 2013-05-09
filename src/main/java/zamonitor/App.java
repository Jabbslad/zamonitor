package zamonitor;

import zamonitor.process.ProcessMonitor;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {

        ProcessMonitor monitor = new ProcessMonitor(1000);
        Thread thread = new Thread(monitor);
        thread.start();

    }
}
