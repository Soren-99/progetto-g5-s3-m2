package sorenrahimi.progetto.g5s3m2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sorenrahimi.progetto.g5s3m2.entities.User;
import sorenrahimi.progetto.g5s3m2.exceptions.UnauthorizedException;
import sorenrahimi.progetto.g5s3m2.payload.users.UserLoginDTO;
import sorenrahimi.progetto.g5s3m2.security.JWTTools;

@Service
public class AuthService {
    @Autowired
    private UsersService usersService;
    @Autowired
    private JWTTools jwtTools;
    @Autowired
    private PasswordEncoder bcrypt;

    public String authenticateDipendenteAndGenerateToken(UserLoginDTO payload){


        User user = this.usersService.findByEmail(payload.email());

        if (bcrypt.matches(payload.password(), user.getPassword())) {

            return jwtTools.createToken(user);
        } else {

            throw new UnauthorizedException("Credenziali non valide! Effettua di nuovo il login!");
        }


    }
}

