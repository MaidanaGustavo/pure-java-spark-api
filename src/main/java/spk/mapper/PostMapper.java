package spk.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import spk.domain.Post;
import spk.dto.Post.PostRequestDTO;

public interface PostMapper {
  
  @Insert("INSERT INTO post (title,description,id_user) VALUES (#{title},#{description},#{idUser})")
  void insertPost(PostRequestDTO postRequestDTO);

  @Results({
    @Result(property = "idUser" , column = "id_user")
  })
  @Select("select * from post")
  List<Post> getAllPost();
  
  @Select("select * from post where id=#{id}")
  Post findById(Integer id);
  
  @Results({
    @Result(property = "idUser" , column = "id_user")
  })
  @Select("SELECT * FROM post WHERE id= LAST_INSERT_ID() ")
  Post lastPostInserted();
 
  @Delete("DELETE FROM post WHERE id = #{id}")
  void deletePost(Integer id);

  @Update("UPDATE post SET title = #{title} WHERE id = #{id}")
  void updateTitlePost(Post post);


  @Update("UPDATE post SET description = #{description} WHERE id = #{id}")
  void updateDescriptionPost(Post post);
  
  @Results({
    @Result(property = "idUser" , column = "id_user")
  })
  @Select("select * from post where id_user=#{idUser} ")
  List<Post> listPostByUser(Integer idUser);
}
