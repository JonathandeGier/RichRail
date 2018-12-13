package richrail.persistence;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import richrail.service.TreinService;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import javax.json.*;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonFileDataSource extends FileDataSource {

    protected final File ioFile = new File(super.dirPath + "treinen.json");


    private void processJsonInput(JsonArray array, TreinService service) {

        for (int i=0;i<array.size();i++) {

            JsonObject treinObject = array.getJsonObject(i);
            String treinNaam = treinObject.getString("name");
            service.newTrein(treinNaam);
            JsonArray componentenArray = treinObject.getJsonArray("componenten");

            for (int j=0;j<componentenArray.size();j++) {

                JsonObject componentObject = componentenArray.getJsonObject(j);
                String wagonNaam = componentObject.getString("name");
                int wagonGewicht = componentObject.getInt("gewicht");

                JsonObject typeObject = componentObject.getJsonObject("componentType");
                String typeNaam = typeObject.getString("typeName");
                int typeWaarde = typeObject.getInt("specialeWaarde");

                service.createRollingComponent(wagonNaam, wagonGewicht, typeNaam, typeWaarde);
                service.addComponentToTrain(treinNaam, wagonNaam);

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
    public void saveTreinen(TreinService service) {
        try {

            ObjectMapper jsonMapper = new ObjectMapper();
            checkForFile(); // Checkt of de file bestaat en maakt hem eventueel aan.
            jsonMapper.writeValue(ioFile, service.getAlleTreinen());

        } catch (IOException exception) {
            System.out.println(exception.getMessage());
            exception.printStackTrace();
        }

    }

    @Override
    public void loadTreinen(TreinService service) {

        try {
            FileReader inputFile = new FileReader(ioFile);
            JsonReader jsonReader = Json.createReader(inputFile);
            JsonArray treinenArray = jsonReader.readArray();
            jsonReader.close();
            inputFile.close();

            processJsonInput(treinenArray, service);

        } catch (IOException exception) {
            exception.printStackTrace();
        }

    }

}
