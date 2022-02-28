package spk.controllers;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import com.google.gson.Gson;

import spark.Request;
import spark.Response;
import spk.domain.PasswordChangeRequest;
import spk.dto.PasswordChangeRequest.VerifyTokenDTO;
import spk.exceptions.ApiErrorException;
import spk.models.ResponseModel;
import spk.services.interfaces.IPasswordChangeService;

public class PasswordChangeController {
 
  public PasswordChangeController(IPasswordChangeService iPasswordChangeService){
    this.iPasswordChangeService = iPasswordChangeService;
  }

  IPasswordChangeService iPasswordChangeService;

  public ResponseModel<LinkedHashMap<String,String>> save(Request req,Response res){

    List<LinkedHashMap<String,String>> resp = new ArrayList<LinkedHashMap<String,String>>();
    ResponseModel<LinkedHashMap<String,String>> response = null;
    String body = req.body();

    LinkedHashMap<String,String> dados = new Gson().fromJson(body, LinkedHashMap.class);
    Object idUsuario = "";
    Double idUserDouble = 0.0 ;
    Integer idUser = 0;
    try {
       idUsuario =  dados.get("idUser");
       idUserDouble = (Double) idUsuario;
       idUser = idUserDouble.intValue();
    } catch (Exception e) {
      res.status(400);
        return new ResponseModel<LinkedHashMap<String,String>>(400,null,"O campo com o id do usuário é obrigatório!");
    }

    try {
      PasswordChangeRequest pass = iPasswordChangeService.createPasswordChangeRequest(idUser);
      LinkedHashMap<String,String> retorno = new LinkedHashMap<String,String>();
      retorno.put("idToken", pass.getId());
      resp.add(retorno);
      response = new ResponseModel<LinkedHashMap<String,String>>(201,resp,"Sucesso!");
      res.status(201);

    } catch (ApiErrorException e) {
      res.status(e.getStatusCode());
      response = new ResponseModel<LinkedHashMap<String,String>>(e.getStatusCode(),null,e.getMessage());
    }
    return response;
}

  public ResponseModel<LinkedHashMap<String,Boolean>> verifyToken(Request req, Response res){
    List<LinkedHashMap<String,Boolean>> resp = new ArrayList<LinkedHashMap<String,Boolean>>();
    ResponseModel<LinkedHashMap<String,Boolean>> response = null;

    VerifyTokenDTO verifyTokenDTO = new Gson().fromJson(req.body(), VerifyTokenDTO.class);
    try {
      Boolean resultado = iPasswordChangeService.verifyToken(verifyTokenDTO);
      LinkedHashMap<String,Boolean> retorno  = new LinkedHashMap<String,Boolean>();
      retorno.put("valido", resultado);
      resp.add(retorno);
      String mensagemRetorno = resultado?"O token é válido!":"O token é inválido!";
      response = new ResponseModel<LinkedHashMap<String,Boolean>>(200,resp,mensagemRetorno);
    } catch (ApiErrorException e) {
      response = new ResponseModel<LinkedHashMap<String,Boolean>>(e.getStatusCode(),null,e.getMessage());
    }
    return response;
  }
}
