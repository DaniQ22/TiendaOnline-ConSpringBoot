package DaniQ.com.TiendaOnline.domain.service;

import DaniQ.com.TiendaOnline.domain.Admin;
import DaniQ.com.TiendaOnline.domain.Category;
import DaniQ.com.TiendaOnline.domain.Product;
import DaniQ.com.TiendaOnline.domain.repository.AdminRepository;
import DaniQ.com.TiendaOnline.domain.repository.CategoryRepository;
import DaniQ.com.TiendaOnline.domain.repository.ProductRepository;
import DaniQ.com.TiendaOnline.domain.util.results.MensaggeException;
import DaniQ.com.TiendaOnline.domain.util.validation.ProductValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService implements ProductServiceInter{

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    private final AdminRepository adminRepository;

    @Autowired
    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository, AdminRepository adminRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.adminRepository = adminRepository;
    }
    @Override
    public Product saveProduct(Product product) {
        Optional<Category> optionalCategory = categoryRepository.getById(product.getCategoryId());

        if (optionalCategory.isEmpty()){
            throw new MensaggeException("La categoria no existe");
        }


        Product productToSave = product;
        if (!ProductValidation.ValidationNameProduct(productToSave.getProductName())){
            throw new MensaggeException("El producto debe tener un nombre");
        }

        if (!ProductValidation.ValidationQuantyProduct(productToSave.getQuanty())){
            throw new MensaggeException("La cantidad del pridcto debe ser igual o mayor a 1");
        }

        if (!ProductValidation.ValidationPrice(productToSave.getPrice())){
            throw new MensaggeException(("Ingrese un precio valido"));
        }


        String productidRamdon = UUID.randomUUID().toString();
        productToSave.setProductId(productidRamdon);
        productToSave.setAvailable(true);
        return productRepository.saveProduct(product);
    }

    @Override
    public List<Product> getAll() {
        return productRepository.getAll(true);
    }

    @Override
    public void deleteProduct(String productId) {
        Optional<Product> optionalProduct = getProductById(productId);
        if (optionalProduct.isPresent()){
            productRepository.deleteProduct(productId);
        }
        throw new MensaggeException("El prducto no se ecuentra en la base de datos");

    }

    @Override
    public Optional<Product> getProductById(String productId) {
        return productRepository.getProductoById(productId);
    }

    @Override
    public Product updateProduct(Product product) {
        Optional<Product> optionalProduct = getProductById(product.getProductId());
        if (optionalProduct.isPresent()){
            productRepository.saveProduct(product);
        }
        throw new MensaggeException("El producto no esta registrado");
    }
}
