package richrail.persistence;

import richrail.service.TreinEventListener;
import richrail.service.TreinService;

import java.io.*;
import javax.json.*;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonFileDataSource extends FileDataSource implements TreinEventListener {

    private final File ioFile = new File(super.dirPath + "\\treinen.json");

    public JsonFileDataSource(TreinService service) {
        super(service);
    }

    private void processJsonInput(JsonArray array) {

        for (int i=0;i<array.size();i++) {

            JsonObject treinObject = array.getJsonObject(i);
            String treinNaam = treinObject.getString("name");
            this.treinService.newTrein(treinNaam);
            JsonArray componentenArray = treinObject.getJsonArray("componenten");

            for (int j=0;j<componentenArray.size();j++) {

                JsonObject componentObject = componentenArray.getJsonObject(j);
                String wagonNaam = componentObject.getString("name");
                int wagonGewicht = componentObject.getInt("gewicht");

                JsonObject typeObject = componentObject.getJsonObject("componentType");
                String typeNaam = typeObject.getString("typeName");
                int typeWaarde = typeObject.getInt("specialeWaarde");

                this.treinService.createRollingComponent(wagonNaam, wagonGewicht, typeNaam, typeWaarde);
                this.treinService.addComponentToTrain(treinNaam, wagonNaam);

            }

        }

    }

    private void checkForFile() {
        try {

            if (! ioFile.createNewFile()) {
                throw new IOException("File could not be created");
            }

        } catch (IOException exception) {
            System.out.println(exception.getMessage());
            exception.printStackTrace();
        }

    }

    @Override
    public void saveTreinen() {
        try {

            ObjectMapper jsonMapper = new ObjectMapper();
            checkForFile(); // Checkt of de file bestaat en maakt hem eventueel aan.
            jsonMapper.writeValue(ioFile, super.treinService.getAlleTreinen());

        } catch (IOException exception) {
            System.out.println(exception.getMessage());
            exception.printStackTrace();
        }

    }

    @Override
    public void loadTreinen() {

        try {
            FileReader inputFile = new FileReader(ioFile);
            JsonReader jsonReader = Json.createReader(inputFile);
            JsonArray treinenArray = jsonReader.readArray();
            jsonReader.close();
            inputFile.close();

            processJsonInput(treinenArray);

        } catch (IOException exception) {
            exception.printStackTrace();
        }

    }

    @Override
    public void update(String message) {
        saveTreinen();
    }

}
