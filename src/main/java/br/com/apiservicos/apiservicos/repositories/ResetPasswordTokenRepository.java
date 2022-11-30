package br.com.apiservicos.apiservicos.repositories;

import br.com.apiservicos.apiservicos.domain.ResetPasswordToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface ResetPasswordTokenRepository extends JpaRepository<ResetPasswordToken, Integer>{

    @Transactional(readOnly = true)
    Optional<ResetPasswordToken> findByToken(@Param("token") String token);

    @Transactional(readOnly = true)
    @Query(value = "SELECT spt FROM ResetPasswordToken spt JOIN spt.user user WHERE user.email = :email")
    Optional<ResetPasswordToken> findByEmail(@Param("email") String email);
}