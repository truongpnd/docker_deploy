package com.test.demo.container;


import com.test.demo.dto.UserDTO;
import com.test.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

@RestController
public class UserContainer {
    @Autowired
    private UserService userService;

    @GetMapping("/findAll")
    public ResponseEntity getUsers() {
       try{
           return ResponseEntity.ok(userService.getAllUsers());
       }catch(Exception e){
           return ResponseEntity.badRequest().body(e.getMessage());
       }
    }

    @PostMapping(value = "/add")
    public ResponseEntity addUser(@RequestBody UserDTO userDTO) {
       try{
           return ResponseEntity.ok(userService.addUser(userDTO));
       }catch(Exception e){
           e.printStackTrace();
           return ResponseEntity.badRequest().body(e.getMessage());
       }
    }

    @PutMapping("/update")
    public ResponseEntity updateUser(@RequestBody UserDTO userDTO) {
       try{
           return ResponseEntity.ok(userService.updateUser(userDTO));
       }catch(Exception e){
           return ResponseEntity.badRequest().body(e.getMessage());
       }
    }

    @DeleteMapping("/delete/{username}")
    public ResponseEntity deleteUser(@PathVariable String username) {
       try{
           userService.deleteUser(username);
           return ResponseEntity.ok("User deleted successfully");
       }catch(Exception e){
           return ResponseEntity.badRequest().body(e.getMessage());
       }
    }

    @GetMapping("/find/{username}")
    public ResponseEntity getUser(@PathVariable String username) {
       try{
           return ResponseEntity.ok(userService.getUser(username));
       }catch(Exception e){
           return ResponseEntity.badRequest().body(e.getMessage());
       }
    }
    @GetMapping("/a")
    public String a() {
        String cmd = "pg_dump --dbname=jdbc:postgresql://postgresql-113528-0.cloudclusters.net:19937/test"
                + " --no-owner --no-acl" + " --clean --if-exists -f sql/db-" + "test" + "truong" + ".sql";

        Process proc;
        try {
            proc = Runtime.getRuntime().exec(cmd);
            InputStream stderr = proc.getErrorStream();
            InputStreamReader isr = new InputStreamReader(stderr);
            BufferedReader br = new BufferedReader(isr);
            String line = null;

            while ((line = br.readLine()) != null)
                System.out.println(line);

            int exitVal = proc.waitFor();
            System.out.println("Process pg_dump exitValue: " + exitVal);
        } catch (Throwable t) {
            t.printStackTrace();
        }
       return "done";
    }
}
