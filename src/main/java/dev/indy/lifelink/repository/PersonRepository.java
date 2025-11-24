package dev.indy.lifelink.repository;

import dev.indy.lifelink.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {}
