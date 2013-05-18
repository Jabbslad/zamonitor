package zamonitor.process;

import javafx.stage.FileChooser;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;

/**
 * User: zammalad
 * Date: 12/05/2013
 * Time: 18:32
 * Desc:
 */
public class ProcessLoad extends ProcessSaveLoad implements Runnable {

    private ProcessLoad() {
        super();
    }

    public ProcessLoad(ConcurrentHashMap<String, ArrayList<ProcessInfo>> stats) {
        super(stats);
    }

    @Override
    public void run() {

        try {

            FileChooser fc = new FileChooser();

            FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("PSO files (*.pso)", "*.pso");
            fc.getExtensionFilters().add(extensionFilter);

            fc.setTitle("Open Dialog");

            String startPath = System.getProperty("user.home");
            File startDir = new File(startPath);

            fc.setInitialDirectory(startDir);

            //File chosenFile = fc.showOpenDialog(null);

            //if (chosenFile != null) {
                //FileInputStream fis = new FileInputStream(chosenFile.getPath());
                FileInputStream fis = new FileInputStream(this.getFileName());
                DataInputStream dis = new DataInputStream(fis);
                BufferedReader br = new BufferedReader(new InputStreamReader(dis));
                String strLine;

                while ((strLine = br.readLine()) != null)   {
                    addToMap(strLine);
                }

                br.close();
                dis.close();
                fis.close();
            //} else {
            //    System.out.println("Load Error: No file selected");
            //}

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void addToMap(String strLine) {

        final SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM d HH:mm:ss zzz yyyy");

        String[] procInfo = strLine.split("###");
        Date dateTime;

        try {
            dateTime = sdf.parse(procInfo[0]);
        } catch (ParseException e) {
            System.out.println("Unable to parse date string: " + procInfo[0]);
            dateTime = null;
        }

        ProcessInfo pi = new ProcessInfo();

        pi.setTime(dateTime);
        pi.setCpu(Double.parseDouble(procInfo[1]));
        pi.setMemory(Double.parseDouble(procInfo[2]));
        pi.setName(procInfo[3]);

        String key = pi.getName();

        ArrayList<ProcessInfo> existingPiList = this.getStats().get(key);

        if (existingPiList == null) {
            existingPiList = new ArrayList<>();
            existingPiList.add(pi);
            this.getStats().putIfAbsent(key, existingPiList);
        } else {
            existingPiList.add(pi);
        }

    }
}
