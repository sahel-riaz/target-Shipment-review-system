package com.postgresql.kafkaWithPostgre;


import com.postgresql.kafkaWithPostgre.entity.Person;
import com.postgresql.kafkaWithPostgre.repo.PersonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonController {

    @Autowired
    PersonRepo repo;

    @PostMapping("/addPerson")
    public void addPerson(@RequestBody Person person) {
        repo.save(person);
    }
}

