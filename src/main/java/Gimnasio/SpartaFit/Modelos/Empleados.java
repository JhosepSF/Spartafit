package Gimnasio.SpartaFit.Modelos;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Empleados
{

    @Id
    private String idEmpleado;

	@Column
    private String nombre;
    private Date fechaContratacion;
    private Double salario;
    private String horarioTrabajo;    
    
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
	public Date getFechaContratacion() {
		return fechaContratacion;
	}
	public void setFechaContratacion(Date fechaContratacion) {
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