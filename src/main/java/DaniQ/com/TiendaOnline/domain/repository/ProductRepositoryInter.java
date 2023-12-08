package DaniQ.com.TiendaOnline.domain.repository;

import DaniQ.com.TiendaOnline.domain.Product;

import java.util.List;

public interface ProductRepositoryInter {

    Product saveProduct(Product product);

    List<Product> getAll();
}
