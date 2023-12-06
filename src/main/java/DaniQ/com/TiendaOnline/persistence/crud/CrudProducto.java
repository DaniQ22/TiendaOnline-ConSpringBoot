package DaniQ.com.TiendaOnline.persistence.crud;

import DaniQ.com.TiendaOnline.persistence.model.Producto;
import org.springframework.data.repository.CrudRepository;

public interface CrudProducto extends CrudRepository<Producto, String> {
}
