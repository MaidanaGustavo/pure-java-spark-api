package spk;
import static spark.Spark.*;
import spk.resources.UserResource;
/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        before((request,response)->{
            response.header("Content-Type", "application/json");
            response.type("application/json");
        });
        
        get("/", (req, res) -> "Api is working!");
        
        UserResource.configureRoutes("myapi/v1");
        
    }
}
