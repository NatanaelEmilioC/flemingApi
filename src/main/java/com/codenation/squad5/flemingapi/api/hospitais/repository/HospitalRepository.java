package com.codenation.squad5.flemingapi.api.hospitais.repository;


import com.codenation.squad5.flemingapi.api.hospitais.model.Hospital;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HospitalRepository extends MongoRepository<Hospital, String> { 
		
	List<Hospital> findAll();
}
