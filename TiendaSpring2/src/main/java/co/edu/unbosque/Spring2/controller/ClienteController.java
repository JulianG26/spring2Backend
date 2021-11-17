package co.edu.unbosque.Spring2.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unbosque.Spring2.model.Cliente;
import co.edu.unbosque.Spring2.repository.ClienteRepository;

@CrossOrigin(origins = "http://localhost:3001")
@RestController
@RequestMapping("/clientes")

public class ClienteController {


		@Autowired
		ClienteRepository clienteRepo;

		@PostMapping("/save")
		  public ResponseEntity<Cliente> createCliente(@RequestBody Cliente cliente) {
			  try {
				    Cliente _cliente = clienteRepo.save(new Cliente(cliente.getCedula(), cliente.getNombre_completo(), cliente.getDireccion(), cliente.getTelefono(), cliente.getCorreo()));
				    return new ResponseEntity<>(_cliente, HttpStatus.CREATED);
				  } catch (Exception e) {
				    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
				  }
		  }

		 @GetMapping("/getall")
		  public ResponseEntity<List<Cliente>> getAllClientes(@RequestParam(required = false) Long cedula) {
			  try {
				    List<Cliente> clientes = new ArrayList<>();

				    if (cedula == null)
				      clienteRepo.findAll().forEach(clientes::add);
				    else
				      clienteRepo.findByCedula(cedula).forEach(clientes::add);

				    if (clientes.isEmpty()) {
				      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
				    }

				    return new ResponseEntity<>(clientes, HttpStatus.OK);
				  } catch (Exception e) {
				    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
				  }
		 }

		 @GetMapping("/getby/{cedula}")
		  public ResponseEntity<Cliente> getClienteByCedula(@PathVariable("cedula") Long cedula) {
			  Optional<Cliente> clienteData = clienteRepo.findById(cedula);

			  if (clienteData.isPresent()) {
			    return new ResponseEntity<>(clienteData.get(), HttpStatus.OK);
			  } else {
			    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			  }
		  }

		  @PutMapping("/update/{cedula}")
		  public ResponseEntity<Cliente> updateCliente(@PathVariable("cedula") Long cedula, String nombre_completo, String direccion, String telefono, String correo, @RequestBody Cliente cliente) {
			  Optional<Cliente> clienteData = clienteRepo.findById(cedula);

			  if (clienteData.isPresent()) {
			    Cliente _cliente = clienteData.get();
			    _cliente.setNombre_completo(cliente.getNombre_completo());
			    _cliente.setDireccion(cliente.getDireccion());
			    _cliente.setTelefono(cliente.getTelefono());
			    _cliente.setCorreo(cliente.getCorreo());
			    return new ResponseEntity<>(clienteRepo.save(_cliente), HttpStatus.OK);
			  } else {
			    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			  }

		  }

		  @DeleteMapping("/delete/{cedula}")
		  public ResponseEntity<HttpStatus> deleteCliente(@PathVariable("cedula") Long cedula) {
			  try {
				    clienteRepo.deleteByCedula(cedula);
				    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
				  } catch (Exception e) {
				    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
				  }
		  }

		  @DeleteMapping("/deleteall")
		  public ResponseEntity<HttpStatus> deleteAllProducto() {
			  try {
				    clienteRepo.deleteAll();
				    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
				  } catch (Exception e) {
				    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
				  }
	}
	}
