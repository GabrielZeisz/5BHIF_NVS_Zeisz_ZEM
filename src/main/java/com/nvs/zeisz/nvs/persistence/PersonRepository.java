package com.nvs.zeisz.nvs.persistence;

import com.nvs.zeisz.nvs.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    Optional<Person> findByIdentifier(String identifier);
    void deleteByIdentifier(String identifier);
    Optional<Person> findByUsername(String username);
}
