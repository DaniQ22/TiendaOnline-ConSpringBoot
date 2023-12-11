package DaniQ.com.TiendaOnline.domain.service;

import DaniQ.com.TiendaOnline.domain.Customer;
import DaniQ.com.TiendaOnline.domain.Product;
import DaniQ.com.TiendaOnline.domain.Purchase;
import DaniQ.com.TiendaOnline.domain.PurchaseItem;
import DaniQ.com.TiendaOnline.domain.repository.CustomerRepository;
import DaniQ.com.TiendaOnline.domain.repository.ProductRepository;
import DaniQ.com.TiendaOnline.domain.repository.PurchaseRepository;
import DaniQ.com.TiendaOnline.domain.util.results.MensaggeException;
import DaniQ.com.TiendaOnline.domain.util.validation.PurchaseValidation;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PurchaseService implements PurchaseServiceInter{

    private final PurchaseRepository purchaseRepository;
    private final CustomerRepository customerRepository;

    private final ProductRepository productRepository;

    @Autowired
    public PurchaseService(PurchaseRepository purchaseRepository, CustomerRepository customerRepository, ProductRepository productRepository) {
        this.purchaseRepository = purchaseRepository;
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
    }


    @Override
    @Transactional
    public Purchase saveToPurchase(Purchase purchase) {

        if (purchase.getItems() == null || purchase.getItems().isEmpty()) {
            throw new MensaggeException("La lista de productos está vacía");
        }

        List<PurchaseItem> purchaseItems = purchase.getItems();
        for (PurchaseItem pro : purchaseItems){
            Optional<Product> optionalProduct = productRepository.getProductoById(pro.getProductId());
            Product product = optionalProduct.orElseThrow(() -> new MensaggeException("El producto no existe"));

            if (optionalProduct.isPresent() && optionalProduct.get().getQuanty()<pro.getQuantyItem()){
                throw new MensaggeException("Erros en la compra, la cantidad solicitdad es mayor a la cantidad disponible");
            }
            int quantyNow = optionalProduct.get().getQuanty() - pro.getQuantyItem();
            product.setQuanty(quantyNow);
            if (quantyNow >= 0){
                product.setAvailable(false);
            }
            productRepository.saveProduct(product);

            BigDecimal price = BigDecimal.valueOf(pro.getQuantyItem())
                    .multiply(BigDecimal.valueOf(product.getPrice()));
            pro.setTotaPrice(price.doubleValue());

        }

        try {
            PurchaseValidation.validateItems(purchase);
        } catch (MensaggeException ex) {
            // Maneja la excepción específica si es necesario
            throw new MensaggeException("Error de validación de productos: " + ex.getMessage());
        }

        if (!PurchaseValidation.validateCustomer(purchase.getCustomer())){
            throw new MensaggeException("Debes llenar todos los campos del cliente");
        }

        Customer customer = customerRepository.saveCustomer(purchase.getCustomer());
        purchase.setCustomerId(customer.getCustomerDni());
        LocalDateTime dateNow = LocalDateTime.now();
        purchase.setPurchaseDate(dateNow);
        return purchaseRepository.savePurchase(purchase);
    }
}
