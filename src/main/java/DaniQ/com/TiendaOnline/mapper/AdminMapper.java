package DaniQ.com.TiendaOnline.mapper;

import DaniQ.com.TiendaOnline.domain.Admin;
import DaniQ.com.TiendaOnline.persistence.model.Administrador;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AdminMapper {

    @Mappings({
            @Mapping(source = "idAdmin", target = "adminId"),
            @Mapping(source = "nombreCompleto", target = "fullName"),
            @Mapping(source = "nombreUsuario", target = "userName"),
            @Mapping(source = "contrasena", target = "password"),

    })
    Admin toAdmin (Administrador administrador);
    List<Admin> toAdmins (List<Administrador> administradores);

    @InheritInverseConfiguration
    @Mapping(target = "productos", ignore = true)
    Administrador toAdministrador(Admin admin);
}
