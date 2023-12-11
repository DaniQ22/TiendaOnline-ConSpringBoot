package DaniQ.com.TiendaOnline.domain.util.validation;

import DaniQ.com.TiendaOnline.domain.Customer;
import DaniQ.com.TiendaOnline.domain.Product;
import DaniQ.com.TiendaOnline.domain.Purchase;
import DaniQ.com.TiendaOnline.domain.PurchaseItem;
import DaniQ.com.TiendaOnline.domain.repository.ProductRepository;
import DaniQ.com.TiendaOnline.domain.util.results.MensaggeException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class PurchaseValidation {

    public static boolean validateItems (Purchase purchase){
        List<PurchaseItem> products = purchase.getItems();

        for (PurchaseItem pro : products){
            if (pro.getProductId().equals("")){
                throw new MensaggeException("Debes ingresar el producto que desea comprar");
            }

            if (pro.getQuantyItem()<=0){
                throw new MensaggeException("Debe ingresar una cantidad valida");
            }
        }
        return true;
    }

    public static boolean validateCustomer(Customer customer){
        if (customer.getCustomerDni()==null){
            return false;
        }
        if (customer.getName()==null){
            return false;
        }
        if (customer.getLastName()==null){
            return false;
        }
        if (customer.getEmail()==null){
            return false;
        }
        if (customer.getPhone()==null){
            return false;
        }
        return true;
    }


}
