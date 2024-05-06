package Gimnasio.SpartaFit.Modelos;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class ClasesEmpleados 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idClaseEmpleado;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idEmpleados")
    private Empleados idEmpleados;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idClases")
    private Clases idClases;

	public Integer getIdClaseEmpleado() {
		return idClaseEmpleado;
	}

	public void setIdClaseEmpleado(Integer idClaseEmpleado) {
		this.idClaseEmpleado = idClaseEmpleado;
	}

	public Empleados getIdEmpleados() {
		return idEmpleados;
	}

	public void setIdEmpleados(Empleados idEmpleados) {
		this.idEmpleados = idEmpleados;
	}

	public Clases getIdClases() {
		return idClases;
	}

	public void setIdClases(Clases idClases) {
		this.idClases = idClases;
	}
}
