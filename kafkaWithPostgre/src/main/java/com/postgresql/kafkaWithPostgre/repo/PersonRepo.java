package com.postgresql.kafkaWithPostgre.repo;

import com.postgresql.kafkaWithPostgre.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource
public interface PersonRepo extends JpaRepository<Person,Long> {

}
