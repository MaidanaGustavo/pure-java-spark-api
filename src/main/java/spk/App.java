package spk;
import static spark.Spark.*;

import spk.resources.PostResource;
import spk.resources.UserResource;
/**
 * Hello world!
 *
 */
public class App 
{
    private final static String basePath = "myapi/v1";

    public static void main( String[] args )
    {
        before((request,response)->{
            response.header("Content-Type", "application/json");
            response.type("application/json");
        });
        
        get("/", (req, res) -> "Api is working!");
        
        UserResource.configureRoutes(basePath);
        PostResource.configureRoutes(basePath);
        
    }
}
