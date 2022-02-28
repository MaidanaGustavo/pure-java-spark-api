package spk.services.impl;

import java.util.Date;

import spk.domain.PasswordChangeRequest;
import spk.domain.User;
import spk.dto.PasswordChangeRequest.PasswordChangeRequestInsertDTO;
import spk.dto.PasswordChangeRequest.VerifyTokenDTO;
import spk.exceptions.ApiErrorException;
import spk.repositories.interfaces.IPasswordChangingRepository;
import spk.repositories.interfaces.IUserRepository;
import spk.services.interfaces.IPasswordChangeService;
import spk.utils.RandomTokenGenerator;

public class PasswordChangeService implements IPasswordChangeService{

  public PasswordChangeService(IUserRepository iUserRepository,IPasswordChangingRepository iPasswordChangingRepository){
    this.iUserRepository = iUserRepository;
    this.iPasswordChangingRepository = iPasswordChangingRepository;
  }
  
  IPasswordChangingRepository iPasswordChangingRepository;
  IUserRepository iUserRepository;
  @Override
  public PasswordChangeRequest createPasswordChangeRequest(Integer idUser) throws ApiErrorException {
    PasswordChangeRequest pass  = null;
    try {
      User user  = iUserRepository.findById(idUser);
      
      if(user==null) throw new ApiErrorException(404,"Usuario não encontrado!");

      RandomTokenGenerator rdm = RandomTokenGenerator.getInstance();

      String token =  rdm.generateRandomNumber();
      PasswordChangeRequestInsertDTO pInsertDTO  = new PasswordChangeRequestInsertDTO(new Date(),idUser,token);
        pass = iPasswordChangingRepository.create(pInsertDTO);

    } catch (Exception e) {
      System.err.println(e.getMessage());
      throw new ApiErrorException(500,"Erro interno!");
    }

    return pass;
  }
  
  @Override
  public Boolean verifyToken(VerifyTokenDTO verifyTokenDTO)  throws ApiErrorException{
    PasswordChangeRequest pass = null; 
    
    try {
      pass  = iPasswordChangingRepository.verifyToken(verifyTokenDTO);
      if(pass == null) return false;
      return true;
    } catch (Exception e) {
      System.err.println(e.getMessage());
      throw new ApiErrorException(500,"Erro interno");
    }
    
  }
  
  
}
