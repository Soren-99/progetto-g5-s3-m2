package sorenrahimi.progetto.g5s3m2.controllers;

import sorenrahimi.progetto.g5s3m2.exceptions.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import sorenrahimi.progetto.g5s3m2.payload.users.UserLoginDTO;
import sorenrahimi.progetto.g5s3m2.payload.users.UserLoginResponseDTO;
import sorenrahimi.progetto.g5s3m2.payload.users.NewUserDTO;
import sorenrahimi.progetto.g5s3m2.payload.users.NewUserResponseDTO;

import sorenrahimi.progetto.g5s3m2.service.AuthService;
import sorenrahimi.progetto.g5s3m2.service.UsersService;


@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;
    @Autowired
    private UsersService usersService;

    @PostMapping("/login")
    public UserLoginResponseDTO login(@RequestBody UserLoginDTO payload){
        return new UserLoginResponseDTO(this.authService.authenticateDipendenteAndGenerateToken(payload));
    }
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public NewUserResponseDTO save(@RequestBody @Validated NewUserDTO body, BindingResult validation)
    {
        if(validation.hasErrors()) {
            throw new BadRequestException(validation.getAllErrors());
        }

        return new NewUserResponseDTO(this.usersService.save(body).getId());
    }

}
