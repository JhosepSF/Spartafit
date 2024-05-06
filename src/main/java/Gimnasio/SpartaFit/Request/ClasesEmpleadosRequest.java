package Gimnasio.SpartaFit.Request;

public class ClasesEmpleadosRequest 
{
	Integer idClaseEmpleado;
    String idClase;
    String idEmpleado;
    
	public Integer getIdClaseEmpleado() {
		return idClaseEmpleado;
	}

	public void setIdClaseEmpleado(Integer idClaseEmpleado) {
		this.idClaseEmpleado = idClaseEmpleado;
	}

	public String getIdClase() {
		return idClase;
	}

	public void setIdClase(String idClase) {
		this.idClase = idClase;
	}

	public String getIdEmpleado() {
		return idEmpleado;
	}

	public void setIdEmpleado(String idEmpleado) {
		this.idEmpleado = idEmpleado;
	}

	public ClasesEmpleadosRequest(Integer idClaseEmpleado, String idClase, String idEmpleado) {
		super();
		this.idClaseEmpleado = idClaseEmpleado;
		this.idClase = idClase;
		this.idEmpleado = idEmpleado;
	}
}
