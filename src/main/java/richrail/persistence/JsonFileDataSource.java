package richrail.persistence;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import richrail.service.TreinService;

import java.io.*;
import javax.json.*;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonFileDataSource extends FileDataSource {

    protected final File ioFile = new File(super.dirPath + "treinen.json");

//    private JsonArray parseTreinenToJson(List<Trein> treinen) {
//
//        JsonArrayBuilder masterArrayBuilder = Json.createArrayBuilder();
//
//        for (Trein trein : treinen) {
//
//            JsonObjectBuilder treinObjectBuilder = Json.createObjectBuilder();
//            treinObjectBuilder.add("naam", trein.getName());
//            JsonArrayBuilder treinComponentArrayBuilder = Json.createArrayBuilder();
//
//            for (RollingComponent component : trein) {
//
//                JsonObjectBuilder componentObjectBuilder = Json.createObjectBuilder();
//                componentObjectBuilder.add("naam", component.getName());
//                componentObjectBuilder.add("gewicht", component.getGewicht());
//                componentObjectBuilder.add("zitplaatsen", component.getNumberOfSeats());
//                componentObjectBuilder.add("type", component.getType().getTypeName());
//                componentObjectBuilder.add("typeWaarde", component.getType().getSpecialeWaarde());
//                treinComponentArrayBuilder.add(componentObjectBuilder);
//
//            }
//
//            treinObjectBuilder.add("componenten", treinComponentArrayBuilder);
//            masterArrayBuilder.add(treinObjectBuilder);
//
//        }
//
//        return masterArrayBuilder.build();
//    }

//    public List<Trein> parseJsonStringToTreinen(String jsonString) {
//        JsonReader reader = Json.createReader(new StringReader(jsonString));
//        JsonArray treinenArray = reader.readArray();
//        reader.close();
//
//        System.out.println(treinenArray.toString());
//
//        return null;
//    }

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
        //try {

            ObjectMapper jsonMapper = new ObjectMapper();
            checkForFile(); // Checkt of de file bestaat en maakt hem eventueel aan.
            //jsonMapper.writeValue(ioFile, service.getTreinen());

        /*
        } catch (JsonGenerationException exception) {
            System.out.println(exception.getMessage());
            exception.printStackTrace();
        } catch (JsonMappingException exception) {
            System.out.println(exception.getMessage());
            exception.printStackTrace();
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
            exception.printStackTrace();
        }*/

    }

    @Override
    public void loadTreinen(TreinService service) {

        try {
            FileReader inputFile = new FileReader(ioFile);
            JsonReader jsonReader = Json.createReader(inputFile);
            JsonArray treinenArray = jsonReader.readArray();
            jsonReader.close();
            inputFile.close();
            for (int i=0;i<treinenArray.size();i++) {
                JsonObject treinObject = treinenArray.getJsonObject(i);
            }

        } catch (IOException exception) {
            exception.printStackTrace();
        }

    }

}
