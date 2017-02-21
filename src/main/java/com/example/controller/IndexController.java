package com.example.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.dto.Gender;
import com.example.dto.PaymentMethod;
import com.example.dto.User;

@Controller
public class IndexController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("recipient", "World");
        return "index";
    }
    
    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String getuser(Model model) {
    	List<User> users = new ArrayList<User>();
    	User user = new User();
    	user.setName("mio");
    	user.setAge(18);
    	user.setBirthday(new Date());
    	user.setPassword("pass");
    	user.setNumber(1.289);
    
     	User user1 = new User();
    	user1.setName("mio");
    	user1.setAge(18);
    	user1.setBirthday(new Date());
    	user1.setPassword("pass");
    	user1.setNumber(1.289);
    	users.add(user);
    	users.add(user1);
    	model.addAttribute("user", user);
    	model.addAttribute("userList", users);
    	model.addAttribute("customerName", "mio");
    	
    	List<Gender> gs = new ArrayList<>();
    	Gender g1 = new Gender();
    	g1.setId("1");
    	g1.setDescription("Male");
    	Gender g2 = new Gender();
    	g2.setId("2");
    	g2.setDescription("FeMale");
    	gs.add(g1);
    	gs.add(g2);
    	
    	List<PaymentMethod> pmList = new ArrayList<PaymentMethod>();
    	PaymentMethod pm =new PaymentMethod();
    	pm.setId("1");
    	pm.setDescription("what");
    	
    	PaymentMethod pm2 =new PaymentMethod();
    	pm2.setId("2");
    	pm2.setDescription("what2");
    	pmList.add(pm);
    	pmList.add(pm2);
    	
    	model.addAttribute("genders", gs);
    	model.addAttribute("paymentMethods", pmList);
        return "main";
    }
    
    @RequestMapping(value = "/decoupled", method = RequestMethod.GET)
    public String getusers(Model model) {
    	List<User> users = new ArrayList<User>();
    	User user = new User();
    	user.setName("mio");
    	user.setAge(18);
    	user.setBirthday(new Date());
    	user.setPassword("pass");
    	user.setNumber(1.289);
     	User user1 = new User();
    	user1.setName("mio");
    	user1.setAge(18);
    	user1.setBirthday(new Date());
    	user1.setPassword("pass");
    	user1.setNumber(1.289);
    	users.add(user);
    	users.add(user1);
    	model.addAttribute("userList", users);
        return "decoupled";
    }
}
