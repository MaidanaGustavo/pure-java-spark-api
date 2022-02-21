package spk.repositories.interfaces;
import java.util.List;
import spk.domain.User;
import spk.dto.User.UserRequestDTO;
import spk.dto.User.UserRequestLoginDTO;
import spk.exceptions.ApiErrorException;

public interface IUserRepository{

  public List<User> findAll();
  public User create(User userInsert) throws ApiErrorException; 
  public User findById(Integer id) throws ApiErrorException;
  public User update(UserRequestDTO userUpdate,Integer id)throws ApiErrorException;
  public void delete(Integer id)throws ApiErrorException;
  public User findByNickname(String nickname) throws ApiErrorException;
  public User login(UserRequestLoginDTO userRequestLogin) throws ApiErrorException;
}
