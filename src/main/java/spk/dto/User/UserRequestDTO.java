package spk.dto.User;

import java.util.Objects;

import spk.domain.User;



public class UserRequestDTO {
  private String name;
  private String email;
  private String password;
  private String nickname;

  public UserRequestDTO() {
  }

  public UserRequestDTO(String name, String email, String password,String nickname) {
    this.name = name;
    this.email = email;
    this.password = password;
  }

  public UserRequestDTO(User user) {
    this.name = user.getName();
    this.email = user.getEmail();
    this.password = user.getPassword();
  }
  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return this.email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return this.password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getNickname() {
    return this.nickname;
  }

  public void setNickname(String nickname) {
    this.nickname = nickname;
  }


  public UserRequestDTO name(String name) {
    setName(name);
    return this;
  }

  public UserRequestDTO email(String email) {
    setEmail(email);
    return this;
  }

  public UserRequestDTO password(String password) {
    setPassword(password);
    return this;
  }

  @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof UserRequestDTO)) {
            return false;
        }
        UserRequestDTO userRequestDTO = (UserRequestDTO) o;
        return Objects.equals(name, userRequestDTO.name) && Objects.equals(email, userRequestDTO.email) && Objects.equals(password, userRequestDTO.password);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, email, password);
  }

  @Override
  public String toString() {
    return "{" +
      " name='" + getName() + "'" +
      ", email='" + getEmail() + "'" +
      ", password='" + getPassword() + "'" +
      "}";
  }

} 