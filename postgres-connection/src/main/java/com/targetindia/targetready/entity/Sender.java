package com.targetindia.targetready.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
//@ToString(exclude = "id")
//@Embeddable
@Table(name = "sender")
public class Sender {
    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "name")
    private String sName;
    @Column(name = "address")
    private String sAddress;
    @Column(name = "contact")
    private String sContact;


    @OneToOne
    @JoinColumn(name = "id")
    private Message message;

    /*public String toString(){
        return "sender: {\nname: " + getSName() +
                ",\naddress: " + getSAddress() +
                ",\ncontact: " + getSContact();
    }*/

}
