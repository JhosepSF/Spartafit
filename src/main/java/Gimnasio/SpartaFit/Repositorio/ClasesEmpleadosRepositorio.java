package Gimnasio.SpartaFit.Repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import Gimnasio.SpartaFit.Modelos.Clases;
import Gimnasio.SpartaFit.Modelos.ClasesEmpleados;
import Gimnasio.SpartaFit.Modelos.Empleados;

public interface ClasesEmpleadosRepositorio extends JpaRepository<ClasesEmpleados, Integer>
{
	List<ClasesEmpleados> findByIdClases(Clases clase);
	List<ClasesEmpleados> findByIdEmpleados(Empleados empleado);
}
