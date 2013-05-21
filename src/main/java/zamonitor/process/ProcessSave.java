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
 * Desc: Output process information to a file
 */
public class ProcessSave extends ProcessSaveLoad implements Runnable {

    private ProcessSave() {
        super();
    }

    public ProcessSave(ConcurrentHashMap<String, ArrayList<ProcessInfo>> stats) {
        super(stats, "ProcessOutput.txt");
    }

    public ProcessSave(ConcurrentHashMap<String, ArrayList<ProcessInfo>> stats, String fileName) {
        super(stats, fileName);
    }

    @Override
    public void run() {

        try {
            File file = new File(this.getFileName());

            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);

            NumberFormat df = DecimalFormat.getInstance();
            df.setMinimumFractionDigits(1);
            df.setMaximumFractionDigits(1);
            df.setRoundingMode(RoundingMode.DOWN);

            for (ArrayList<ProcessInfo> piList : this.getStats().values()) {
                for (ProcessInfo pi : piList) {
                    bw.write(pi.getTime().toString());
                    bw.write("###");
                    bw.write(df.format(pi.getCpu()).toString());
                    bw.write("###");
                    bw.write(df.format(pi.getMemory()).toString());
                    bw.write("###");
                    bw.write(pi.getName());
                    bw.write("\n");
                }
            }

            bw.flush();
            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
