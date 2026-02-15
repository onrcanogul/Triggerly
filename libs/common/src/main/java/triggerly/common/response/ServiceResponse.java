package triggerly.common.response;


import java.util.Collections;
import java.util.List;


public class ServiceResponse<T> {

    private T data;
    private List<String> errors;
    private int statusCode;
    private boolean successful;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    public boolean isSuccessful() {
        return successful;
    }

    public void setSuccessful(boolean successful) {
        this.successful = successful;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    /* ---------------- SUCCESS ---------------- */

    // Success with data
    public static <T> ServiceResponse<T> success(T data, int statusCode) {
        ServiceResponse<T> response = new ServiceResponse<>();
        response.setData(data);
        response.setStatusCode(statusCode);
        response.setSuccessful(true);
        return response;
    }

    // Success without data
    public static ServiceResponse<Void> success(int statusCode) {
        ServiceResponse<Void> response = new ServiceResponse<>();
        response.setStatusCode(statusCode);
        response.setSuccessful(true);
        return response;
    }

    /* ---------------- FAILURE ---------------- */

    public static <T> ServiceResponse<T> error(List<String> errors, int statusCode) {
        ServiceResponse<T> response = new ServiceResponse<>();
        response.setErrors(errors);
        response.setStatusCode(statusCode);
        response.setSuccessful(false);
        return response;
    }

    public static <T> ServiceResponse<T> error(String error, int statusCode) {
        ServiceResponse<T> response = new ServiceResponse<>();
        response.setErrors(Collections.singletonList(error));
        response.setStatusCode(statusCode);
        response.setSuccessful(false);
        return response;
    }

    public static <T> ServiceResponse<T> failure(List<String> errors, int statusCode) {
        ServiceResponse<T> response = new ServiceResponse<>();
        response.setErrors(errors);
        response.setStatusCode(statusCode);
        response.setSuccessful(false);
        return response;
    }

    public static <T> ServiceResponse<T> failure(String error, int statusCode) {
        ServiceResponse<T> response = new ServiceResponse<>();
        response.setErrors(Collections.singletonList(error));
        response.setStatusCode(statusCode);
        response.setSuccessful(false);
        return response;
    }
}
