package com.md.demo.crud;

import com.md.demo.model.Zipcode;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface iZipcodesCrud extends CrudRepository<Zipcode,Long> {

Optional<Zipcode> findByNr(Integer nr);


}
