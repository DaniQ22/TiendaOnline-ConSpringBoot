package DaniQ.com.TiendaOnline.domain.service;

import DaniQ.com.TiendaOnline.domain.Product;

import java.util.List;

public interface ProductServiceInter {

    Product saveProduct (Product product);

    List<Product> getAll();
}
