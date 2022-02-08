package spk.models;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class ResponseModel <T> implements Serializable{
    private Integer statusCode;
    private List<T> response;
    private String message;


  public ResponseModel() {
  }

  public ResponseModel(Integer statusCode, List<T> response, String message) {
    this.statusCode = statusCode;
    this.response = response;
    this.message = message;
  }

  public Integer getStatusCode() {
    return this.statusCode;
  }

  public void setStatusCode(Integer statusCode) {
    this.statusCode = statusCode;
  }

  public List<T> getResponse() {
    return this.response;
  }

  public void setResponse(List<T> response) {
    this.response = response;
  }

  public String getMessage() {
    return this.message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public ResponseModel<T> statusCode(Integer statusCode) {
    setStatusCode(statusCode);
    return this;
  }

  public ResponseModel<T> response(List<T> response) {
    setResponse(response);
    return this;
  }

  public ResponseModel<T> message(String message) {
    setMessage(message);
    return this;
  }

  @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof ResponseModel)) {
            return false;
        }
        ResponseModel<T> responseModel = (ResponseModel <T> ) o;
        return Objects.equals(statusCode, responseModel.statusCode) && Objects.equals(response, responseModel.response) && Objects.equals(message, responseModel.message);
  }

  @Override
  public int hashCode() {
    return Objects.hash(statusCode, response, message);
  }

  @Override
  public String toString() {
    return "{" +
      " statusCode='" + getStatusCode() + "'" +
      ", response='" + getResponse() + "'" +
      ", message='" + getMessage() + "'" +
      "}";
  }

}