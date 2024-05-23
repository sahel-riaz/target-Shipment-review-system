package com.targetindia.targetready.programs;

import com.targetindia.targetready.entity.Message;
import com.targetindia.targetready.entity.PackageDetails;
import com.targetindia.targetready.entity.Recipient;
import com.targetindia.targetready.entity.Sender;
import com.targetindia.targetready.util.KeyboardUtil;

import java.util.ArrayList;
import java.util.List;

public class MessageInput {
    public static Sender getSender() {
        System.out.println("Sender Details: \n");
        var name = KeyboardUtil.getString("Name: ");
        var address = KeyboardUtil.getString("Address: ");
        var contact = KeyboardUtil.getString("Contact: ");
        Sender sender = new Sender();
        Message msg = new Message();
        sender.setSName(name);
        sender.setSAddress(address);
        sender.setSContact(contact);
        return sender;
    }
    public static Recipient getRecipient() {
        System.out.println("Recipient Details: \n");
        var name = KeyboardUtil.getString("Name: ");
        var address = KeyboardUtil.getString("Address: ");
        var contact = KeyboardUtil.getString("Contact: ");
        Recipient recipient = new Recipient();
        Message msg = new Message();
        recipient.setRName(name);
        recipient.setRAddress(address);
        recipient.setRContact(contact);
        return recipient;
    }
    public static List<PackageDetails> getPackage() {
        System.out.println("Package Details: \n");
        List<PackageDetails> pkgDetails = new ArrayList<>();
        var id = KeyboardUtil.getString("Id: ");
        Message msg = new Message();
        while (id != null && !id.isEmpty()) {
            var dcp = KeyboardUtil.getString("Description: ");
            var weight = KeyboardUtil.getDouble("Weight: ");
            var length = KeyboardUtil.getInt("Length: ");
            var width = KeyboardUtil.getInt("Width: ");
            var height = KeyboardUtil.getInt("Height: ");

            PackageDetails pd = new PackageDetails();
            pd.setPackageId(id);
            pd.setDescription(dcp);
            pd.setWeight(weight);
            pd.setLength(length);
            pd.setWidth(width);
            pd.setHeight(height);
            pkgDetails.add(pd);
            id = KeyboardUtil.getString("Id: ");
        }
        return pkgDetails;
    }
    public static Message getMsg() {
        Message msg = new Message();
        System.out.println("Enter Shipment Message Details: ");
        var id = KeyboardUtil.getString("Shipment Id: ");

        msg.setShipmentId(id);
        msg.setShipmentId(id);


        Sender sender = getSender();
        Recipient recipient  = getRecipient();
        List<PackageDetails> packageDetails = getPackage();

        sender.setId(id);
        recipient.setId(id);
        for (PackageDetails pckg: packageDetails){
            pckg.setShipmentid(id);
        }


        var shpMethod = KeyboardUtil.getString("Shipment Method: ");
        msg.setShippingMethod(shpMethod);

        var deliverDate = KeyboardUtil.getString("Delivery Date: ");
        msg.setDeliveryDate(deliverDate);

        var cost = KeyboardUtil.getInt("Total Cost: ");
        msg.setCost(cost);

        var currency = KeyboardUtil.getString("Currency: ");
        msg.setCurrency(currency);

        var status = KeyboardUtil.getString("Status: ");
        msg.setStatus(status);



        msg.setSender(sender);
        msg.setRecipient(recipient);
        msg.setPackageDetails(packageDetails);




        return msg;
    }

}
