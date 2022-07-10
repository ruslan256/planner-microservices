package org.ruslan.todo.mc.users.controller;

import org.ruslan.todo.mc.entity.User;
import org.ruslan.todo.mc.users.search.UserSearchValues;
import org.ruslan.todo.mc.users.service.UserService;
import org.ruslan.todo.mc.utils.rest.api.IDataServiceClient;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    public static final String ID_COLUMN = "id";
    private final UserService userService;
    private final IDataServiceClient dataServiceClient;

    public UserController(UserService userService, IDataServiceClient dataServiceClient) {
        this.userService = userService;
        this.dataServiceClient = dataServiceClient;
    }

    @PostMapping("/add")
    public ResponseEntity<User> add(@RequestBody User user) {

        if (user.getId() != null && user.getId() != 0) {
            return new ResponseEntity("redundant param: id MUST be null", HttpStatus.NOT_ACCEPTABLE);
        }

        if (user.getEmail() == null || user.getEmail().trim().length() == 0) {
            return new ResponseEntity("missed param: email", HttpStatus.NOT_ACCEPTABLE);
        }

        if (user.getPassword() == null || user.getPassword().trim().length() == 0) {
            return new ResponseEntity("missed param: password", HttpStatus.NOT_ACCEPTABLE);
        }

        if (user.getUsername() == null || user.getUsername().trim().length() == 0) {
            return new ResponseEntity("missed param: username", HttpStatus.NOT_ACCEPTABLE);
        }

        // add the new user
        user = userService.add(user);

        if (user != null) {
            // fill in initial user data (in parallel flow)
            dataServiceClient.initUserDataAsync(user.getId()).subscribe(result -> {
                        System.out.println("user populated: " + result);
                    }
            );
        }

        return ResponseEntity.ok(user);
    }

    @PutMapping("/update")
    public ResponseEntity<User> update(@RequestBody User user) {

        if (user.getId() == null || user.getId() == 0) {
            return new ResponseEntity("missed param: id", HttpStatus.NOT_ACCEPTABLE);
        }

        if (user.getEmail() == null || user.getEmail().trim().length() == 0) {
            return new ResponseEntity("missed param: email", HttpStatus.NOT_ACCEPTABLE);
        }

        if (user.getPassword() == null || user.getPassword().trim().length() == 0) {
            return new ResponseEntity("missed param: password", HttpStatus.NOT_ACCEPTABLE);
        }

        if (user.getUsername() == null || user.getUsername().trim().length() == 0) {
            return new ResponseEntity("missed param: username", HttpStatus.NOT_ACCEPTABLE);
        }

        userService.update(user);

        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/deletebyid")
    public ResponseEntity deleteByUserId(@RequestBody Long userId) {

        try {
            userService.deleteByUserId(userId);
        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
            return new ResponseEntity("userId = " + userId + " not found", HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/deletebyemail")
    public ResponseEntity deleteByUserEmail(@RequestBody String email) {

        try {
            userService.deleteByUserEmail(email);
        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
            return new ResponseEntity("email = " + email + " not found", HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    // search User by ID
    @PostMapping("/id")
    public ResponseEntity<User> findById(@RequestBody Long id) {

        Optional<User> userOptional = userService.findById(id);

        try {
            if (userOptional.isPresent()) {
                return ResponseEntity.ok(userOptional.get());
            }
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }

        return new ResponseEntity("id = " + id + " not found", HttpStatus.NOT_ACCEPTABLE);
    }

    // search User by email
    @PostMapping("/email")
    public ResponseEntity<User> findByEmail(@RequestBody String email) {

        User user = null;

        try {
            user = userService.findByEmail(email);
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            return new ResponseEntity("email = " + email + " not found", HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok(user);
    }

    // search by UserSearchValues
    @PostMapping("/search")
    public ResponseEntity<Page<User>> search(@RequestBody UserSearchValues userSearchValues) throws ParseException {

        String email = userSearchValues.getEmail() != null ? userSearchValues.getEmail() : null;

        String username = userSearchValues.getUsername() != null ? userSearchValues.getUsername() : null;

        String sortColumn = userSearchValues.getSortColumn() != null ? userSearchValues.getSortColumn() : null;
        String sortDirection = userSearchValues.getSortDirection() != null ? userSearchValues.getSortDirection() : null;

        Integer pageNumber = userSearchValues.getPageNumber() != null ? userSearchValues.getPageNumber() : null;
        Integer pageSize = userSearchValues.getPageSize() != null ? userSearchValues.getPageSize() : null;

        // sorting direction
        Sort.Direction direction = sortDirection == null || sortDirection.trim().length() == 0 || sortDirection.trim().equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;

        Sort sort = Sort.by(direction, sortColumn, ID_COLUMN);

        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sort);

        Page<User> result = userService.findByParams(email, username, pageRequest);

        return ResponseEntity.ok(result);
    }

}