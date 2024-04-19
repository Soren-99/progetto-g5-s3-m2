package sorenrahimi.progetto.g5s3m2.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import sorenrahimi.progetto.g5s3m2.entities.User;
import sorenrahimi.progetto.g5s3m2.service.UsersService;

@RestController
@RequestMapping("/users")
public class UsersController {
    @Autowired
    private UsersService usersService;

    @GetMapping
    @PreAuthorize("hasAuthority('ORGANIZZATORE_DI_EVENTI')")
    public Page<User> getDipendenti(@RequestParam(defaultValue = "0") int page,
                                    @RequestParam(defaultValue = "10") int size,
                                    @RequestParam(defaultValue = "id") String sortBy) {
        return this.usersService.getUsers(page, size, sortBy);
    }

    @GetMapping("/me")
    public User getProfile(@AuthenticationPrincipal User currentAuthenticatedUser) {
        return currentAuthenticatedUser;
    }

    @PutMapping("/me")
    public User updateProfile(@AuthenticationPrincipal User currentAuthenticatedUser, @RequestBody User updateUser) {
        return this.usersService.findByIdAndUpdate(currentAuthenticatedUser.getId(), updateUser);
    }

    @DeleteMapping("/me")
    public void deleteProfile(@AuthenticationPrincipal User currentAuthenticatedUser) {
        this.usersService.findByIdAndDelete(currentAuthenticatedUser.getId());
    }
}

