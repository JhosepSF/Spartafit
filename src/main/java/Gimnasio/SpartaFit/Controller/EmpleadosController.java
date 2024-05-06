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

import Gimnasio.SpartaFit.Modelos.Empleados;
import Gimnasio.SpartaFit.Repositorio.EmpleadosRepositorio;
import Gimnasio.SpartaFit.Request.EmpleadoRequest;

@RestController
@RequestMapping("/apispartafit")
public class EmpleadosController
{
	@Autowired
	private EmpleadosRepositorio reEmpleados;
	
	@GetMapping("/empleados")
	public ResponseEntity<?> buscarTodos()
	{
		return ResponseEntity.ok(reEmpleados.findAll());
	}
	
	@PostMapping("/empleados")
	public ResponseEntity<?> guardar(@RequestBody EmpleadoRequest request)
	{
		try 
		{
			Empleados empleado = new Empleados();
			empleado.setIdEmpleado(request.getIdEmpleado());
			empleado.setHorarioTrabajo(request.getHorarioTrabajo());
			empleado.setNombre(request.getNombre());
			empleado.setSalario(request.getSalario());
			
			DateTimeFormatter formatear = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		    LocalDate fecha = LocalDate.parse(request.getFechaContratacion(), formatear);
			empleado.setFechaContratacion(Date.valueOf(fecha));
			
			reEmpleados.save(empleado);
			return ResponseEntity.ok("Se guardo correctamente");
		}
		catch(Exception e)
		{
			return ResponseEntity.badRequest().body("No se pudo crear, por " + e.getMessage());
		}		
	}
	
	@SuppressWarnings("null")
	@PutMapping("/empleados")
	public ResponseEntity<?> modificar (@RequestBody EmpleadoRequest request) 
	{
		try 
		{
			Empleados empleado = reEmpleados.findById(request.getIdEmpleado())
					.orElseThrow(()-> new RuntimeException("No se encontro al empleado"));

			empleado.setHorarioTrabajo(request.getHorarioTrabajo());
			empleado.setNombre(request.getNombre());
			empleado.setSalario(request.getSalario());
			
			DateTimeFormatter formatear = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		    LocalDate fecha = LocalDate.parse(request.getFechaContratacion(), formatear);
			empleado.setFechaContratacion(Date.valueOf(fecha));
			
			reEmpleados.save(empleado);
			return ResponseEntity.ok("Se edito correctamente");
		}
		catch(Exception e)
		{
			return ResponseEntity.badRequest().body("No se pudo editar");
		}
	}
	
	@SuppressWarnings("null")
	@GetMapping("/empleados/{idEmpleado}")
	public ResponseEntity<?> buscarId(@PathVariable String idEmpleado)
	{
		return ResponseEntity.ok(reEmpleados.findById(idEmpleado));
	}
	
	@SuppressWarnings("null")
	@DeleteMapping("/empleados/{idEmpleado}")
	public ResponseEntity<?> eliminar(@PathVariable String idEmpleado) 
	{
		try 
		{
			reEmpleados.deleteById(idEmpleado);
			return ResponseEntity.ok("Se borro el empleado");
		}
		catch(Exception e) 
		{
			return ResponseEntity.badRequest().body("No se pudo eliminar el usuario");
		}
	}
}
