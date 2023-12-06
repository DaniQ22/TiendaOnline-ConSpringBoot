package DaniQ.com.TiendaOnline.domain.util.results;

public class SaveResult<T> {
    private Boolean success;
    private String message;
    private T entity;

    // Constructor
    public SaveResult(Boolean success, String message, T entity) {
        this.success = success;
        this.message = message;
        this.entity = entity;
    }

    // Getters y Setters

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getEntity() {
        return entity;
    }

    public void setEntity(T entity) {
        this.entity = entity;
    }
}