package spk.resources;

import com.google.gson.Gson;
import static spark.Spark.*;
import spk.controllers.PostController;
import spk.repositories.impl.PostRepository;
import spk.services.impl.PostService;

public class PostResource {
  
  static PostController postController = new PostController(new PostService(new PostRepository()) );
  static 	Gson gson = new Gson();


  public static void configureRoutes(String basePath){

    path(basePath+"/posts",()->{
        get("",postController::index,gson::toJson);
        get("/:id",postController::find,gson::toJson);
        post("",postController::save,gson::toJson);
        put("/:id", postController::update,gson::toJson);
        delete("/:id",postController::delete,gson::toJson);
        get("/user/:idUser",postController::listPostByUser,gson::toJson);
    });

  }
}
