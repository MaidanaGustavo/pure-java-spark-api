package spk.mapper;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import spk.domain.User;

public interface UserMapper {
    
  
  
  @Insert("INSERT INTO user (name,email,password) VALUES (#{name},#{email},#{password})")
  void insertUser(User user);

  @Select("select * from user")
  List<User> getAllUser();
  
  @Select("select * from user where id=#{id}")
  User findById(Integer id);
  
  @Select("SELECT * FROM user WHERE id= LAST_INSERT_ID() ")
  User lastUserInserted();
 
  @Delete("DELETE FROM user WHERE id = #{id}")
  void deleteUser(Integer id);

  @Update("UPDATE user SET name = #{name} WHERE id = #{id}")
  void updateNameUser(User user);


  @Update("UPDATE user SET email = #{email} WHERE id = #{id}")
  void updateEmailUser(String email,Integer id);


  @Update("UPDATE user SET password = #{password} WHERE id = #{id}")
  void updatePasswordUser(String password,Integer id);
}