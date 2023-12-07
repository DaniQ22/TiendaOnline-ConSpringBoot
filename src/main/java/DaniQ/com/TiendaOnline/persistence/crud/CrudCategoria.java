package DaniQ.com.TiendaOnline.persistence.crud;

import DaniQ.com.TiendaOnline.persistence.model.Categoria;
import org.springframework.data.repository.CrudRepository;

public interface CrudCategoria extends CrudRepository<Categoria, Integer> {

}
