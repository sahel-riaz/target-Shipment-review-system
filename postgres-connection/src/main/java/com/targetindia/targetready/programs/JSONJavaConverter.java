package com.targetindia.targetready.programs;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.targetindia.targetready.Model.Message;
import com.targetindia.targetready.entity.Shipment;

import java.io.File;
import java.io.IOException;

public class JSONJavaConverter {
    public static Message getJSONtoJava(String fileName){
        ObjectMapper objectMapper = new ObjectMapper();
        Message msg;
        try{
            msg = objectMapper.readValue(new File(fileName), Message.class);
            //Shipment shipmentMsg = objectMapper.readValue(new File("shipmentMsg.json"), Shipment.class);
            System.out.println(msg);

            //System.out.println(shipmentMsg);
        }
        catch (JsonMappingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return msg;
    }
    public static Shipment getJSONtoJPA(String fileName){
        ObjectMapper objectMapper = new ObjectMapper();
        Shipment shipmentMsg;
        try{
            shipmentMsg = objectMapper.readValue(new File(fileName), Shipment.class);
            //Shipment shipmentMsg = objectMapper.readValue(new File("shipmentMsg.json"), Shipment.class);
            System.out.println(shipmentMsg);

            //System.out.println(shipmentMsg);
        }
        catch (JsonMappingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return shipmentMsg;
    }
    /*public static void main(String[] args) {
        ObjectMapper objectMapper = new ObjectMapper();
        try{
            Message msg = objectMapper.readValue(new File("shipmentMsg.json"), Message.class);
            //Shipment shipmentMsg = objectMapper.readValue(new File("shipmentMsg.json"), Shipment.class);
            System.out.println(msg);

            //System.out.println(shipmentMsg);
        }
         catch (JsonMappingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }*/
    public static void main(String[] args) {
        ObjectMapper objectMapper = new ObjectMapper();
        Shipment shipmentMsg;
        try{
            shipmentMsg = objectMapper.readValue(new File("shipmentMsg.json"), Shipment.class);
            //Shipment shipmentMsg = objectMapper.readValue(new File("shipmentMsg.json"), Shipment.class);
            System.out.println(shipmentMsg);

            //System.out.println(shipmentMsg);
        }
        catch (JsonMappingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
