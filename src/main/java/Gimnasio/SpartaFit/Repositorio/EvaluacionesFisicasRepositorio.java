package Gimnasio.SpartaFit.Repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import Gimnasio.SpartaFit.Modelos.Clientes;
import Gimnasio.SpartaFit.Modelos.EvaluacionesFisicas;

public interface EvaluacionesFisicasRepositorio extends JpaRepository<EvaluacionesFisicas, Integer>
{
	List<EvaluacionesFisicas> findByIdCliente(Clientes cliente);
}
