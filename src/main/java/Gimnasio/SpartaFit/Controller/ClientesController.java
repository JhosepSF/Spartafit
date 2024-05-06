package Gimnasio.SpartaFit.Controller;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Gimnasio.SpartaFit.Modelos.Clientes;
import Gimnasio.SpartaFit.Repositorio.ClientesRepositorio;
import Gimnasio.SpartaFit.Request.ClienteRequest;

@RestController
@RequestMapping("/apispartafit")
public class ClientesController
{
	@Autowired
	private ClientesRepositorio reClientes;
	
	@GetMapping("/clientes")
	public ResponseEntity<?> buscarTodos() 
	{
		return ResponseEntity.ok(reClientes.findAll());
	}
	
	@PostMapping("/clientes")
	public ResponseEntity<?> guardar(@RequestBody ClienteRequest request) 
	{
		try 
		{
			Clientes cliente = new Clientes();
			cliente.setDireccion(request.getDireccion());
			cliente.setCorreoElectronico(request.getCorreoElectronico());
			cliente.setIdCliente(request.getIdCliente());
			cliente.setNombre(request.getNombre());
			cliente.setTelefono(request.getTelefono());
			cliente.setTipoMembresia(request.getTipoMembresia());
			
			DateTimeFormatter formatear = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		    LocalDate fechainicio = LocalDate.parse(request.getFechaInicio(), formatear);
			cliente.setFechaInicio(Date.valueOf(fechainicio));
			
		    LocalDate fechavencimiento = LocalDate.parse(request.getFechaVencimiento(), formatear);
			cliente.setFechaVencimiento(Date.valueOf(fechavencimiento));
			
			reClientes.save(cliente);
			return ResponseEntity.ok("Se guardo al cliente correctaente");
		}
		catch(Exception e) 
		{
			return ResponseEntity.badRequest().body("No se pudo crear el cliente");
		}		
	}
	
	@SuppressWarnings("null")
	@PutMapping("/clientes")
	public ResponseEntity<?> modificar (@RequestBody ClienteRequest request)
	{
		try 
		{
			Clientes cliente = reClientes.findById(request.getIdCliente())
					.orElseThrow(()-> new RuntimeException("No se encontro al cliente"));
			
			cliente.setDireccion(request.getDireccion());
			cliente.setCorreoElectronico(request.getCorreoElectronico());
			cliente.setIdCliente(request.getIdCliente());
			cliente.setNombre(request.getNombre());
			cliente.setTelefono(request.getTelefono());
			cliente.setTipoMembresia(request.getTipoMembresia());
			
			DateTimeFormatter formatear = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		    LocalDate fechainicio = LocalDate.parse(request.getFechaInicio(), formatear);
			cliente.setFechaInicio(Date.valueOf(fechainicio));
			
		    LocalDate fechavencimiento = LocalDate.parse(request.getFechaVencimiento(), formatear);
			cliente.setFechaVencimiento(Date.valueOf(fechavencimiento));
			
			reClientes.save(cliente);
			return ResponseEntity.ok("Se modifico al cliente correctaente");
		}
		catch(Exception e) 
		{
			return ResponseEntity.badRequest().body("No se pudo modificar el cliente");
		}	
	}
	
	@SuppressWarnings("null")
	@GetMapping("/clientes/{idCliente}")
	public ResponseEntity<?> buscarId(@PathVariable String idCliente)
	{
		try 
		{
			return ResponseEntity.ok(reClientes.findById(idCliente));
		}
		catch(Exception e) 
		{
			return ResponseEntity.badRequest().body("No se pudo encontrar al cliente");
		}	
	}
	
	@SuppressWarnings("null")
	@DeleteMapping("/clientes/{idCliente}")
	public ResponseEntity<?> eliminar(@PathVariable String idCliente) 
	{
		try 
		{
			reClientes.deleteById(idCliente);
			return ResponseEntity.ok("Se borro al cliente correctaente");
		}
		catch(Exception e) 
		{
			return ResponseEntity.badRequest().body("No se pudo borrar el cliente");
		}	
	}
}
