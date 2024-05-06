package Gimnasio.SpartaFit.Modelos;

import java.sql.Date;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Asistencias 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idAsistencia;

    @Column
    private Date fecha;
    @JsonFormat(pattern = "HH:mm")
    private LocalTime hora;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idCliente")
    private Clientes cliente;    
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idClasesEmpleado")
    private ClasesEmpleados claseEmpleado;

	public int getIdAsistencia() {
		return idAsistencia;
	}

	public void setIdAsistencia(int idAsistencia) {
		this.idAsistencia = idAsistencia;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public LocalTime getHora() {
		return hora;
	}

	public void setHora(LocalTime hora) {
		this.hora = hora;
	}

	public Clientes getCliente() {
		return cliente;
	}

	public void setCliente(Clientes cliente) {
		this.cliente = cliente;
	}

	public ClasesEmpleados getClaseEmpleado() {
		return claseEmpleado;
	}

	public void setClaseEmpleado(ClasesEmpleados claseEmpleado) {
		this.claseEmpleado = claseEmpleado;
	}

}
