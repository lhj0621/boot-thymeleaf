package idu.cs.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import idu.cs.domain.User;
import idu.cs.entity.UserEntity;
import idu.cs.exception.ResourceNotFoundException;
import idu.cs.repository.UserRepository;
import idu.cs.service.UserService;

@Controller //@Component, @Service, @Repository
//Spring Framwork에게 이 클래스로 부터 작성된 객체는 Controller 역활을 함을 알려줌
//Sprng 이 클래스로 부터 Bean 객체를
public class UserController {
  // @Autowired UserRepository userRepo; // Dependency Injection
	@Autowired UserService userService;
	
	
   @GetMapping("/")
   public String home(Model model) {
      return "index";
   }
   @GetMapping("/user-login-form")
   public String getLoginForm(Model model) {
      return "login";
   }
   
   @PostMapping("/login")
   public String loginUser(@Valid UserEntity user, HttpSession session) {
      System.out.println("login process : "+ user.getUserId());
      User sessionUser = userService.getUserByUserId(user.getUserId());
    		  //userRepo.findByUserId(user.getUserId());
      if(sessionUser == null) {
    	  System.out.println("id error");
    	  return "redirect:/uesr-login-form";
      }
      if(!sessionUser.getUserPw().equals(user.getUserPw())) {
    	  System.out.println("pw error");
    	  return "redirect:/uesr-login-form";
      }
	   //userRepo.save(user);
      session.setAttribute("user", sessionUser);
      return "redirect:/";
   }
   
   @GetMapping("/logout")
   public String logoutUser(HttpSession session) {
	  session.removeAttribute("user");
	  //session.invalidate();//세션에 있는걸 모두 다 지워버림
	   return "redirect:/";
   }
   @GetMapping("/users")
   public String getAllUser(Model model,HttpSession session) {
		model.addAttribute("users", userService.getUsers());
		return "userlist";
		
      //model.addAttribute("users", userRepo.findAll());
      //return "userlist";
   }
   @GetMapping("/user-register-form")
   public String getRegForm(Model model) {
      return "register";
   }
   
   @GetMapping("/user-update-form")
   public String getupdateForm(Model model) {
      return "update";
   }
   
   @PostMapping("/users")
   public String createUser(@Valid  User user, Model model) {
	  userService.saveUser(user);
      return "redirect:/users";//get 방식으로 해당 url에 재지정함
   }
   /*
   @GetMapping("/users/{id}")
   public String getUserById(@PathVariable(value = "id") Long userId, Model model)
         throws ResourceNotFoundException {
      UserEntity user = userRepo.findById(userId).get(); //.orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId));
      //model.addAttribute("id", user.getId()); // 이 값을 user.html로 넘겨주는것. 이름을 꼭 아이디값으로 하지 않아도 됨
      //model.addAttribute("name", user.getName());
      //model.addAttribute("company", user.getCompany());
      model.addAttribute("user", user); // user를 통채로 가져오기
      return "user";
      //return ResponseEntity.ok().body(user);
   }
   @GetMapping("/users/fn")
   public String getUserByName(@Param(value="name") String name, Model model)
         throws ResourceNotFoundException {
      List<UserEntity> users = userRepo.findByName(name); //.orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId));
      model.addAttribute("users", users);
      return "userlist";
      //return ResponseEntity.ok().body(user);
   }
   
   @PutMapping("/users/{id}") // @PatchMapping
   public String updateUser(@PathVariable(value = "id") Long userId, @Valid UserEntity userDetails, Model model,HttpSession session) {
      UserEntity user = userRepo.findById(userId).get(); // user는 DB로 부터 읽어온 객체 
      System.out.println("들어옴"+userId);
      user.setUserId(userDetails.getUserId()); // userDetails가 전송한 객체
      user.setUserPw(userDetails.getUserPw());
      user.setName(userDetails.getName());
      user.setCompany(userDetails.getCompany());
      userRepo.save(user); // 저장!
      session.setAttribute("user", user);
      return "redirect:/users";
   }
   
   @DeleteMapping("/users/{id}")
   public String deleteUser(@PathVariable(value = "id") Long userId, Model model) {
      UserEntity user = userRepo.findById(userId).get();
      userRepo.delete(user);
      model.addAttribute("name", user.getName());
      return "user-deleted";
   }
   */
}