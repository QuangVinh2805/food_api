package org.example.food_api.services;

import org.apache.commons.lang3.RandomStringUtils;
import org.example.food_api.models.User;
import org.example.food_api.repository.UserRepository;
import org.example.food_api.request.LoginRequest;
import org.example.food_api.request.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    private JavaMailSender emailSender;

    public ResponseEntity<List<User>> listAllUser() {
        List<User> listUser = userRepository.findAll();
        if (listUser.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<User>>(listUser, HttpStatus.OK);
    }

    public User findUser(@PathVariable("id") long id) {
        User contact = userRepository.findById(id);
        if (contact == null) {
            ResponseEntity.notFound().build();
        }
        return contact;
    }

    public User findUserById(@RequestParam("id") long id) {
        User contact = userRepository.findById(id);
        if (contact == null) {
            ResponseEntity.notFound().build();
        }
        return contact;
    }

    public ResponseEntity<User> findUserLogin(LoginRequest request) {
        String username = request.getUsername();
        String password = request.getPassword();
        System.out.println(username);
        System.out.println(password);
        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            String message = "username and password are required";
            System.out.println(message);
            return null;
        }
        User user = userRepository.findByUsername(username);
        if (user == null) {
            String message = "username not found!";
            System.out.println(message);
            return null;
        }
        if (user.getPassword().equals(password)) {
            System.out.println("");
            if (user.getRoleId() == User.ROLE_ADMIN) {
                System.out.println("Hello ADMIN");
            } else {
                System.out.println("Hello customer");
            }
        } else {
            System.out.println("password does not match");
            return new ResponseEntity<User>(user, HttpStatus.UNAUTHORIZED);
        }
        System.out.println(user);
        return new ResponseEntity<User>(user, HttpStatus.OK);

    }

    public void save(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User object cannot be null");
        }

        // Check that all required fields are not null or empty
        if (user.getUsername() == null || user.getUsername().isEmpty()) {
            throw new IllegalArgumentException("Username cannot be null or empty");
        }
        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            throw new IllegalArgumentException("Password cannot be null or empty");
        }
        if (user.getBirthday() == null) {
            throw new IllegalArgumentException("Birthday cannot be null");
        }
        if (user.getName() == null || user.getName().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        if (user.getAddress() == null || user.getAddress().isEmpty()) {
            throw new IllegalArgumentException("Address cannot be null or empty");
        }
        if (user.getEmail() == null || user.getEmail().isEmpty()) {
            throw new IllegalArgumentException("Email cannot be null or empty");
        }
        if (user.getRoleId() < 0) { // Assuming roleId should be a non-negative value
            throw new IllegalArgumentException("Role ID cannot be negative");
        }
        if (user.getPhoneNumber() <= 0) { // Assuming phoneNumber should be a positive value
            throw new IllegalArgumentException("Phone number must be a positive number");
        }


        userRepository.save(user);
    }

    public ResponseEntity<User> updateUser(User user) {
        if (user == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        if (user.getId() == null || user.getId() <= 0) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        User existingUser = userRepository.findById(user.getId()).orElse(null);
        if (existingUser == null) {
            String message = "User ID not found: " + user.getId();
            System.out.println(message);
            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
        }

        // Kiểm tra nếu username bị trùng (nhưng cho phép giữ nguyên username cũ)
        if (!existingUser.getUsername().equals(user.getUsername())) {
            if (userRepository.findByUsername(user.getUsername()) != null) {
                String message = "Username đã tồn tại: " + user.getUsername();
                System.out.println(message);
                return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
            }
        }

        // Cập nhật user trong database
        existingUser.setName(user.getName());
        existingUser.setBirthday(user.getBirthday());
        existingUser.setPhoneNumber(user.getPhoneNumber());
        existingUser.setEmail(user.getEmail());
        existingUser.setAddress(user.getAddress());
        // Không cập nhật lại username nếu không thay đổi
        existingUser.setUsername(user.getUsername());

        userRepository.save(existingUser);
        return new ResponseEntity<>(existingUser, HttpStatus.OK);
    }

    public ResponseEntity<User> createUser(UserRequest request) {
        if (request == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        User user = userRepository.findByUsername(request.getUsername());
        if (user != null) {
            String message = "User đã tồn tại: " + request.getUsername();
            System.out.println(message);
            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
        }
        user = User.builder()
                .username(request.getUsername())
                .password(request.getPassword())
                .birthday(request.getBirthday())
                .name(request.getName())
                .address(request.getAddress())
                .email(request.getEmail())
                .phoneNumber(request.getPhoneNumber())
                .roleId(request.getRoleId())
                .build();

        // Generate a random password
        String randomPassword = RandomStringUtils.randomAlphanumeric(8);

        // Update the user's password
        user.setPassword(randomPassword); // You might want to hash this before saving to the database

        // Send email with the random password
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("vinhdaumoi2805.com");
        message.setTo(user.getEmail());
        message.setSubject("Thông báo");
        message.setText("Chúc mừng bạn đã đăng ký tài khoản thành công. Mật khẩu của bạn là: " + randomPassword);
        emailSender.send(message);
        userRepository.save(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }


    public ResponseEntity<String> deleteUser(Long id) {
        Long userId = userRepository.findIdByUserId(id);
        if (userId == null) {
            String message = "user id not found";
            System.out.println(message + userId);
            return new ResponseEntity(message, HttpStatus.FORBIDDEN);

        }
        userRepository.deleteById(userId);
        return new ResponseEntity(HttpStatus.OK);
    }

}
