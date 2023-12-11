package DaniQ.com.TiendaOnline.persistence.crud;

import DaniQ.com.TiendaOnline.persistence.model.Cliente;
import org.springframework.data.repository.CrudRepository;

public interface CrudCliente extends CrudRepository<Cliente, String> {
}
