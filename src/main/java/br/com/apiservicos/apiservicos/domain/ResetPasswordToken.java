package br.com.apiservicos.apiservicos.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

@Entity
@Data
@ToString(exclude="id")
@EqualsAndHashCode(exclude={"token","createdDate"})
public class ResetPasswordToken implements Serializable {

    private static final long serialVersionUID = -2747634784133926811L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String token;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @OneToOne(targetEntity = Usuario.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private Usuario user;

    public boolean isExpired(Long resetPasswordTokenExpirationMisiseg) {
        Calendar timeout = Calendar.getInstance();
        timeout.setTimeInMillis(this.createdDate.getTime() + resetPasswordTokenExpirationMisiseg);
        Date dateTimeout = timeout.getTime();
        return (dateTimeout.before(new Date()));
    }

    public Usuario getUser() {
        return user;
    }
}
