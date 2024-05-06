package Gimnasio.SpartaFit.Repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import Gimnasio.SpartaFit.Modelos.Asistencias;
import Gimnasio.SpartaFit.Modelos.Clientes;

public interface AsistenciasRepositorio extends JpaRepository<Asistencias, Integer>
{
	List<Asistencias> findByCliente(Clientes cliente);
}
