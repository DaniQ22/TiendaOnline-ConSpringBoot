package DaniQ.com.TiendaOnline.domain.util.results;

public class AdminExistsException extends RuntimeException{
    public AdminExistsException(String mensagge){
        super(mensagge);
    }
}
