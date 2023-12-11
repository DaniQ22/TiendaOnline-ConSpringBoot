package DaniQ.com.TiendaOnline.domain.util.validation;

import DaniQ.com.TiendaOnline.domain.Category;
import DaniQ.com.TiendaOnline.domain.repository.CategoryRepository;

public class ProductValidation {


    public static boolean  ValidationNameProduct(String productName){
        if (productName.isEmpty() || productName.isBlank()){
            return false;
        }
        return true;
    }

    public static boolean ValidationQuantyProduct(int quanty){
        if (quanty <=0) {
            return false;
        }
        return true;
    }

    public static boolean ValidationPrice(double price){
        if (price <=0){
            return false;
        }
        return true;
    }

}
