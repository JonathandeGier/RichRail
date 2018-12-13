package richrail.persistence;

import richrail.service.TreinService;

import javax.swing.JFileChooser;

public abstract class FileDataSource implements DataSource {

    protected final String dirPath = new JFileChooser().getFileSystemView().getDefaultDirectory().toString();

    public abstract void saveTreinen(TreinService service);
    public abstract void loadTreinen(TreinService serivce);

}
