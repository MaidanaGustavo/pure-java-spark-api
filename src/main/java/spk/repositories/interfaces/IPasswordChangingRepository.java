package spk.repositories.interfaces;

import java.util.List;

import spk.domain.PasswordChangeRequest;
import spk.dto.PasswordChangeRequest.PasswordChangeRequestInsertDTO;
import spk.dto.PasswordChangeRequest.VerifyTokenDTO;
import spk.exceptions.ApiErrorException;

public interface IPasswordChangingRepository {
  
  public List<PasswordChangeRequest> findAll();
  public PasswordChangeRequest create(PasswordChangeRequestInsertDTO passInserted) throws ApiErrorException; 
  public PasswordChangeRequest findById(String id) throws ApiErrorException;
  public List<PasswordChangeRequest> listByUser(Integer userId);
  public PasswordChangeRequest verifyToken(VerifyTokenDTO verifyTokenDTO);
  
}
