package spk.domain;

import java.util.Date;

import spk.dto.PasswordChangeRequest.PasswordChangeRequestInsertDTO;

public class PasswordChangeRequest {
  private String id;
  private Date time;
  private Integer idUser;
  private String tokenRecover;

  public PasswordChangeRequest() {
  }


  public PasswordChangeRequest(String id, Date time, Integer idUser,String tokenRecover) {
    this.id = id;
    this.time = time;
    this.idUser = idUser;
    this.tokenRecover = tokenRecover;
  }

  public PasswordChangeRequest(PasswordChangeRequestInsertDTO dto){
    this.time = dto.getTime();
    this.idUser = dto.getIdUser();
    this.tokenRecover = dto.getTokenRecover();
  }

  public String getId() {
    return this.id;
  }

  public void setId(String id) {
    this.id = id;
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
