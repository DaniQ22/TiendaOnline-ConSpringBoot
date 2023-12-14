package DaniQ.com.TiendaOnline.persistence.crud;

import DaniQ.com.TiendaOnline.persistence.model.Producto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CrudProducto extends CrudRepository<Producto, String> {


    @Query(value = "SELECT * FROM producto pro WHERE pro.disponible = :disponible", nativeQuery = true)
    List<Producto> getAllProduct(boolean disponible);
}
