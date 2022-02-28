package spk.resources;

import com.google.gson.Gson;
import static spark.Spark.*;
import spk.controllers.PasswordChangeController;
import spk.repositories.impl.PasswordChangingRepository;
import spk.repositories.impl.UserRepository;
import spk.services.impl.PasswordChangeService;

public class PasswordChangeResource {

  static PasswordChangeController passwordChangeController = new 
  
  PasswordChangeController(new PasswordChangeService(new UserRepository(),new PasswordChangingRepository()) );

  static 	Gson gson = new Gson();


  public static void configureRoutes(String basePath){

  
    path(basePath+"/passwordchange",()->{
        post("",passwordChangeController::save,gson::toJson);
        post("/verifyToken",passwordChangeController::verifyToken,gson::toJson);
    });

  }
}
