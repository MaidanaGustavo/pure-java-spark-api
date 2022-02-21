package spk.repositories.impl;
import java.util.ArrayList;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import spk.domain.User;
import spk.dto.User.UserRequestDTO;
import spk.dto.User.UserRequestLoginDTO;
import spk.exceptions.ApiErrorException;
import spk.mapper.UserMapper;
import spk.repositories.interfaces.IUserRepository;
import spk.utils.MyBatisUtil;
public class UserRepository  implements IUserRepository{
    

    @Override
    public List<User> findAll() {
      SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
      List<User> users = new ArrayList<User>();
      try{

        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        users = userMapper.getAllUser();
        
      }catch(Exception e){
        //
      }finally{
        sqlSession.close();
      }
      return users;
    }

    @Override
    public User create(User userInsert)  throws ApiErrorException {
      SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
      User user ;
      try{
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
         userMapper.insertUser(userInsert);
         sqlSession.commit();
         user = userMapper.lastUserInserted();
         

      }catch(Exception e){
        System.err.println(e.getMessage());
        user = null;
         throw new ApiErrorException(500,"Erro ao criar usuário " + e.getMessage());
      }finally{
        sqlSession.close();
      }
      return user;
    }
    

    @Override
    public User findById(Integer id) throws ApiErrorException {
      SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();

      User user = null;
      try{
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        user = userMapper.findById(id);

      }catch (Exception e){
        System.err.println(e.getMessage());
        throw new ApiErrorException(500,"Erro ao buscar usuário " + e.getMessage());
      }finally{
        sqlSession.close();
      }
      
      return user;
    }

    @Override
    public User update(UserRequestDTO userUpdate,Integer id) throws ApiErrorException {
      SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();

      User user = null;
      User userMonted = new User(id,userUpdate.getName(),userUpdate.getEmail(),userUpdate.getPassword(),userUpdate.getNickname());
      try{
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        
        if(userUpdate.getName()!=null){
          userMapper.updateNameUser(userMonted);
        }
        
        if(userUpdate.getEmail()!=null){
          userMapper.updateEmailUser(userMonted);
        } 
        
        if(userUpdate.getPassword()!=null){
          userMapper.updateEmailUser(userMonted);
        }

        if(userUpdate.getNickname()!=null){
          userMapper.updateNicknameUser(user);
        }

        user = userMapper.findById(id);
        sqlSession.commit();
      }catch (Exception e){
        System.err.println(e.getMessage());
        throw new ApiErrorException(500,"Erro ao atualizar usuário " + e.getMessage());
      }finally{
        sqlSession.close();
      }
      
      return user;

    }

    @Override
    public void delete(Integer id) throws ApiErrorException {
      SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
      try{
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        userMapper.deleteUser(id);
        sqlSession.commit();
      }catch (Exception e){
        System.err.println(e.getMessage());
        throw new ApiErrorException(500,"Erro ao deletar usuário " + e.getMessage());
      }finally{
        sqlSession.close();
      }
      
    }

    @Override
    public User findByNickname(String  nickname) throws ApiErrorException {
      SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();

      User user = null;
      try{
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        user = userMapper.findByNickname(nickname);

      }catch (Exception e){
        System.err.println(e.getMessage());
        throw new ApiErrorException(500,"Erro ao buscar usuário " + e.getMessage());
      }finally{
        sqlSession.close();
      }
      
      return user;
    }

    @Override
    public User login(UserRequestLoginDTO userRequestLogin) throws ApiErrorException {
      SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
      User user = null;

      try {
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        user = userMapper.login(userRequestLogin);

      } catch (Exception e) {
        System.err.println(e.getMessage());
        throw new ApiErrorException(500,"Erro ao buscar usuário " + e.getMessage());
      }finally{
        sqlSession.close();
      }
      
      return user;
    }

}
