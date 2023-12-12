package DaniQ.com.TiendaOnline.domain.controller;

import DaniQ.com.TiendaOnline.domain.Purchase;
import DaniQ.com.TiendaOnline.domain.service.PurchaseService;
import DaniQ.com.TiendaOnline.domain.util.results.MensaggeException;
import DaniQ.com.TiendaOnline.domain.util.webToken.JWTtoken;
import io.jsonwebtoken.Claims;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("Puerchase")
public class PurchaseController {

    private final PurchaseService purchaseService;

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    private final JWTtoken jwTtoken;


    @Autowired
    public PurchaseController(PurchaseService purchaseService, JWTtoken jwTtoken) {
        this.purchaseService = purchaseService;
        this.jwTtoken = jwTtoken;
    }

    @PostMapping("api/purchase")
    public ResponseEntity<?> savePuerchase (@RequestHeader(value = "Authorization") String token,
                                            @RequestBody Purchase purchase){
        logger.info("Token recibido en la solicitud: {}", token);

        try {
            jwTtoken.validateToken(token);
            Purchase purchaTosave = purchaseService.saveToPurchase(purchase);
            return ResponseEntity.status(HttpStatus.CREATED).body(purchaTosave);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Verifica tus credenciales, " +
                    "no tienes acceso a esta ruta");
        }
    }
}


