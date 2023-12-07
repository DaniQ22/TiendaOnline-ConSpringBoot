package DaniQ.com.TiendaOnline.persistence.crud;

import DaniQ.com.TiendaOnline.persistence.model.Administrador;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface CrudAdministrador extends CrudRepository<Administrador, String> {

    @Query(value = "SELECT * FROM administrador ad WHERE ad.nombre_usuario = :nombreUsuario", nativeQuery = true)
    Administrador getAdminByUsername(@Param("nombreUsuario") String nombreUsuario);

}
