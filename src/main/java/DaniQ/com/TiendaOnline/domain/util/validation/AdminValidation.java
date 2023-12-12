package DaniQ.com.TiendaOnline.domain.util.validation;

import DaniQ.com.TiendaOnline.domain.Admin;

public class AdminValidation {

    private static final String cadenaCaracter = ".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?].*";

    public static Boolean validateSave(Admin admin){

        if (admin.getAdminId().isEmpty() || admin.getAdminId()==null){
            return false;
        }

        if (admin.getUserName().equals("") ||
                admin.getUserName().length()<3 ||
                admin.getUserName().length() >20 ||
                !admin.getUserName().matches(".*[A-Z].*")) {
            return false;
        }

        if (admin.getFullName().isEmpty() ||
                admin.getFullName().length()<10){
            return false;
        }

        if (admin.getPassword().isEmpty() ||
                admin.getPassword().length()<8 ||
                !admin.getPassword().matches(".*[A-Z].*") ||
                !admin.getPassword().matches(cadenaCaracter)) {
            return false;
        }

        return true;

    }
}
