package spk.dto.PasswordChangeRequest;

import java.util.Date;

public class PasswordChangeRequestInsertDTO {

  private Date time;
  private Integer idUser;
  private String tokenRecover;



  public PasswordChangeRequestInsertDTO() {
  }

  public PasswordChangeRequestInsertDTO(Date time, Integer idUser, String tokenRecover) {
    this.time = time;
    this.idUser = idUser;
    this.tokenRecover = tokenRecover;
  }

  public Date getTime() {
    return this.time;
  }

  public void setTime(Date time) {
    this.time = time;
  }

  public Integer getIdUser() {
    return this.idUser;
  }

  public void setIdUser(Integer idUser) {
    this.idUser = idUser;
  }

  public String getTokenRecover() {
    return this.tokenRecover;
  }

  public void setTokenRecover(String tokenRecover) {
    this.tokenRecover = tokenRecover;
  }
  
}
