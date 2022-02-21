package spk.mapper;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import spk.domain.User;
import spk.dto.User.UserRequestLoginDTO;

public interface UserMapper {
    
  
  
  @Insert("INSERT INTO user (name,email,password,nickName) VALUES (#{name},#{email},#{password},#{nickname})")
  void insertUser(User user);

  @Results({
    @Result(property = "nickname" , column = "nickName")
  })
  @Select("select * from user")
  List<User> getAllUser();
  
  @Results({
    @Result(property = "nickname" , column = "nickName")
  })
  @Select("select * from user where id=#{id}")
  User findById(Integer id);
  
  @Results({
    @Result(property = "nickname" , column = "nickName")
  })
  @Select("SELECT * FROM user WHERE id= LAST_INSERT_ID() ")
  User lastUserInserted();
 
  @Delete("DELETE FROM user WHERE id = #{id}")
  void deleteUser(Integer id);

  @Update("UPDATE user SET name = #{name} WHERE id = #{id}")
  void updateNameUser(User user);


  @Update("UPDATE user SET email = #{email} WHERE id = #{id}")
  void updateEmailUser(User user);


  @Update("UPDATE user SET password = #{password} WHERE id = #{id}")
  void updatePasswordUser(User user);

  @Update("UPDATE user SET nickName = #{nickname} WHERE id = #{id}")
  void updateNicknameUser(User user);

  @Results({
    @Result(property = "nickname" , column = "nickName")
  })
  @Select("select * from user where nickName=#{nickname}")
  User findByNickname(String  nickname);
  
  @Results({
    @Result(property = "nickname" , column= "nickName")
  })
  @Select("select * from user where nickName= #{nickname} and password = #{password}")
  User login(UserRequestLoginDTO user);
  
}
