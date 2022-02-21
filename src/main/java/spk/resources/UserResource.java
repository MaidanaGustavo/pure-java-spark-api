package spk.resources;
import static spark.Spark.*;
import com.google.gson.Gson;
import spk.controllers.UserController;
import spk.repositories.impl.UserRepository;
import spk.services.impl.UserService;

public class UserResource{
  static UserController userController = new UserController( new UserService(new UserRepository()) );
  static 	Gson gson = new Gson();

  public static void configureRoutes(String basePath){
    

    path(basePath+"/users",()->{
        get("",userController::index,gson::toJson);
        get("/:id",userController::find,gson::toJson);
        post("",userController::save,gson::toJson);
        put("/:id", userController::update,gson::toJson);
        delete("/:id",userController::delete,gson::toJson);
        get("/findbynickname/:nickname",userController::findByNickname,gson::toJson);
        post("/login",userController::login,gson::toJson);
    });

  }
}