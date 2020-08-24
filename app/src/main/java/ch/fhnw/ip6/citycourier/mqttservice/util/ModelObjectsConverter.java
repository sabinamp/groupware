package ch.fhnw.ip6.citycourier.mqttservice.util;
import ch.fhnw.ip6.citycourier.model.Courier;
import ch.fhnw.ip6.citycourier.model.CourierInfo;
import ch.fhnw.ip6.citycourier.model.DeliveryStep;
import ch.fhnw.ip6.citycourier.model.OrderDescriptiveInfo;
import ch.fhnw.ip6.citycourier.model.OrderInfo;
import ch.fhnw.ip6.citycourier.model.TaskRequest;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.*;
import java.util.HashMap;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;


public class ModelObjectsConverter {
    private static Logger logger;
    private static final ObjectMapper objectMapper;

    private static ClassLoader classLoader;

    static {
        objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, false);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.registerModule(new JavaTimeModule());
        logger= LogManager.getLogManager().getLogger(String.valueOf(ModelObjectsConverter.class));
        classLoader = ClassLoader.getSystemClassLoader();
    }




    public static  <K, T> HashMap<K, T> readObjects(String folderName, Class<T> clazz, Function<T, K> keyExtractor) {
        try {
            return readObjectsImpl(folderName, clazz, keyExtractor);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static  <K, T> HashMap<K, T> readObjectsImpl(String folderName, Class<T> clazz, Function<T, K> keyExtractor) {

        try{
            URL path = classLoader.getResource(folderName);
            if(path != null){
                File folder  = new File(path.getFile());

                File[] files = folder.listFiles();
                HashMap<K, T> objectsMap = new HashMap<>();
                if(files != null){
                    for (File file : files) {
                        T object = objectMapper.readValue(file, clazz);
                        objectsMap.put(keyExtractor.apply(object), object);

                    }
                    return objectsMap;
                }   else{
                    logger.log(Level.INFO, "files is null");
                }

            }else{
                System.out.println("the folder name doesn't exist.");
            }


        }catch(NullPointerException e){
            e.printStackTrace();
            logger.info("NullPointerException caught "+ e.getMessage());
        }
        catch(IOException e){
            e.printStackTrace();
            logger.info("IOException caught "+ e.getMessage());
        }
        return null;
    }

    //read from JSON
    public static <T> T readJSONObject(String folderName, String objectId, Class<T> clazz) {

        try {
            URL path = classLoader.getResource(folderName + "/" + objectId + ".json");
            if(path == null){
                System.out.print("Order id: "+objectId+ "or the given folder name doesn't exist.");
            }else{
                String ab=  path.getFile();
                File file  = new File(ab);

                return objectMapper.readValue(file, clazz);

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    //read from JSON
    private static JsonNode readJSONNode(String folderName, String objectId, String nodePath) {

       try {
           URL path = classLoader.getResource(folderName + "/" + objectId + ".json");
           if(path != null){
               String ab= path.getFile();
               File file  = new File(ab);
               // if(file != null){
               JsonNode root= objectMapper.readValue(file, JsonNode.class);
               return root.at(nodePath);

           } else{
              logger.info("readJSONNode(" +folderName+" " +objectId+ " "+nodePath +"the json file is null");
           }

        }
       catch (NullPointerException | IOException e) {
           e.printStackTrace();
       }
        return null;
    }

    //write to JSON
    public static boolean writeJSONObject(String folderName, String objectId, String jsonValue) {

        try {
            URL path = classLoader.getResource(folderName + "/" + objectId + ".json");
            if(path != null){
                File file  = new File(path.getFile());
                JsonNode tree= objectMapper.readTree(jsonValue);
                objectMapper.writeValue(file, tree);
                return true;
            }else{
                System.out.println("the given folderName or objectId is wrong");
            }

        }catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    //write new Order to JSON
    public static boolean writeNewOrderToJSON(String folderName, String orderId, OrderDescriptiveInfo order) {
        try {
            URL path = classLoader.getResource(folderName);
            if (path != null) {
                File source = new File(path.getFile());
                Path newFilePath = Paths.get(source + "/" + orderId + ".json");
                File file = newFilePath.toFile();
                if(!file.exists()){
                    Path filePath = Files.createFile(newFilePath);
                    file = filePath.toFile();
                }

                JsonNode tree= objectMapper.valueToTree(order);
                objectMapper.writeValue(file, tree);
                return true;
            }
           else{
               System.out.println("the given folder name is wrong. The folder "+folderName+" doesn't exist.");
            }

        } catch (InvalidPathException e) {
            e.printStackTrace();
            System.out.println(e.getReason());
        }catch (FileAlreadyExistsException e) {
            e.printStackTrace();
            System.out.println(e.getReason());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return false;

    }

    public static boolean writeOrderToJSON(String folderName, String orderId, OrderDescriptiveInfo order) {

        try {
            URL path = classLoader.getResource(folderName + "/" + orderId + ".json");
            if (path != null) {
                File file  = new File(path.getFile());
                if(order != null){
                    JsonNode tree= objectMapper.valueToTree(order);
                    objectMapper.writeValue(file, tree);
                    return true;
                }
            }else{
                System.out.println("wrong folder name or orderId");
            }

        }
        catch (InvalidPathException e) {
            e.printStackTrace();
            System.out.println(e.getReason());
        }catch (NullPointerException ex){
            ex.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return false;
    }





    public static DeliveryStep convertDeliveryStepData(String jsonValue){
        try {
            if(jsonValue != null && !jsonValue.isEmpty()){
                System.out.println("delivery step to be converted "+ jsonValue);
                return objectMapper.readValue(jsonValue, DeliveryStep.class);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return  null;
    }

   public static OrderInfo convert(String jsonString){

       try {
          return objectMapper.readValue(jsonString, OrderInfo.class);

       } catch (Exception e) {
           e.printStackTrace();
       }
       return  null;
   }

    public static OrderDescriptiveInfo convertJsonToOrderDescriptiveInfo(String jsonString) {

        try {
              if(jsonString != null && !jsonString.isEmpty()){
              return objectMapper.readValue(jsonString, OrderDescriptiveInfo.class);

            }
        }
        catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return  null;
    }

    public static Courier convertJsonToCourier(String jsonString) {

        try {
          if(jsonString != null && !jsonString.isEmpty()){
               return objectMapper.readValue(jsonString, Courier.class);
            }
        }catch (JsonParseException | JsonMappingException e) {
            e.printStackTrace();
            logger.log(Level.WARNING, e.getMessage());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return  null;
    }

    public static String convertToJSON(Object obj) {
           JsonNode tree= objectMapper.valueToTree(obj);
           return tree.toPrettyString();
    }

    public static TaskRequest convertJsonToTaskRequest(String jsonString) {

        try {
            if(jsonString != null && !jsonString.isEmpty()){
               return objectMapper.readValue(jsonString, TaskRequest.class);
            }else{
                logger.log(Level.INFO, "convertJsonToTaskRequest -the jsonString param is null");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  null;
    }


    public static OrderInfo convertJsonToOrderInfo(String jsonString) {
      try {
            if(jsonString != null && !jsonString.isEmpty()){
               return objectMapper.readValue(jsonString, OrderInfo.class);
            }
        }catch (Exception e) {
            e.printStackTrace();
            logger.log(Level.WARNING, e.getMessage());
        }
        return null;
    }

    public static CourierInfo convertJsonToCourierInfo(String jsonString) {
        try {
            if(jsonString != null && !jsonString.isEmpty()){
                return objectMapper.readValue(jsonString, CourierInfo.class);
            }
        }catch (Exception e) {
            e.printStackTrace();
            logger.log(Level.WARNING, e.getMessage());
        }
        return null;
    }
}
