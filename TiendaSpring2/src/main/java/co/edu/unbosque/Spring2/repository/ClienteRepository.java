package co.edu.unbosque.Spring2.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import co.edu.unbosque.Spring2.model.Cliente;

public interface ClienteRepository extends MongoRepository<Cliente, Long> {

	List<Cliente> findByCedula(Long cedula);
	
	List<Cliente> deleteByCedula(Long cedula);
}
