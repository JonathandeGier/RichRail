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

    private void checkForFile() {
        try {

            if (filePath.getParentFile().mkdirs()) {
                System.out.println("Directories aangemaakt");
            } else {
                System.out.println("Directories gevonden");
            }

            if (filePath.createNewFile()) {
                System.out.println("File aangemaakt");
            } else {
                System.out.println("File gevonden");
            }

        } catch (IOException exception) {
            exception.printStackTrace();
        }

    }

    private void logMessage(String line) {
        try {
            checkForFile();
            BufferedWriter outputFile = new BufferedWriter( new FileWriter(filePath, true) );
            outputFile.write(line);
            outputFile.close();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

}
