package Gimnasio.SpartaFit.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Gimnasio.SpartaFit.Modelos.Clases;
import Gimnasio.SpartaFit.Repositorio.ClasesRepositorio;

@RestController
@RequestMapping("/apispartafit")
public class ClasesController 
{
	@Autowired
	private ClasesRepositorio reClases;
	
	@GetMapping("/clases")
	public ResponseEntity<?> buscarTodos() {
		return ResponseEntity.ok(reClases.findAll());
	}
	
	@PostMapping("/clases")
	public ResponseEntity<?> guardar(@RequestBody @NonNull Clases clase) 
	{
		try 
		{
			reClases.save(clase);
			return ResponseEntity.ok("Se guardo correctamente");
		}
		catch(Exception e) 
		{
			return ResponseEntity.badRequest().body("Hubo un error al crear la clase");
		}
	}
	
	@SuppressWarnings("null")
	@PutMapping("/clases")
	public ResponseEntity<?> modificar (@RequestBody Clases clase) 
	{
		try 
		{
			reClases.save(clase);
			return ResponseEntity.ok("Se modifico correctamente");
		}
		catch(Exception e) 
		{
			return ResponseEntity.badRequest().body("Hubo un error al modificar la clase");
		}
	}
	
	@SuppressWarnings("null")
	@GetMapping("/clases/{idClase}")
	public ResponseEntity<?> buscarId(@PathVariable Integer idClase)
	{
		return ResponseEntity.ok(reClases.findById(idClase));
	}
	
	@SuppressWarnings("null")
	@DeleteMapping("/clases/{idClase}")
	public ResponseEntity<?> eliminar(@PathVariable Integer idClase) 
	{
		try 
		{
			reClases.deleteById(idClase);
			return ResponseEntity.ok("Se elimino correctamente");
		}
		catch(Exception e) 
		{
			return ResponseEntity.badRequest().body("Hubo un error al borrar la clase");
		}
	}
}
