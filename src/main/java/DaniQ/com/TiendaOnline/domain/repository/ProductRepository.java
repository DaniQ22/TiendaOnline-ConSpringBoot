package DaniQ.com.TiendaOnline.domain.repository;

import DaniQ.com.TiendaOnline.domain.Product;
import DaniQ.com.TiendaOnline.mapper.ProductMapper;
import DaniQ.com.TiendaOnline.persistence.crud.CrudProducto;
import DaniQ.com.TiendaOnline.persistence.model.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductRepository implements ProductRepositoryInter{

    private final ProductMapper mapper;

    private final CrudProducto crudProducto;

    @Autowired
    public ProductRepository(ProductMapper mapper, CrudProducto crudProducto) {
        this.mapper = mapper;
        this.crudProducto = crudProducto;
    }

    @Override
    public Product saveProduct(Product product) {
        Producto producto = mapper.toProductp(product);
        return mapper.toProduct(crudProducto.save(producto));
    }

    @Override
    public List<Product> getAll() {
        List<Producto> productos = (List<Producto>) crudProducto.findAll();
        return mapper.toProducts(productos);
    }
}
