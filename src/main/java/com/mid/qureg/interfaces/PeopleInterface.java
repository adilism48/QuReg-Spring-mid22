package com.mid.qureg.interfaces;

import com.mid.qureg.models.People;
import org.springframework.data.repository.CrudRepository;

public interface PeopleInterface extends CrudRepository<People, Long> {
}
