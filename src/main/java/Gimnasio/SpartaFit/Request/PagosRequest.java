package Gimnasio.SpartaFit.Request;

public class PagosRequest
{
	
    int idPago;

    String fechaPago;
    Double monto;
    String metodoPago;
    String estadoPago;

    String idCliente;
    
	public int getIdPago() {
		return idPago;
	}

	public void setIdPago(int idPago) {
		this.idPago = idPago;
	}

	public String getFechaPago() {
		return fechaPago;
	}

	public void setFechaPago(String fechaPago) {
		this.fechaPago = fechaPago;
	}

	public Double getMonto() {
		return monto;
	}

	public void setMonto(Double monto) {
		this.monto = monto;
	}

	public String getMetodoPago() {
		return metodoPago;
	}

	public void setMetodoPago(String metodoPago) {
		this.metodoPago = metodoPago;
	}

	public String getEstadoPago() {
		return estadoPago;
	}

	public void setEstadoPago(String estadoPago) {
		this.estadoPago = estadoPago;
	}

	public String getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
	}

	public PagosRequest(int idPago, String fechaPago, Double monto, String metodoPago, String estadoPago,
			String idCliente) {
		super();
		this.idPago = idPago;
		this.fechaPago = fechaPago;
		this.monto = monto;
		this.metodoPago = metodoPago;
		this.estadoPago = estadoPago;
		this.idCliente = idCliente;
	}
}
