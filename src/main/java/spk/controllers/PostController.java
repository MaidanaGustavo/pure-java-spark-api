package spk.controllers;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import spark.Request;
import spark.Response;
import spk.domain.Post;
import spk.dto.Post.PostRequestDTO;
import spk.exceptions.ApiErrorException;
import spk.models.ResponseModel;
import spk.services.interfaces.IPostService;

public class PostController {
  IPostService postService;

  public static PostController getInstance(){
    return new PostController();
  }
  public PostController(){}

  public PostController(IPostService postService){
    this.postService = postService;
  }

  public ResponseModel<Post> index(Request req,Response res){
    List<Post> resp = new ArrayList<Post>();
    ResponseModel<Post> response = null;

    try {
      resp = postService.listAll();
      response = new ResponseModel<Post>(200,resp,"Sucesso!");

    } catch (Exception e) {
      response = new ResponseModel<Post>(500,null,"Erro ao listar posts!");
    }
    return response;
  }

  public ResponseModel<Post> find(Request req, Response res){
    ResponseModel<Post> response = null;
    List<Post> resp = new ArrayList<Post>();

    try {
      Integer id = Integer.parseInt(req.params("id"));
      resp.add(postService.findById(id));
      response = new ResponseModel<Post>(200,resp,"Sucesso!");
    } catch (ApiErrorException e) {
      response = new ResponseModel<Post>(e.getStatusCode(),null,e.getMessage());
    }

    return response;
  }
  
  public ResponseModel<Post> save(Request req,Response res){
      List<Post> resp = new ArrayList<Post>();
      ResponseModel<Post> response = null;

      try {

        PostRequestDTO postRequestDTO = new Gson().fromJson(req.body(), PostRequestDTO.class);
        Post postInserted = postService.save(postRequestDTO);
        resp.add(postInserted);
        response = new ResponseModel<Post>(201,resp,"Sucesso!");
        res.status(201);

      } catch (ApiErrorException e) {
        response = new ResponseModel<Post>(e.getStatusCode(),null,e.getMessage());
        res.status(e.getStatusCode());
      }
      return response;
  }

  public ResponseModel<Post> update(Request req,Response res){
    List<Post> resp = new ArrayList<Post>();
    ResponseModel<Post> response = null;
    
    try {
      Integer id = Integer.parseInt(req.params("id")); 
      PostRequestDTO postRequestDTO = new Gson().fromJson(req.body(), PostRequestDTO.class);
      Post post = postService.update(postRequestDTO, id);
      resp.add(post);
      response = new ResponseModel<Post>(200,resp,"Sucesso");
    } catch (ApiErrorException e) {
      response = new ResponseModel<Post>(e.getStatusCode(),null,e.getMessage());
      res.status(e.getStatusCode());
    }

    return response;
  }

  public ResponseModel<Post> delete(Request req, Response res){
    ResponseModel<Post> response = null;
    
    try {
      Integer id = Integer.parseInt(req.params("id"));
      postService.delete(id);  
      response = new ResponseModel<Post>(203,null,"Sucesso!");
      res.status(200);
    } catch (ApiErrorException e) {
      response = new ResponseModel<Post>(e.getStatusCode(),null,e.getMessage());
    }
    
    return response;
  }

  public ResponseModel<Post> listPostByUser(Request req,Response res){
    ResponseModel<Post> response =null;
    List<Post> resp = new ArrayList<Post>();
    
    try {
      Integer id = Integer.parseInt(req.params("idUser"));
      resp = postService.listPostsByUser(id);
      response = new ResponseModel<Post>(200,resp,"Sucesso!");
    } catch (ApiErrorException e) {
      response = new ResponseModel<Post>(e.getStatusCode(),null,e.getMessage());
    }

    return response;
  }
}
