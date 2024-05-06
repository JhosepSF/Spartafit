package Gimnasio.SpartaFit.Request;

public class EvaluacionesFisicasRequest 
{
	int idEvaluacionFisica;

	String idCliente;

	String fechaEvaluacion;
    Double peso;
    Double altura;
    Double imc;
    
	public int getIdEvaluacionFisica() {
		return idEvaluacionFisica;
	}
	public void setIdEvaluacionFisica(int idEvaluacionFisica) {
		this.idEvaluacionFisica = idEvaluacionFisica;
	}
	public String getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
	}
	public String getFechaEvaluacion() {
		return fechaEvaluacion;
	}
	public void setFechaEvaluacion(String fechaEvaluacion) {
		this.fechaEvaluacion = fechaEvaluacion;
	}
	public Double getPeso() {
		return peso;
	}
	public void setPeso(Double peso) {
		this.peso = peso;
	}
	public Double getAltura() {
		return altura;
	}
	public void setAltura(Double altura) {
		this.altura = altura;
	}
	public Double getImc() {
		return imc;
	}
	public void setImc(Double imc) {
		this.imc = imc;
	}
	
	public EvaluacionesFisicasRequest(int idEvaluacionFisica, String idCliente, String fechaEvaluacion, Double peso,
			Double altura, Double imc) {
		super();
		this.idEvaluacionFisica = idEvaluacionFisica;
		this.idCliente = idCliente;
		this.fechaEvaluacion = fechaEvaluacion;
		this.peso = peso;
		this.altura = altura;
		this.imc = imc;
	}
}
