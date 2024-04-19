package sorenrahimi.progetto.g5s3m2.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sorenrahimi.progetto.g5s3m2.entities.User;
import sorenrahimi.progetto.g5s3m2.exceptions.BadRequestException;
import sorenrahimi.progetto.g5s3m2.exceptions.NotFoundException;
import sorenrahimi.progetto.g5s3m2.payload.users.NewUserDTO;
import sorenrahimi.progetto.g5s3m2.repositories.UsersRepository;

@Service
public class UsersService {

    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private PasswordEncoder bcrypt;
    public Page<User> getUsers(int page, int size, String sortBy){
        if(size > 100) size = 100;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return this.usersRepository.findAll(pageable);
    }
    public User save(NewUserDTO body){

        this.usersRepository.findByEmail(body.email()).ifPresent(

                user -> {
                    throw new BadRequestException("L'email " + user.getEmail() + " è già in uso!");
                }
        );

        User newUser = new User(body.username(), body.nome(), body.cognome(), body.email(), bcrypt.encode(body.password()));

        return usersRepository.save(newUser);
    }
    public User findById(int id){
        return this.usersRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }
    public User findByIdAndUpdate(int id, User modifiedUser){
        User found = this.findById(id);
        found.setUsername(modifiedUser.getUsername());
        found.setNome(modifiedUser.getNome());
        found.setCognome(modifiedUser.getCognome());
        found.setEmail(modifiedUser.getEmail());
        found.setPassword(modifiedUser.getPassword());
        return this.usersRepository.save(found);
    }
    public void findByIdAndDelete(int id){
        User found = this.findById(id);
        this.usersRepository.delete(found);
    }
    public User findByEmail(String email) {
        return usersRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("Utente con email " + email + " non trovato!"));
    }
}
