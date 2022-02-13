package spk.services.interfaces;

import java.util.List;

import spk.domain.Post;
import spk.dto.Post.PostRequestDTO;
import spk.exceptions.ApiErrorException;

public interface IPostService {
  
  public List<Post> listAll();
  public Post findById(Integer id) throws ApiErrorException;
  public Post save(PostRequestDTO postRequestDTO) throws ApiErrorException;  
  public Post update(PostRequestDTO updatRequestDTO,Integer postId) throws ApiErrorException;
  public void delete(Integer id) throws ApiErrorException;
  public List<Post> listPostsByUser(Integer userId) throws ApiErrorException;

}
