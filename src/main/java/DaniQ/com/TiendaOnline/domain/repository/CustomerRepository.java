package DaniQ.com.TiendaOnline.domain.repository;

import DaniQ.com.TiendaOnline.domain.Customer;
import DaniQ.com.TiendaOnline.mapper.CustomerMapper;
import DaniQ.com.TiendaOnline.persistence.crud.CrudCliente;
import DaniQ.com.TiendaOnline.persistence.model.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerRepository implements CustomerRepositoryInter{

    private final CustomerMapper mapper;

    private final CrudCliente cliente;

    @Autowired
    public CustomerRepository(CustomerMapper mapper, CrudCliente cliente) {
        this.mapper = mapper;
        this.cliente = cliente;
    }

    @Override
    public Customer saveCustomer(Customer customer) {
        Cliente cli = mapper.toCliente(customer);
        return mapper.toCustomer(cliente.save(cli));
    }
}
