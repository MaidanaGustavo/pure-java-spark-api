package spk.domain;
import java.io.Serializable;
import java.util.Objects;
public class User implements Serializable{

  
  private Integer id;
  private String name;
  private String email;
  private String password;
  private String nickname;

  public User() {
  }

  public User(Integer id, String name, String email, String password,String nickname ) {
    this.id = id;
    this.name = name;
    this.email = email;
    this.password = password;
    this.nickname = nickname;
  }

  public Integer getId() {
    return this.id;
  }

  public void setId(Integer id) {
    this.id = id;
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


  public User id(Integer id) {
    setId(id);
    return this;
  }

  public User name(String name) {
    setName(name);
    return this;
  }

  public User email(String email) {
    setEmail(email);
    return this;
  }

  public User password(String password) {
    setPassword(password);
    return this;
  }

  @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof User)) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(name, user.name) && Objects.equals(email, user.email) && Objects.equals(password, user.password);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, email, password);
  }

  @Override
  public String toString() {
    return "{" +
      " id='" + getId() + "'" +
      ", name='" + getName() + "'" +
      ", email='" + getEmail() + "'" +
      ", password='" + getPassword() + "'" +
      ", nickname '"+getNickname()+"'"+
      "}";
  }

}