package com.targetindia.targetready.programs;

import com.targetindia.targetready.entity.Location;
import com.targetindia.targetready.entity.Shipment;
import com.targetindia.targetready.entity.Package;
import com.targetindia.targetready.util.KeyboardUtil;

import java.util.ArrayList;
import java.util.List;

public class MessageInput {
/*
    public static Location getLocation() {
        System.out.println("Location Details: \n");
        var name = KeyboardUtil.getString("Name: ");
        var address = KeyboardUtil.getString("Address: ");
        var contact = KeyboardUtil.getString("Contact: ");
        Location location = new Location();
        Shipment msg = new Shipment();
        location.setName(name);
        location.setAddress(address);
        location.setContact(contact);
        return location;
    }
    public static List<Package> getPackage() {
        System.out.println("Package Details: \n");
        List<Package> pkgDetails = new ArrayList<>();
        var id = KeyboardUtil.getString("Id: ");
        Shipment msg = new Shipment();
        while (id != null && !id.isEmpty()) {
            var dcp = KeyboardUtil.getString("Description: ");
            var weight = KeyboardUtil.getDouble("Weight: ");
            var length = KeyboardUtil.getInt("Length: ");
            var width = KeyboardUtil.getInt("Width: ");
            var height = KeyboardUtil.getInt("Height: ");

            Package pd = new Package();
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
    public static Shipment getMsg() {
        Shipment msg = new Shipment();
        System.out.println("Enter Shipment Message Details: ");
        var id = KeyboardUtil.getString("Shipment Id: ");

        msg.setShipmentId(id);


        Location sender = getLocation();
        Location recipient = getLocation();
        //Recipient recipient  = getRecipient();
        List<Package> packages = getPackage();

        sender.setShipmentId(id);
        recipient.setShipmentId(id);

        sender.setIdentity("Sender");
        recipient.setIdentity("Recipient");

        for (Package pckg: packages){
            pckg.setShipmentId(id);
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



        msg.setLocation(sender);


       //msg.setRecipient(recipient);
        msg.setPackages(packages);




        return msg;
    }
*/

}
