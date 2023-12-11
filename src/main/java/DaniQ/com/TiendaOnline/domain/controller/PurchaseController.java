package DaniQ.com.TiendaOnline.domain.controller;

import DaniQ.com.TiendaOnline.domain.Purchase;
import DaniQ.com.TiendaOnline.domain.service.PurchaseService;
import DaniQ.com.TiendaOnline.domain.util.results.MensaggeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.method.HandlerTypePredicate;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("Puerchase")
public class PurchaseController {

    private final PurchaseService purchaseService;


    @Autowired
    public PurchaseController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @PostMapping("api/purchase")
    public ResponseEntity<?> savePuerchase (@RequestBody Purchase purchase){
        try {
            Purchase purchaTosave = purchaseService.saveToPurchase(purchase);
            return ResponseEntity.status(HttpStatus.CREATED).body(purchaTosave);
        }catch (MensaggeException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }
}


