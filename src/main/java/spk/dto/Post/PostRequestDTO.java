package spk.dto.Post;

public class PostRequestDTO {
  
  private String title;
  private String description;
  private Integer idUser;
  

  public PostRequestDTO() {
  }

  public PostRequestDTO(String title, String description, Integer idUser) {
    this.title = title;
    this.description = description;
    this.idUser = idUser;
  }

  public String getTitle() {
    return this.title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return this.description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Integer getIdUser() {
    return this.idUser;
  }

  public void setIdUser(Integer idUser) {
    this.idUser = idUser;
  }

}
