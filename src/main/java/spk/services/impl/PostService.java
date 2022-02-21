package spk.services.impl;

import java.util.ArrayList;
import java.util.List;


import spk.domain.Post;
import spk.dto.Post.PostRequestDTO;
import spk.exceptions.ApiErrorException;
import spk.repositories.interfaces.IPostRepository;
import spk.services.interfaces.IPostService;

public class PostService implements IPostService {

  IPostRepository pRepository;

  public PostService(IPostRepository postRepository){
    this.pRepository = postRepository;
  }
  @Override
  public List<Post> listAll() {
    return pRepository.findAll();
  }

  @Override
  public Post findById(Integer id) throws ApiErrorException {
    Post post = null;
    try {
      post = pRepository.findById(id);
      if(post==null){
        throw new ApiErrorException(404,"Post não encontrado!");
      }
    } catch (Exception e) {
      throw new ApiErrorException(500,"Erro ao buscar Post");
    }
    return post;
  }

  @Override
  public Post save(PostRequestDTO postRequestDTO) throws ApiErrorException {
    
    if(postRequestDTO.getTitle()==null || postRequestDTO.getDescription()==null || postRequestDTO.getIdUser()==null ){
        throw new ApiErrorException(400,"Há campos obrigatórios faltando!");
    }
    Post post = null;
    try {
      post  = pRepository.create(postRequestDTO);
    } catch (Exception e) {
      throw new ApiErrorException(500,"Erro ao criar Post");
    }
    return post;
  }

  @Override
  public Post update(PostRequestDTO updateRequestDTO, Integer postId) throws ApiErrorException {
    Post post = null;
    try {
      Post postExists = pRepository.findById(postId);

      if(postExists==null){
        throw new ApiErrorException(404,"Post não encontrado!");
      }

      post = pRepository.update(updateRequestDTO, postId);
    } catch (Exception e) {
      System.out.println(e.getMessage());
      throw new ApiErrorException(500,"Erro ao atualizar Post");
    }
    return post;
  }

  @Override
  public void delete(Integer id) throws ApiErrorException {
    
    try {
      Post postExists = pRepository.findById(id);

      if(postExists==null){
        throw new ApiErrorException(404,"Post não encontrado!");
      }
      pRepository.delete(id);
    } catch (ApiErrorException e) {
      throw new ApiErrorException(e.getStatusCode(),e.getMessage());
    } catch (Exception e) {
      System.out.println(e.getMessage());
      throw new ApiErrorException(500,"Erro ao deletar Post");
    }
  }

  @Override
  public List<Post> listPostsByUser(Integer userId) throws ApiErrorException {
    List<Post> posts = new ArrayList<Post>();
    try {
      posts = pRepository.listByUser(userId);
      
    } catch (Exception e) {
      throw new ApiErrorException(500,"Erro ao listar Post");
    }
    return posts;
  }
  
}
