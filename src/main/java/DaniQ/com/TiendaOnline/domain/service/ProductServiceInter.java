package DaniQ.com.TiendaOnline.domain.service;

import DaniQ.com.TiendaOnline.domain.Admin;
import DaniQ.com.TiendaOnline.domain.Product;

import java.util.List;
import java.util.Optional;

public interface ProductServiceInter {

    Product saveProduct (Product product);

    List<Product> getAll();

    void deleteProduct(String productId);

    Optional<Product> getProductById(String productId);

    Product updateProduct(Product product);


}
