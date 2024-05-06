package Gimnasio.SpartaFit.Request;

public class AsistenciasRequest 
{
    int idAsistencia;

    String fecha;
    String hora;

    String idCliente;
    
    String idClaseEmpleado;

	public int getIdAsistencia() {
		return idAsistencia;
	}

	public void setIdAsistencia(int idAsistencia) {
		this.idAsistencia = idAsistencia;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public String getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
	}

	public String getIdClaseEmpleado() {
		return idClaseEmpleado;
	}

	public void setIdClaseEmpleado(String idClaseEmpleado) {
		this.idClaseEmpleado = idClaseEmpleado;
	}

	public AsistenciasRequest(int idAsistencia, String fecha, String hora, String idCliente, String idClaseEmpleado) {
		super();
		this.idAsistencia = idAsistencia;
		this.fecha = fecha;
		this.hora = hora;
		this.idCliente = idCliente;
		this.idClaseEmpleado = idClaseEmpleado;
	}
}
