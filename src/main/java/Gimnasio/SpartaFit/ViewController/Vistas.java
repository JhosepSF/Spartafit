package Gimnasio.SpartaFit.ViewController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Vistas
{
	@GetMapping("/spartafit")
	public String master() 
	{
		return "master";
	}
	
	@GetMapping("/spartafit/asistencias")
	public String asistencias() 
	{
		return "asistencias";
	}
	
	@GetMapping("/spartafit/clases")
	public String clases() 
	{
		return "clases";
	}

	@GetMapping("/spartafit/clasesempleado")
	public String clasesempleado() 
	{
		return "clasesempleado";
	}

	@GetMapping("/spartafit/empleados")
	public String empleados() 
	{
		return "empleados";
	}

	@GetMapping("/spartafit/evaluaciones")
	public String evaluaciones() 
	{
		return "evaluaciones";
	}

	@GetMapping("/spartafit/pagos")
	public String pagos() 
	{
		return "pagos";
	}

	@GetMapping("/spartafit/clientes")
	public String clientes() 
	{
		return "clientes";
	}
}
