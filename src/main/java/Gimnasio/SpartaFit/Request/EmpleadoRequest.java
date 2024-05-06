package Gimnasio.SpartaFit.Request;


public class EmpleadoRequest 
{
	String idEmpleado;
	String nombre;
    String fechaContratacion;
    Double salario;
    String horarioTrabajo;
	public String getIdEmpleado() {
		return idEmpleado;
	}
	public void setIdEmpleado(String idEmpleado) {
		this.idEmpleado = idEmpleado;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getFechaContratacion() {
		return fechaContratacion;
	}
	public void setFechaContratacion(String fechaContratacion) {
		this.fechaContratacion = fechaContratacion;
	}
	public Double getSalario() {
		return salario;
	}
	public void setSalario(Double salario) {
		this.salario = salario;
	}
	public String getHorarioTrabajo() {
		return horarioTrabajo;
	}
	public void setHorarioTrabajo(String horarioTrabajo) {
		this.horarioTrabajo = horarioTrabajo;
	}   
}
