package spk.controllers;
import com.google.gson.Gson;

import io.jsonwebtoken.Jwts;

import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;


import spark.Request;
import spark.Response;
import spk.App;
import spk.domain.User;
import spk.dto.User.UserRequestDTO;
import spk.dto.User.UserRequestLoginDTO;
import spk.exceptions.ApiErrorException;
import spk.models.ResponseModel;
import spk.services.interfaces.IUserService;

public class UserController {

     IUserService _userService;

    public static UserController getInstance(){
      return new UserController();
    }
    public UserController(){}
    
    public UserController(IUserService _userService){
      this._userService = _userService;
    }

  public ResponseModel<User> index(Request req,Response res){
    ResponseModel<User> resp ;

    try{
      List<User> users = _userService.listAllUsers();
      resp = new ResponseModel<User>(200,users,"Ok");
      res.status(200);
    }catch(Exception e){
      resp = new ResponseModel<User>(500,null,"Erro ao buscar Usuarios!");
      res.status(500);
    }


    return resp;
  }


  public ResponseModel<User> save(Request req,Response res){
    ResponseModel<User> resp = null;
    Gson gson = new Gson();
    List<User> userResp = new ArrayList<>();

    try{
      User userRequest = gson.fromJson(req.body(),User.class);
      User userInserted  = _userService.createUser(userRequest);
      userResp.add(userInserted);
      resp = new ResponseModel<User>(201,userResp,"Sucesso!");
      res.status(201);
    }catch(ApiErrorException ap){
      // System.out.println(ap.getMessage());
       resp  = new ResponseModel<User>(ap.getStatusCode(),null,ap.getMessage()); 
       res.status(ap.getStatusCode());
    }catch(Exception e){
      System.err.println(e);
      resp = new ResponseModel<User>(500,null, "Erro ao criar usuário.");
      res.status(500);
    }
    return resp;
  }

  public ResponseModel<User> find(Request req,Response res){
    ResponseModel<User> resp = null;
    List<User> data  = new ArrayList<User>();
    try{
      Integer id = Integer.parseInt(req.params("id"));
      User user = _userService.findUserById(id);
      data.add(user);
      resp = new ResponseModel<User>(200,data,"Sucesso!");
      res.status(200);
    }catch(ApiErrorException ap){
      resp = new ResponseModel<User>(ap.getStatusCode(),data,ap.getMessage());
    }catch(Exception e){
      System.err.println(e);
      resp = new ResponseModel<User>(500,data, "Erro ao buscar usuário.");
      res.status(500);
    }
    return resp;
  }

  public ResponseModel<User> update(Request req,Response res) throws ApiErrorException{

    ResponseModel<User> resp = null;
    Gson gson = new Gson();
    List<User> userResp = new ArrayList<>();
    Integer id =  Integer.parseInt(req.params("id"));
   
    try {

      UserRequestDTO user = gson.fromJson(req.body(), UserRequestDTO.class);
      User userUpdated = _userService.updateUser(id, user);
      userResp.add(userUpdated);
      resp = new ResponseModel<User>(200,userResp,"Sucesso!");
      res.status(200);
    } catch (Exception e) {
      resp = new ResponseModel<User>(500,null,"Erro ao atualizar usuário.");
      res.status(500);
    }

    return resp;
  }

  public ResponseModel<User> delete(Request req,Response res) {
    ResponseModel<User> resp = null;
    Integer id =  Integer.parseInt(req.params("id"));

    try {
      
      Boolean response = _userService.deleteUser(id);

      if(response){
        resp = new ResponseModel<User>(200,null,"Usuario deletado com sucesso!");
        res.status(200);
      }else{
        resp = new ResponseModel<User>(500,null,"Erro ao deletar usuário.");
        res.status(500);
      }

    } catch (Exception e) {
      resp = new ResponseModel<User>(500,null,"Erro ao deletar usuário.");
      res.status(500);
    }

    return resp;
  }

  public ResponseModel<User> findByNickname(Request req,Response res){
    ResponseModel<User> resp = null;
    List<User> data  = new ArrayList<User>();
    try{
      String nickname = req.params("nickname");
      User user = _userService.findByNickname(nickname);
      data.add(user);
      resp = new ResponseModel<User>(200,data,"Sucesso!");
      res.status(200);
    }catch(ApiErrorException ap){
      resp = new ResponseModel<User>(ap.getStatusCode(),data,ap.getMessage());
    }catch(Exception e){
      System.err.println(e);
      resp = new ResponseModel<User>(500,data, "Erro ao buscar usuário.");
      res.status(500);
    }
    return resp;
  }

  public ResponseModel<LinkedHashMap<String,String>> login(Request req,Response res) throws NoSuchAlgorithmException{
    ResponseModel<LinkedHashMap<String,String>> resp = null;
    List<LinkedHashMap<String,String>> data = new ArrayList<LinkedHashMap<String,String>>();
    UserRequestLoginDTO userRequestLoginDTO = new Gson().fromJson(req.body(), UserRequestLoginDTO.class);
    
    try { 
      Boolean login = _userService.loginUser(userRequestLoginDTO);
      
      if(login){

              String jwtToken = Jwts.builder()
          .setSubject(userRequestLoginDTO.getNickname())
          .setIssuer("localhost:"+App.port)
          .setIssuedAt(new Date())
          .setExpiration(
          Date.from(
            LocalDateTime.now().plusMinutes(15L)
              .atZone(ZoneId.systemDefault())
            .toInstant()))
          .signWith(App.CHAVE)
          .compact();

          LinkedHashMap<String,String> token = new LinkedHashMap<String,String>();
          token.put("token", jwtToken);
          data.add(token);
          
        resp = new ResponseModel<LinkedHashMap<String,String>>(200,data,"Login efetuado com sucesso!");
      }
    
    } catch (ApiErrorException e) {
      resp = new ResponseModel<LinkedHashMap<String,String>>(e.getStatusCode(),null,e.getMessage());
      res.status(e.getStatusCode());
    }
    return resp;
  }

} 