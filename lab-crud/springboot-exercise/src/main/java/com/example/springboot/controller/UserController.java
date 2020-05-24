package com.example.springboot.controller;

import com.example.springboot.entity.User;
import com.example.springboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins ="http://localhost:8080")
@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    //添加用户
    @PostMapping("/insert")
    public String insertUser(User user){
        userRepository.save(user);
        return "insert success";
    }

    //删除用户
    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id){
        userRepository.deleteById(id);
        return "delete success";
    }

    //更新用户
    @GetMapping("/update")
    public String updateUser(@RequestParam(name="id") Integer id,@RequestParam(name="name") String newName,@RequestParam(name="email") String newEmail){
        User user=userRepository.findById(id).orElse(null);
        user.setName(newName);
        user.setEmail(newEmail);
        userRepository.save(user);
        return "update success";
    }

    //通过姓名查询
    @GetMapping("/getByName")
    public List<User> getUserByName(@RequestParam String name){
        List<User> users=userRepository.findByName(name);
        return users;
    }

    //通过邮箱
    @GetMapping("/getByEmail")
    public List<User> getUserByAge(@RequestParam String email){
        List<User> users=userRepository.findByEmail(email);
        return users;
    }
    @RequestMapping("/hello")
    public String hello(){
        return "HelloWorld!";
    }
}
