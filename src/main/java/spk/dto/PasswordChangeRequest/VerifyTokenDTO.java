package spk.dto.PasswordChangeRequest;

public class VerifyTokenDTO {
    private Integer idUser;
    private String idRequest;
    private String tokenRequest;



  public VerifyTokenDTO() {
  }

  public VerifyTokenDTO(Integer idUser, String idRequest, String tokenRequest) {
    this.idUser = idUser;
    this.idRequest = idRequest;
    this.tokenRequest = tokenRequest;
  }

  public Integer getIdUser() {
    return this.idUser;
  }

  public void setIdUser(Integer idUser) {
    this.idUser = idUser;
  }

  public String getIdRequest() {
    return this.idRequest;
  }

  public void setIdRequest(String idRequest) {
    this.idRequest = idRequest;
  }

  public String getTokenRequest() {
    return this.tokenRequest;
  }

  public void setTokenRequest(String tokenRequest) {
    this.tokenRequest = tokenRequest;
  }

}
