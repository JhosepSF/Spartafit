package Gimnasio.SpartaFit.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Gimnasio.SpartaFit.Modelos.Clases;
import Gimnasio.SpartaFit.Modelos.ClasesEmpleados;
import Gimnasio.SpartaFit.Modelos.Empleados;
import Gimnasio.SpartaFit.Repositorio.ClasesEmpleadosRepositorio;
import Gimnasio.SpartaFit.Repositorio.ClasesRepositorio;
import Gimnasio.SpartaFit.Repositorio.EmpleadosRepositorio;
import Gimnasio.SpartaFit.Request.ClasesEmpleadosRequest;

@RestController
@RequestMapping("/apispartafit")
public class ClasesEmpleadosController
{
	@Autowired
	private ClasesEmpleadosRepositorio reClasesEmpleados;
	
	@Autowired
	private ClasesRepositorio reClases;
	
	@Autowired
	private EmpleadosRepositorio reEmpleados;
	
	@GetMapping("/clasesempleados")
	public ResponseEntity<?> buscarTodos() 
	{
		List<ClasesEmpleadosRequest> cedto = new ArrayList<>();
		List<ClasesEmpleados> ce = reClasesEmpleados.findAll();
		
		for(ClasesEmpleados emp : ce) 
		{
			String clase = emp.getIdClases() != null ? emp.getIdClases().getNombre() : null;
			String empleado = emp.getIdEmpleados() != null ? emp.getIdEmpleados().getNombre() : null;
			
			ClasesEmpleadosRequest dto = new ClasesEmpleadosRequest
					(
						emp.getIdClaseEmpleado(),
						clase,
						empleado
					);
			cedto.add(dto);
		}
		
		return ResponseEntity.ok(cedto);
	}
	
	@SuppressWarnings("null")
	@PostMapping("/clasesempleados")
	public ResponseEntity<?> guardar(@RequestBody ClasesEmpleadosRequest claseEmpleado)
	{
		try 
		{
			Clases cla = reClases.findById(Integer.parseInt(claseEmpleado.getIdClase()))
					.orElseThrow(()->new RuntimeException("Registro de la clase no encontrado"));

			Empleados emple = reEmpleados.findById(claseEmpleado.getIdEmpleado())
					.orElseThrow(()->new RuntimeException("Registro del empleado no encontrado"));
			
			ClasesEmpleados claEmp = new ClasesEmpleados();
			claEmp.setIdClases(cla);
			claEmp.setIdEmpleados(emple);
			reClasesEmpleados.save(claEmp);
			return ResponseEntity.ok("Se guardo el registro con exito");
		}
		catch (Exception e) 
		{
			return ResponseEntity.badRequest().body("El programa no pudo guardar el registro, el error es el siguiente: " + e.getMessage());
		}
	}
}
