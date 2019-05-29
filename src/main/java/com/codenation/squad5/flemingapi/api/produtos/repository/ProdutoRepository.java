package com.codenation.squad5.flemingapi.api.produtos.repository;


import com.codenation.squad5.flemingapi.api.produtos.model.Produtos;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository extends MongoRepository<Produtos, String> { 
		
	List<Produtos> findAll();
}
