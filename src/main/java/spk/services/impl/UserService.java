package spk.services.impl;
import java.security.NoSuchAlgorithmException;
import java.util.List;


import spk.domain.User;
import spk.dto.User.UserRequestDTO;
import spk.dto.User.UserRequestLoginDTO;
import spk.exceptions.ApiErrorException;
import spk.repositories.interfaces.IUserRepository;
import spk.services.interfaces.IUserService;
import spk.utils.HashCreator;
public class UserService implements IUserService {
    
   private IUserRepository _userRepository;

    public UserService(IUserRepository _userRepository){
        this._userRepository = _userRepository;
    }
    
    @Override
    public List<User> listAllUsers() {
        return  _userRepository.findAll();
    }

    @Override
    public User createUser(User userInsert) throws ApiErrorException , NoSuchAlgorithmException {
        User user = null;
        
        

        if(userInsert!=null){
            
            System.out.println(userInsert);

            if(!this.validUserFields(userInsert)){
                throw new ApiErrorException(400,"Há campos obrigatórios faltando."); 
            }
            User userExists  = _userRepository.findByNickname(userInsert.getNickname());

            if(userExists!=null){
                throw new ApiErrorException(403, "Nickname já cadastrado!.");
            }
            
            String pss  = this.hashPassword(userInsert.getPassword());
            userInsert.setPassword(pss);
            
            user = _userRepository.create(userInsert);
            
        }else{
            throw new ApiErrorException(400,"Usuário inválido.");
        }
        
        return user;
    }

    @Override
    public Boolean validUserFields(User user) {
        if(user.getName()==null || user.getEmail()==null || user.getPassword()==null){
            return false;
        }

        if(user.getEmail().equalsIgnoreCase("") || user.getName().equalsIgnoreCase("") || user.getPassword().equalsIgnoreCase("")){
            return false;
        }

        return true;
    }

    @Override
    public String hashPassword(String pass) throws NoSuchAlgorithmException {
        String hashString;
        HashCreator hash = new HashCreator();
        hashString = hash.createSHAHash(pass);
        return hashString;
    }

    @Override
    public User findUserById(Integer id) throws ApiErrorException {
        User user = null;
        if(id==null){
            throw new ApiErrorException(400,"O id precisa ser válido!");
        }
        user = _userRepository.findById(id);
        
        if(user==null){
            throw new ApiErrorException(404,"Usuário não encontrado");
        }
        
        return user;
    }

    @Override
    public User updateUser(Integer id, UserRequestDTO user) throws ApiErrorException {
        
        User userExists = _userRepository.findById(id);

        if(userExists!=null){
            User userUpdated = _userRepository.update(user, id);
            return userUpdated;
        }else{
            throw new ApiErrorException(404,"Usuario não encontrado.");
        }
        
    }

    @Override
    public Boolean deleteUser(Integer id) {
        try {
            User userExists = _userRepository.findById(id);
            if(userExists!=null){
                _userRepository.delete(id);
                return true;
            }else{
                throw new ApiErrorException(404,"Usuario não encontrado.");
            }
            
        } catch (Exception e) {
            return false;
        }
        
    }

    @Override
    public User findByNickname(String nickname) throws ApiErrorException {
        User user = null;
        if(nickname==null){
            throw new ApiErrorException(400,"O nickname precisa ser válido!");
        }
        user = _userRepository.findByNickname(nickname);
        
        if(user==null){
            throw new ApiErrorException(404,"Usuário não encontrado");
        }
        
        return user;
    }

    @Override
    public Boolean loginUser(UserRequestLoginDTO userRequestLoginDTO) throws ApiErrorException, NoSuchAlgorithmException {
             
        if(userRequestLoginDTO.getNickname()==null || userRequestLoginDTO.getPassword()==null){
            throw new ApiErrorException(400,"Há campos obrigatórios faltando!");
        }
        
        User userExists = _userRepository.findByNickname(userRequestLoginDTO.getNickname());
        
        if(userExists==null){
            throw new ApiErrorException(404,"Usuário não encontrado!");
        }


        String hashPassword = hashPassword(userRequestLoginDTO.getPassword());
        userRequestLoginDTO.setPassword(hashPassword);
        
        User userLogin = _userRepository.login(userRequestLoginDTO);

        if(userLogin==null){
            throw new ApiErrorException(401,"Senha incorreta!");
        }


        return true;
    }
}
