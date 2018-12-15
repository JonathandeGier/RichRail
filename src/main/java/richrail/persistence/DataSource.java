package richrail.persistence;

import richrail.service.TreinService;

public interface DataSource {

    public void saveTreinen();
    public void loadTreinen();

}
