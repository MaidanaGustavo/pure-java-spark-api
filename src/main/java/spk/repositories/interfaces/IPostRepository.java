package spk.repositories.interfaces;
import java.util.List;

import spk.domain.Post;
import spk.dto.Post.PostRequestDTO;
import spk.exceptions.ApiErrorException;

public interface IPostRepository{

  public List<Post> findAll();
  public Post create(PostRequestDTO postInserted) throws ApiErrorException; 
  public Post findById(Integer id) throws ApiErrorException;
  public Post update(PostRequestDTO postUpdated,Integer id)throws ApiErrorException;
  public void delete(Integer id)throws ApiErrorException;
  public List<Post> listByUser(Integer userId);
}