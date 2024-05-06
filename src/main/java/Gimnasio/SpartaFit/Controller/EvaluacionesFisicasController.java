package Gimnasio.SpartaFit.Controller;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Gimnasio.SpartaFit.Modelos.Clientes;
import Gimnasio.SpartaFit.Modelos.EvaluacionesFisicas;
import Gimnasio.SpartaFit.Repositorio.ClientesRepositorio;
import Gimnasio.SpartaFit.Repositorio.EvaluacionesFisicasRepositorio;
import Gimnasio.SpartaFit.Request.EvaluacionesFisicasRequest;

@RestController
@RequestMapping("/apispartafit")
public class EvaluacionesFisicasController 
{
	@Autowired
	private EvaluacionesFisicasRepositorio reEvaluacionesFisicas;
	
	@Autowired
	private ClientesRepositorio reClientes;
	
	@GetMapping("/evaluacionesfisicas")
	public ResponseEntity<?> buscarTodos() 
	{
		List<EvaluacionesFisicasRequest> evadto = new ArrayList<>();
		List<EvaluacionesFisicas> eva = reEvaluacionesFisicas.findAll();
		
		for(EvaluacionesFisicas evaluacion : eva) 
		{
			String nombre = evaluacion.getCliente() != null ? evaluacion.getCliente().getNombre() : null;
	        
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
	        String fecha = sdf.format(evaluacion.getFechaEvaluacion());
	        
			
			EvaluacionesFisicasRequest dto  = new EvaluacionesFisicasRequest
					(
						evaluacion.getIdEvaluacionFisica(),
						nombre,
						fecha,
						evaluacion.getPeso(),
						evaluacion.getAltura(),
						evaluacion.getImc()
					);
			evadto.add(dto);
		}
		
		return ResponseEntity.ok(evadto);
	}
	
	@SuppressWarnings("null")
	@PostMapping("/evaluacionesfisicas")
	public ResponseEntity<?> guardar(@RequestBody EvaluacionesFisicasRequest evaluacionFisica)
	{
		try 
		{
			Clientes clie = reClientes.findById(evaluacionFisica.getIdCliente())
					.orElseThrow(()->new RuntimeException("Cliente no encontrado"));
			
			EvaluacionesFisicas EvaluacionesFisicas = new EvaluacionesFisicas();
			EvaluacionesFisicas.setCliente(clie);
			
			EvaluacionesFisicas.setIdEvaluacionFisica(evaluacionFisica.getIdEvaluacionFisica());
			
			DateTimeFormatter formatear = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		    LocalDate fechapago = LocalDate.parse(evaluacionFisica.getFechaEvaluacion(), formatear);
		    EvaluacionesFisicas.setFechaEvaluacion(Date.valueOf(fechapago));
		    
			EvaluacionesFisicas.setPeso(evaluacionFisica.getPeso());
			EvaluacionesFisicas.setAltura(evaluacionFisica.getAltura());
			EvaluacionesFisicas.setImc(evaluacionFisica.getImc());
			
			reEvaluacionesFisicas.save(EvaluacionesFisicas);
			
			return ResponseEntity.ok("Se guardo la evaluacion fisica con exito");
		}
		catch (Exception e) 
		{
			return ResponseEntity.badRequest().body("El programa no pudo registrar la evaluacion fisica, el error es el siguiente: " + e.getMessage());
		}
	}

	@GetMapping("/evaluacionesfisicas/{idCliente}")
	public ResponseEntity<?> buscarIdCliente(@PathVariable("idCliente") String idCliente){
		try 
		{
			@SuppressWarnings("null")
			Clientes clie = reClientes.findById(idCliente)
					.orElseThrow(()->new RuntimeException("Error al buscar al cliente"));

			return ResponseEntity.ok(reEvaluacionesFisicas.findByIdCliente(clie));
		}
		catch (Exception e) 
		{
			return ResponseEntity.badRequest().body("El programa no pudo encontrar la evaluacion fisica, el error es el siguiente: " + e.getMessage());
		}		
	}
}
