package spk.services.interfaces;

import spk.domain.PasswordChangeRequest;
import spk.dto.PasswordChangeRequest.VerifyTokenDTO;
import spk.exceptions.ApiErrorException;

public interface IPasswordChangeService {
  public PasswordChangeRequest createPasswordChangeRequest(Integer idUser) throws ApiErrorException ;
  public Boolean verifyToken(VerifyTokenDTO verifyTokenDTO)  throws ApiErrorException;
}
