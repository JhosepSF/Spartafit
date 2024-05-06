package Gimnasio.SpartaFit.Modelos;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class EvaluacionesFisicas 
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idEvaluacionFisica;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idCliente")
    private Clientes idCliente;
    
    @Column
    private Date fechaEvaluacion;
    private double peso;
    private double altura;
    private double imc;
    
    
	public int getIdEvaluacionFisica() {
		return idEvaluacionFisica;
	}
	public void setIdEvaluacionFisica(int idEvaluacionFisica) {
		this.idEvaluacionFisica = idEvaluacionFisica;
	}
	public Clientes getCliente() {
		return idCliente;
	}
	public void setCliente(Clientes cliente) {
		this.idCliente = cliente;
	}
	public Date getFechaEvaluacion() {
		return fechaEvaluacion;
	}
	public void setFechaEvaluacion(Date fechaEvaluacion) {
		this.fechaEvaluacion = fechaEvaluacion;
	}
	public double getPeso() {
		return peso;
	}
	public void setPeso(double peso) {
		this.peso = peso;
	}
	public double getAltura() {
		return altura;
	}
	public void setAltura(double altura) {
		this.altura = altura;
	}
	public double getImc() {
		return imc;
	}
	public void setImc(double imc) {
		this.imc = imc;
	}

}