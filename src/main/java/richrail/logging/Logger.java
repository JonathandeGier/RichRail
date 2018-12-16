package richrail.logging;

import richrail.service.TreinEventListener;

import javax.swing.JFileChooser;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger implements TreinEventListener {

    private static final File filePath = new File( new JFileChooser().getFileSystemView().getDefaultDirectory().toString() + "\\RichRailIO\\treinen.log" );

    public Logger() {}

    public void update(String message) {
        String timeStamp = new SimpleDateFormat("[dd/MM/yyyy HH:mm:ss]").format(new Date()); // Neem de huidige tijd als string
        logMessage(timeStamp + " "+ message +"\n");
    }

    private void logMessage(String line) {
        try {
            BufferedWriter outputFile = new BufferedWriter( new FileWriter(filePath, true) );
            outputFile.write(line);
            outputFile.close();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

}
