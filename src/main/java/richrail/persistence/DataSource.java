package richrail.persistence;

import richrail.service.TreinService;

public interface DataSource {

    public void saveTreinen(TreinService service);
    public void loadTreinen(TreinService service);

}
