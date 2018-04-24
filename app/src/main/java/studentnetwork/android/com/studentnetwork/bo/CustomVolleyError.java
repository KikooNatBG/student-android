package studentnetwork.android.com.studentnetwork.bo;

public class CustomVolleyError {
    private String entityName;
    private String errorKey;
    private String type;
    private String title;
    private int status;
    private String message;
    private String params;

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public String getErrorKey() {
        return errorKey;
    }

    public void setErrorKey(String errorKey) {
        this.errorKey = errorKey;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    @Override
    public String toString() {
        return "CustomVolleyError{" +
                "entityName='" + entityName + '\'' +
                ", errorKey='" + errorKey + '\'' +
                ", type='" + type + '\'' +
                ", title='" + title + '\'' +
                ", status=" + status +
                ", message='" + message + '\'' +
                ", params='" + params + '\'' +
                '}';
    }
}
