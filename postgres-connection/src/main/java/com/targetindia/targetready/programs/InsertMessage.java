package com.targetindia.targetready.programs;

import com.targetindia.targetready.entity.Message;
import com.targetindia.targetready.entity.PackageDetails;
import com.targetindia.targetready.entity.Recipient;
import com.targetindia.targetready.entity.Sender;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import com.targetindia.targetready.util.JpaUtil;

import java.util.List;

@Slf4j
public class InsertMessage {

    public static void main(String[] args) {

        try(EntityManager em = JpaUtil.createEntityManager();){
            MessageInput msgIp = new MessageInput();
            Message msg = msgIp.getMsg();
            Sender sender = msg.getSender();
            Recipient recipient = msg.getRecipient();
            List<PackageDetails> pd = msg.getPackageDetails();

            log.debug("Inserting the new Shipment Message {}", msg);

            var tx = em.getTransaction();
            try {
                tx.begin();
                em.persist(msg);
                //em.flush(); // Ensure the shipmentId is generated
                System.out.println("Message details inserted");
                em.persist(sender);
                System.out.println("Sender details inserted");

                em.persist(recipient);
                System.out.println("Recipient details inserted");

                for (PackageDetails pckDetails:  pd){
                    em.persist(pckDetails);
                }
                System.out.println("Pckg details inserted");

                tx.commit();
                System.out.println("New Shipment Details added successfully");
                log.debug("New Shipment Details added successfully");

            }
            catch(Exception e){
                log.error("Error while inserting new message", e);
                System.out.println(e);
                tx.rollback();
            }
        }

    }

}
