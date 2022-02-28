package spk.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import spk.domain.PasswordChangeRequest;
import spk.dto.PasswordChangeRequest.VerifyTokenDTO;

public interface PasswordChangesRequestMapper {

  @Insert("INSERT INTO password_change_requests(time,id_user,token_recover) VALUES(#{time},#{idUser},#{tokenRecover})")
  void insertPassCR(PasswordChangeRequest pass);


  @Results({
    @Result(property = "idUser" , column = "id_user"),
    @Result(property = "tokenRecover" , column = "token_recover")
  })
  @Select("select * from password_change_requests")
  List<PasswordChangeRequest> getAllPassCR();


  @Results({
    @Result(property = "idUser" , column = "id_user"),
    @Result(property = "tokenRecover" , column = "token_recover")
  })
  @Select("select * from password_change_requests WHERE id = #{id}")
  PasswordChangeRequest findPassCR(String id);

  @Results({
    @Result(property = "idUser" , column = "id_user"),
    @Result(property = "tokenRecover" , column = "token_recover")
  })
  @Select("select * from password_change_requests WHERE id_user = #{idUser} order by time desc LIMIT 1")
  PasswordChangeRequest lastInserted(Integer idUser);


  @Results({
    @Result(property = "idUser" , column = "id_user"),
    @Result(property = "tokenRecover" , column = "token_recover")
  })
  @Select("select * from password_change_requests WHERE id_user = #{idUser}")
  List<PasswordChangeRequest> getAllByUserId(Integer idUser);

  @Results({
    @Result(property = "idUser" , column = "id_user"),
    @Result(property = "tokenRecover" , column = "token_recover")
  })
  @Select("select * from password_change_requests WHERE id_user = #{idUser} AND id = #{idRequest} and token_recover = #{tokenRequest}")
  PasswordChangeRequest verifyTokenAndUser(VerifyTokenDTO verifyTokenDTO);
}
