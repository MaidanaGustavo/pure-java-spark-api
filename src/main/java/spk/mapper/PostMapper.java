package spk.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import spk.domain.Post;

public interface PostMapper {
  
  @Insert("INSERT INTO post (title,description,id_user,created_at) VALUES (#{title},#{description},#{idUser},#{createdAt})")
  void insertPost(Post post);

  @Results({
    @Result(property = "idUser" , column = "id_user"),
    @Result(property = "createdAt" , column = "created_at")
  })
  @Select("select * from post")
  List<Post> getAllPost();
  
  @Results({
    @Result(property = "idUser" , column = "id_user"),
    @Result(property = "createdAt" , column = "created_at")
  })
  @Select("select * from post where id=#{id}")
  Post findById(Integer id);
  
  @Results({
    @Result(property = "idUser" , column = "id_user"),
    @Result(property = "createdAt" , column = "created_at")
  })
  @Select("SELECT * FROM post WHERE id= LAST_INSERT_ID() ")
  Post lastPostInserted();
 
  @Delete("DELETE FROM post WHERE id = #{id}")
  void deletePost(Integer id);

  @Update("UPDATE post SET title = #{title} WHERE id = #{id}")
  void updateTitlePost(Post post);


  @Update("UPDATE post SET description = #{description} WHERE id = #{id}")
  void updateDescriptionPost(Post post);

  @Update("UPDATE post SET isEdited = 1 WHERE id = #{id}")
  void updateIsEdit(Integer id);
  
  @Results({
    @Result(property = "idUser" , column = "id_user"),
    @Result(property = "createdAt" , column = "created_at")
  })
  @Select("select * from post where id_user=#{idUser} order by id desc ")
  List<Post> listPostByUser(Integer idUser);
}
