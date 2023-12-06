package DaniQ.com.TiendaOnline.mapper;

import DaniQ.com.TiendaOnline.domain.Customer;
import DaniQ.com.TiendaOnline.persistence.model.Cliente;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    @Mappings({
            @Mapping(source = "dni", target = "customerDni"),
            @Mapping(source = "nombre", target = "name"),
            @Mapping(source = "apellido", target = "lastName"),
            @Mapping(source = "correo", target = "email"),
            @Mapping(source = "telefono", target = "phone")
    })
    Customer toCustomer (Cliente cliente);
    List<Customer> toCustomers(List<Cliente> clientes);

    @InheritInverseConfiguration
    @Mapping(target = "compras", ignore = true)
    Cliente toCliente(Customer customer);
}
