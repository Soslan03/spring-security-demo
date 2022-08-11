package ru.badtiev.springsecuritydemo.rest;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.badtiev.springsecuritydemo.model.User;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api/v1/users")
public class UserRestController {
    private long id_count=0;
    private List<User> USERS = Stream.of(
            new User(id_count++,"tom","Jones",35,"badti2014@yandex.ru"),
            new User(id_count++,"bob","marley",45,"badti2014@yandex.ru"),
            new User(id_count++,"bill","klinton",95,"badti2014@yandex.ru")

    ).collect(Collectors.toList());

    @GetMapping
    public List<User>  getAll(){
        return USERS;
    }
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('users:read')")
    public User getById(@PathVariable Long id){
        return USERS.stream().filter(user->user.getId().equals(id)).findFirst().orElse(null);
    }
    @PostMapping
    @PreAuthorize("hasAuthority('users:write')")
    public User saveUser(@RequestBody User user){
        this.USERS.add(user);
        return user;
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('users:write')")
    public void deleteById(@PathVariable Long id){
        this.USERS.removeIf(user->user.getId().equals(id));

    }
}
