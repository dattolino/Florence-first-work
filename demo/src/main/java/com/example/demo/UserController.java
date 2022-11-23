package com.example.demo;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class UserController {

    private final UserRepository userRepository;

    //private final UserServices userServices;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
        //this.userServices = userServices;
    }

    @GetMapping("/users")
    public Iterable<User> getAllUsers(){
        System.out.println("quiiiiiiiii");
        return userRepository.findAll();
    }

    @PostMapping("/users")
    public User createNewUser(@RequestBody User newUser){
        return userRepository.save(newUser);
    }

    @PutMapping("/users")
    public User updateUser(@RequestParam Long id, @RequestBody User userDto){

        User userToUpdate = userRepository.findById(id).orElseThrow();   // va bene gestire così l'eccezione??
        userToUpdate.setNome(userDto.getNome());
        userToUpdate.setCognome(userDto.getCognome());
        userToUpdate.setMail(userDto.getMail());
        userToUpdate.setIndirizzo(userDto.getIndirizzo());

        return userRepository.save(userToUpdate);
    }

    @DeleteMapping("/users")
    public void deleteUser(@RequestParam Long id){
        //userRepository.deleteById(userId);
        //oppure
        User userToDelete = userRepository.findById(id).orElseThrow();
        //userRepository.deleteById();
        userRepository.delete(userToDelete);
    }

    @GetMapping("/users/") //QueryParam
    public List<User> getUsersSearched(@RequestParam String nome, @RequestParam String cognome){
        System.out.println(nome + cognome);
        List<User> selectedUsers = userRepository.findByNomeAndCognome(nome, cognome);

        if(!selectedUsers.isEmpty())
            return selectedUsers;
        else
            throw new ResponseStatusException( //ControllerAdvace
                    HttpStatus.NOT_FOUND, "entity not found"
            );
    }

}
