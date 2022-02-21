package spk.domain;

import java.util.Objects;

import spk.dto.Post.PostRequestDTO;

import java.util.Date;

public class Post {
  
  private Integer id;
  private String title;
  private String description;
  private Integer idUser;
  private Date createdAt;
  private Integer isEdited;


  public Post() {
  }

  public Post(Integer id, String title, String description, Integer idUser,Date createdAt, Integer isEdited) {
    this.id = id;
    this.title = title;
    this.description = description;
    this.idUser = idUser;
    this.createdAt = createdAt;
    this.isEdited = isEdited;
  }

  public Post(Integer id, String title, String description, Integer idUser,Date createdAt) {
    this.id = id;
    this.title = title;
    this.description = description;
    this.idUser = idUser;
    this.createdAt = createdAt;
  }


  public Post(Integer id, String title, String description, Integer idUser) {
    this.id = id;
    this.title = title;
    this.description = description;
    this.idUser = idUser;
  }

  public Post(PostRequestDTO postRequestDTO){
    this.title = postRequestDTO.getTitle();
    this.description = postRequestDTO.getDescription();
    this.idUser = postRequestDTO.getIdUser();
    this.createdAt = new Date();
  }

  public Integer getId() {
    return this.id;
  }

  public void setId(Integer id) {
    this.id = id;
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
  
  public Date getCreatedAT(){
    return this.createdAt;
  }

  public void setCreatedAt(Date createdAt){
    this.createdAt = createdAt;
  }

  public Integer getIsEdited(){
    return this.isEdited;
  }

  public void setIsEdited(Integer isEdited){
    this.isEdited = isEdited;
  }
  public void setIdUser(Integer idUser) {
    this.idUser = idUser;
  }

  public Post id(Integer id) {
    setId(id);
    return this;
  }

  public Post title(String title) {
    setTitle(title);
    return this;
  }

  public Post description(String description) {
    setDescription(description);
    return this;
  }

  public Post idUser(Integer idUser) {
    setIdUser(idUser);
    return this;
  }

  @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Post)) {
            return false;
        }
        Post post = (Post) o;
        return Objects.equals(id, post.id) && Objects.equals(title, post.title) && Objects.equals(description, post.description) && Objects.equals(idUser, post.idUser);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, title, description, idUser);
  }

  @Override
  public String toString() {
    return "{" +
      " id='" + getId() + "'" +
      ", title='" + getTitle() + "'" +
      ", description='" + getDescription() + "'" +
      ", idUser='" + getIdUser() + "'" +
      "}";
  }

}
