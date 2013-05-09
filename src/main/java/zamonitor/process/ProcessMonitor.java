package zamonitor.process;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created with IntelliJ IDEA.
 * User: jabbslad
 * Date: 09/05/2013
 * Time: 22:15
 * To change this template use File | Settings | File Templates.
 */
public class ProcessMonitor implements Runnable {

    private int frequency;

    public ProcessMonitor(int frequency) {
        this.frequency = frequency;
    }

    @Override
    public void run() {


        try {
            while(true) {
                Process ps = Runtime.getRuntime().exec("ps -eaf");
                InputStreamReader sr = new InputStreamReader(ps.getInputStream());
                BufferedReader br = new BufferedReader(sr);
                String line = null;
                while((line = br.readLine()) != null) {
                    System.out.println(line);
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
