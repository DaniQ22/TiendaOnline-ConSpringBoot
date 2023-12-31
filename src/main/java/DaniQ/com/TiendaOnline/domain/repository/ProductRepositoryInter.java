package DaniQ.com.TiendaOnline.domain.repository;

import DaniQ.com.TiendaOnline.domain.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepositoryInter {

    Product saveProduct(Product product);

    List<Product> getAll(boolean available);

    Optional<Product> getProductoById(String productId);

    void deleteProduct(String ProductId);



}
