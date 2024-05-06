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
import Gimnasio.SpartaFit.Modelos.Pagos;
import Gimnasio.SpartaFit.Repositorio.ClientesRepositorio;
import Gimnasio.SpartaFit.Repositorio.PagosRepositorio;
import Gimnasio.SpartaFit.Request.PagosRequest;

@RestController
@RequestMapping("/apispartafit")
public class PagosController 
{
	@Autowired
	private PagosRepositorio rePagos;
	
	@Autowired
	private ClientesRepositorio reClientes;
	
	@GetMapping("/pagos")
	public ResponseEntity<?> buscarTodos() 
	{
		List<Pagos> pago = rePagos.findAll();
		List<PagosRequest> pagodto = new ArrayList<>();
		for (Pagos paga : pago) 
	    {
	        String nombre = paga.getCliente() != null ? paga.getCliente().getNombre() : null;
	        
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
	        String fecha = sdf.format(paga.getFechaPago());
	        
	        PagosRequest dto = new PagosRequest
	        		(
	        				paga.getIdPago(), 
	        				fecha, 
	        				paga.getMonto(),
	        				paga.getMetodoPago(),
	        				paga.getEstadoPago(),
	        				nombre
	        		);

	        pagodto.add(dto);
	    }
		
		return ResponseEntity.ok(pagodto);
	}
	
	@SuppressWarnings("null")
	@PostMapping("/pagos")
	public ResponseEntity<?> guardar(@RequestBody PagosRequest pago)
	{
		try 
		{
			Clientes clie = reClientes.findById(pago.getIdCliente())
					.orElseThrow(()->new RuntimeException("Cliente no encontrado"));
			Pagos pagos = new Pagos();
			pagos.setCliente(clie);
			pagos.setIdPago(pago.getIdPago());
			
			DateTimeFormatter formatear = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		    LocalDate fechapago = LocalDate.parse(pago.getFechaPago(), formatear);
		    pagos.setFechaPago(Date.valueOf(fechapago));
		    
			pagos.setMonto(pago.getMonto());
			pagos.setMetodoPago(pago.getMetodoPago());
			pagos.setEstadoPago(pago.getEstadoPago());
			
			rePagos.save(pagos);
			return ResponseEntity.ok("Se guardo el pago con exito");
		}
		catch (Exception e) 
		{
			return ResponseEntity.badRequest().body("El programa no pudo registrar el pago, el error es el siguiente: " + e.getMessage());
		}
	}

	@SuppressWarnings("null")
	@GetMapping("/pagos/{idCliente}")
	public ResponseEntity<?> buscarIdCliente(@PathVariable("idCliente") String idCliente){
		try 
		{
			Clientes clie = reClientes.findById(idCliente)
					.orElseThrow(()->new RuntimeException("Error al buscar al cliente"));
			return ResponseEntity.ok(rePagos.findByIdCliente(clie));
		}
		catch (Exception e) 
		{
			return ResponseEntity.badRequest().body("El programa no pudo encontrar el pago, el error es el siguiente: " + e.getMessage());
		}		
	}
}
