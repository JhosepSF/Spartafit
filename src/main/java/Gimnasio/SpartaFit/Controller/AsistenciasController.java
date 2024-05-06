package Gimnasio.SpartaFit.Controller;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Gimnasio.SpartaFit.Modelos.Asistencias;
import Gimnasio.SpartaFit.Modelos.ClasesEmpleados;
import Gimnasio.SpartaFit.Modelos.Clientes;
import Gimnasio.SpartaFit.Repositorio.AsistenciasRepositorio;
import Gimnasio.SpartaFit.Repositorio.ClasesEmpleadosRepositorio;
import Gimnasio.SpartaFit.Repositorio.ClientesRepositorio;
import Gimnasio.SpartaFit.Request.AsistenciasRequest;


@RestController
@RequestMapping("/apispartafit")
public class AsistenciasController 
{
	@Autowired
	private AsistenciasRepositorio reAsistencias;
	
	@Autowired
	private ClientesRepositorio reClientes;
	
	@Autowired
	private ClasesEmpleadosRepositorio reClasesEmpleados;
	
	@GetMapping("/asistencias")
	public ResponseEntity<?> buscarTodos()
	{
		List<Asistencias> asi = reAsistencias.findAll();
		List<AsistenciasRequest> asidto = new ArrayList<>();
		
		for(Asistencias asistencia : asi) 
		{
			String cliente = asistencia.getCliente() != null ? asistencia.getCliente().getNombre() : null;
			String ce = asistencia.getClaseEmpleado() != null ? asistencia.getClaseEmpleado().getIdClases().getNombre() 
																+ " - " + asistencia.getClaseEmpleado().getIdEmpleados().getNombre() : null;
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
	        String fecha = sdf.format(asistencia.getFecha());
	        
	        LocalTime horaActual = asistencia.getHora();
	        DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("HH:mm:ss");
	        String hora = horaActual.format(formatoHora);
	        
			AsistenciasRequest dto = new AsistenciasRequest
					(
						asistencia.getIdAsistencia(),
						fecha,
						hora,
						cliente,
						ce
					);
			asidto.add(dto);
		}
		
		return ResponseEntity.ok(asidto);
	}
	
	@SuppressWarnings("null")
	@PostMapping("/asistencias")
	public ResponseEntity<?> guardar(@RequestBody AsistenciasRequest asistencia)
	{
		try 
		{
			Clientes clie = reClientes.findById(asistencia.getIdCliente())
					.orElseThrow(()->new RuntimeException("Cliente no encontrado"));

			ClasesEmpleados claEmp = reClasesEmpleados.findById(Integer.valueOf(asistencia.getIdClaseEmpleado()))
					.orElseThrow(()->new RuntimeException("Registro de la ClaseXEmpleado no encontrado"));
			
			Asistencias Asistencias = new Asistencias();

			Asistencias.setCliente(clie);
			
			Asistencias.setClaseEmpleado(claEmp);

			Asistencias.setIdAsistencia(asistencia.getIdAsistencia());
			
			DateTimeFormatter formatear = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		    LocalDate fechapago = LocalDate.parse(asistencia.getFecha(), formatear);
		    Asistencias.setFecha(Date.valueOf(fechapago));
			
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
	        LocalTime hora = LocalTime.parse((CharSequence) asistencia.getHora(), formatter);
	        Asistencias.setHora(hora);
			
			reAsistencias.save(Asistencias);
			return ResponseEntity.ok("Se a guardado con exito la asistencia");
		}
		catch (Exception e) 
		{
			return ResponseEntity.badRequest().body("El programa no pudo registrar la asistencia, el error es el siguiente: " + e.getMessage());
		}
	}	
}
