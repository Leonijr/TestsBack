package br.com.apiservicos.apiservicos.services;

import br.com.apiservicos.apiservicos.domain.ResetPasswordToken;
import br.com.apiservicos.apiservicos.exceptions.ObjectNotFoundException;
import br.com.apiservicos.apiservicos.repositories.ResetPasswordTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ResetPasswordTokenService {

    @Autowired
    private ResetPasswordTokenRepository repo;

    public ResetPasswordToken findByEmail(String email) {
        Optional<ResetPasswordToken> obj = this.repo.findByEmail(email);
        return obj.orElse(null);
    }

    public ResetPasswordToken findByToken(String token) {
        return this.repo.findByToken(token).orElse(null);
    }


    public ResetPasswordToken findById(Integer id) {
        Optional<ResetPasswordToken> obj = this.repo.findById(id);
        return obj.orElse(null);
    }


    public ResetPasswordToken insert(ResetPasswordToken obj) {
        obj.setId(null);
        return this.repo.save(obj);
    }


    public ResetPasswordToken update(ResetPasswordToken obj) {
        if(this.findById(obj.getId()) == null) {
            throw new ObjectNotFoundException("Obeject "+ResetPasswordToken.class.getName()+" no found! ID "+obj.getId());
        }

        return this.repo.save(obj);
    }

    public Boolean tokenIsValid(String token){
        return repo.findByToken(token).isPresent();
    }
}
