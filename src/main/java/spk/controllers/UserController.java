package spk.controllers;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import spark.Request;
import spark.Response;
import spk.domain.User;
import spk.dto.User.UserRequestDTO;
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
        resp = new ResponseModel<User>(203,null,"Usuario deletado com sucesso!");
        res.status(203);
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
} 