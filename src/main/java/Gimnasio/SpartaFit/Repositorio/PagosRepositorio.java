package Gimnasio.SpartaFit.Repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import Gimnasio.SpartaFit.Modelos.Clientes;
import Gimnasio.SpartaFit.Modelos.Pagos;

public interface PagosRepositorio extends JpaRepository<Pagos, Integer>
{
	List<Pagos> findByIdCliente(Clientes cliente);
}
