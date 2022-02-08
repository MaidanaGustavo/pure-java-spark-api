package spk.exceptions;
import java.util.Objects;

public class ApiErrorException extends Exception{

  protected Integer statusCode;
  protected String message; 
  protected Throwable error;

  public ApiErrorException(){
    super();
  }


  public ApiErrorException(Integer statusCode, String message) {
    super();
    this.statusCode = statusCode;
    this.message = message;
  }

  public ApiErrorException(Integer statusCode, String message, Throwable error) {
    super();
    this.statusCode = statusCode;
    this.message = message;
    this.error = error; 
  }

  public Throwable getError() {
    return this.error;
  }

  public void setError(Throwable error) {
    this.error = error;
  }



  public Integer getStatusCode() {
    return this.statusCode;
  }

  public void setStatusCode(Integer statusCode) {
    this.statusCode = statusCode;
  }

  public String getMessage() {
    return this.message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public ApiErrorException statusCode(Integer statusCode) {
    setStatusCode(statusCode);
    return this;
  }

  public ApiErrorException message(String message) {
    setMessage(message);
    return this;
  }

  @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof ApiErrorException)) {
            return false;
        }
        ApiErrorException apiErrorException = (ApiErrorException) o;
        return Objects.equals(statusCode, apiErrorException.statusCode) && Objects.equals(message, apiErrorException.message);
  }

  @Override
  public int hashCode() {
    return Objects.hash(statusCode, message);
  }

  @Override
  public String toString() {
    return "{" +
      " statusCode='" + getStatusCode() + "'" +
      ", message='" + getMessage() + "'" +
      "}";
  }

}