package spk.repositories.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import spk.domain.Post;
import spk.dto.Post.PostRequestDTO;
import spk.exceptions.ApiErrorException;
import spk.mapper.PostMapper;
import spk.repositories.interfaces.IPostRepository;
import spk.utils.MyBatisUtil;

public class PostRepository implements IPostRepository{

  @Override
  public List<Post> findAll() {
    SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
    List<Post> posts = new ArrayList<Post>();


    try{

      PostMapper postMapper = sqlSession.getMapper(PostMapper.class);
      posts = postMapper.getAllPost();
      
    }catch(Exception e){
      //
    }finally{
      sqlSession.close();
    }
    
    return posts;
  }

  @Override
  public Post create(PostRequestDTO postInserted)   {
    SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
    Post post = null;

    try{

      PostMapper postMapper = sqlSession.getMapper(PostMapper.class);
      postMapper.insertPost(new Post(postInserted));
      sqlSession.commit();
      post  = postMapper.lastPostInserted();

    }catch(Exception e){
      System.out.println(e.getMessage());
    }finally{
      sqlSession.close();
    }
    return post;
  }

  @Override
  public Post findById(Integer id) throws ApiErrorException {
    SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
    Post post = null;

    try{

      PostMapper postMapper = sqlSession.getMapper(PostMapper.class);
      post  = postMapper.findById(id);

    }catch(Exception e){
      //
    }finally{
      sqlSession.close();
    }
    return post;
  }

  @Override
  public Post update(PostRequestDTO  postUpdated, Integer id)   {
    
    SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
    Post post = null;

    try{
      PostMapper postMapper = sqlSession.getMapper(PostMapper.class);

      if(! (postUpdated.getTitle()==null) || !(postUpdated.getTitle().equalsIgnoreCase("")) ){
        postMapper.updateTitlePost(new Post(id,postUpdated.getTitle(),postUpdated.getDescription(),postUpdated.getIdUser()));
      }

      if(! (postUpdated.getDescription()==null) || !(postUpdated.getDescription().equalsIgnoreCase("")) ){
        postMapper.updateDescriptionPost(new Post(id,postUpdated.getTitle(),postUpdated.getDescription(),postUpdated.getIdUser()));
      }

      postMapper.updateIsEdit(id);

      post  = postMapper.findById(id);
    }catch(Exception e){
      //
    }finally{
      sqlSession.commit();
      sqlSession.close();
    }
    return post;
  }

  @Override
  public void delete(Integer id) throws ApiErrorException {
    SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();

    try{

      PostMapper postMapper = sqlSession.getMapper(PostMapper.class);
      postMapper.deletePost(id);

    }catch(Exception e){
      System.out.println(e.getMessage());
    }finally{
      sqlSession.commit();
      sqlSession.close();
    }
  }

  @Override
  public List<Post> listByUser(Integer userId) {
    SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
    List<Post> posts = new ArrayList<Post>();
    
    try{

      PostMapper postMapper = sqlSession.getMapper(PostMapper.class);
      posts = postMapper.listPostByUser(userId);

    }catch(Exception e){
      //
    }finally{
      sqlSession.close();
    }

    return posts;
  }
  
  
}
