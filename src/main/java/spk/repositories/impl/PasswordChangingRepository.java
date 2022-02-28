package spk.repositories.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import spk.domain.PasswordChangeRequest;
import spk.dto.PasswordChangeRequest.PasswordChangeRequestInsertDTO;
import spk.dto.PasswordChangeRequest.VerifyTokenDTO;
import spk.exceptions.ApiErrorException;
import spk.mapper.PasswordChangesRequestMapper;
import spk.repositories.interfaces.IPasswordChangingRepository;
import spk.utils.MyBatisUtil;

public class PasswordChangingRepository implements IPasswordChangingRepository{

  @Override
  public List<PasswordChangeRequest> findAll() {
    SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
    List<PasswordChangeRequest> pass = new ArrayList<PasswordChangeRequest>();
    
    try {
      PasswordChangesRequestMapper passMapper =sqlSession.getMapper(PasswordChangesRequestMapper.class);
      pass = passMapper.getAllPassCR();

    } catch (Exception e) {
      //TODO: handle exception
    }finally{
      sqlSession.close();
    }
    return pass;

  }

  @Override
  public PasswordChangeRequest create(PasswordChangeRequestInsertDTO passInserted) throws ApiErrorException {
    SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
    PasswordChangeRequest pass = null;

    try {
      PasswordChangesRequestMapper passMapper = sqlSession.getMapper(PasswordChangesRequestMapper.class);

      passMapper.insertPassCR(new PasswordChangeRequest(passInserted));
      sqlSession.commit();
      pass = passMapper.lastInserted(passInserted.getIdUser());

    } catch (Exception e) {
      //TODO: handle exception
    }finally{
      sqlSession.close();
    }
    
    return pass;
  }

  @Override
  public PasswordChangeRequest findById(String id) throws ApiErrorException {
    SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
    PasswordChangeRequest pass = null;

    try {
      PasswordChangesRequestMapper passMapper = sqlSession.getMapper(PasswordChangesRequestMapper.class);
      pass = passMapper.findPassCR(id);
    } catch (Exception e) {
      //TODO: handle exception
    }finally{
      sqlSession.close();
    }
    return pass;
  }


  @Override
  public List<PasswordChangeRequest> listByUser(Integer userId) {
    SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
    List<PasswordChangeRequest> pass = new ArrayList<PasswordChangeRequest>();
    
    try {
      PasswordChangesRequestMapper passMapper =sqlSession.getMapper(PasswordChangesRequestMapper.class);
      pass = passMapper.getAllByUserId(userId);

    } catch (Exception e) {
      //TODO: handle exception
    }finally{
      sqlSession.close();
    }

    return pass;
  }

  @Override
  public PasswordChangeRequest verifyToken(VerifyTokenDTO verifyTokenDTO) {
    SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
    PasswordChangeRequest pass = null;

    try {
      PasswordChangesRequestMapper passMapper = sqlSession.getMapper(PasswordChangesRequestMapper.class);

      pass = passMapper.verifyTokenAndUser(verifyTokenDTO);

    } catch (Exception e) {
      //TODO: handle exception
    }finally{
      sqlSession.close();
    }
    return pass;
  }


}