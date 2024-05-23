package com.targetindia.targetready.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
//@ToString(exclude = "id")
//@Embeddable
@Entity
@Table(name = "recipient")
public class Recipient {
    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "name")
    private String rName;
    @Column(name = "address")
    private String rAddress;
    @Column(name = "contact")
    private String rContact;


    @OneToOne
    //@MapsId
    @JoinColumn(name = "id")
    private Message message;
    /*public String toString(){
        return "recipient: {\nname: " + getRName() +
                ",\naddress: " + getRAddress() +
                ",\ncontact: " + getRContact();
    }*/
}
