package richrail.persistence;

import richrail.service.TreinService;
import richrail.service.TreinEventListener;

import javax.swing.JFileChooser;

public abstract class FileDataSource implements DataSource, TreinEventListener {

    protected final String dirPath = new JFileChooser().getFileSystemView().getDefaultDirectory().toString();
    protected TreinService treinService;

    protected FileDataSource(TreinService service) {
        treinService = service;
    }

    public abstract void saveTreinen();
    public abstract void loadTreinen();
    public abstract void update(String updateMessage);

}
