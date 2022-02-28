package spk;
import static spark.Spark.*;

import java.nio.charset.StandardCharsets;

import javax.crypto.SecretKey;

import com.google.gson.Gson;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import spk.models.ResponseModel;
import spk.resources.PasswordChangeResource;
import spk.resources.PostResource;
import spk.resources.UserResource;
/**
 * Hello world!
 *
 */
public class App 
{
    private final static String basePath = "myapi/v1";
    public final static Integer port = 3636;

    public final static SecretKey CHAVE = Keys.hmacShaKeyFor(
        "7f-j&CKk=coNzZc0y7_4obMP?#TfcYq%fcD0mDpenW2nc!lfGoZ|d?f&RNbDHUX6"
        .getBytes(StandardCharsets.UTF_8));
        
    public static void main( String[] args )
    {
        port(port);

        before((request,response)->{
            response.header("Content-Type", "application/json");
            response.type("application/json");
            response.header("Access-Control-Allow-Origin", "*");
            System.out.println(request.uri());
            Boolean isLoginRoute = request.uri().equalsIgnoreCase("/myapi/v1/users/login") ;
            Boolean isRegisterUserRoute = (request.uri().equalsIgnoreCase("/myapi/v1/users") && request.requestMethod().equalsIgnoreCase("POST")) ;
            Boolean isFindByNickname  = request.uri().contains("/myapi/v1/users/findbynickname/");
            System.out.println(isFindByNickname);
            System.out.println(request.uri());
            
            Boolean isChangePassword  = request.uri().contains("/myapi/v1/passwordchange");
            Boolean isVerifyToken  = request.uri().contains("/myapi/v1/passwordchange/verifyToken");

            System.out.println(isFindByNickname);
            System.out.println(request.uri());
            if( !(isLoginRoute || isRegisterUserRoute || request.requestMethod().equalsIgnoreCase("OPTIONS") || isFindByNickname || isChangePassword || isVerifyToken)  ){
                String token = request.headers("bearer");
                
                try {
                Jwts.parserBuilder()
                .setSigningKey(CHAVE)
                .build()
                .parseClaimsJws(token);

                } catch (Exception e) {
                    ResponseModel<String> resp = new ResponseModel<String>(401,null,"Não autorizado");
                    String respModel = new Gson().toJson(resp);
                    halt(401,respModel);
                }
            }
            
        });
        
        after((request,response)->{

                response.header(
                "Access-Control-Allow-Credentials", "true");

                response.header(
                "Access-Control-Allow-Headers",
                "X-TENANT-ID, origin, content-type, accept, authorization");

                response.header(
                "Access-Control-Allow-Methods",
                "*");
            });

        get("/", (req, res) -> {
           try {
           
           } catch (Exception e) {
            return "Nao autorizado";
           }
            
            return "Api is working!";
        });

        options("/*", (request,response)->{

            String accessControlRequestHeaders = request.headers("Access-Control-Request-Headers");
    	    if (accessControlRequestHeaders != null) {
    	        response.header("Access-Control-Allow-Headers", "*");
    	    }

    	    String accessControlRequestMethod = request.headers("Access-Control-Request-Method");
    	    if(accessControlRequestMethod != null){
    		response.header("Access-Control-Allow-Methods", "*");
    	    }

            response.status(200);
    	    return "OK";
           
    	});  
        
        UserResource.configureRoutes(basePath);
        PostResource.configureRoutes(basePath);
        PasswordChangeResource.configureRoutes(basePath);
        
    }
}
