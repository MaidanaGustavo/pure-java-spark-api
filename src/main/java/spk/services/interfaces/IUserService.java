package spk.services.interfaces;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import spk.domain.User;
import spk.dto.User.UserRequestDTO;
import spk.exceptions.ApiErrorException;

public interface IUserService{

  public List<User> listAllUsers();
  public User createUser(User userInsert) throws ApiErrorException, NoSuchAlgorithmException;
  public Boolean validUserFields(User user);
  public String hashPassword(String pass) throws NoSuchAlgorithmException;
  public User findUserById(Integer id) throws ApiErrorException;
  public User updateUser(Integer id, UserRequestDTO user) throws ApiErrorException ;
  public Boolean deleteUser(Integer id);
  
}