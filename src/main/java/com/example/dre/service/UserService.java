package com.example.dre.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.dre.dao.UserDao;
import com.example.dre.dto.MatchingUser;
import com.example.dre.entity.User;
import com.example.dre.responsestructure.ResponseStructure;
import com.example.dre.util.SortByAge;
@Service
public class UserService {
	@Autowired
	private UserDao dao;
	
	@Autowired
	private EmailService emailService;
	


public ResponseEntity<?> saveUser(User user) {
	User savedUser=dao.saveUser(user);
	emailService.sendEmail(savedUser);
	ResponseStructure rs = new ResponseStructure(HttpStatus.OK.value(),"user saved successfully",savedUser);
	ResponseEntity re=ResponseEntity.status(HttpStatus.OK).body(rs);
	return re;
}



public ResponseEntity<?> findAllUsers() {
   
	List<User> users=	dao.findAllUsers();
	ResponseStructure rs = new ResponseStructure(HttpStatus.OK.value(),"user saved successfully",users);
	ResponseEntity re=ResponseEntity.status(HttpStatus.OK).body(rs);
	return re;

	
}



public ResponseEntity<?> findUserById(int id) {
	Optional<User> op=dao.findUserById(id);
	if(op.isEmpty()) {
		throw new RuntimeException("can not find user");
	}
	User u = op.get();
	ResponseStructure rs = new ResponseStructure(HttpStatus.OK.value(),"user saved successfully",u);
	ResponseEntity re=ResponseEntity.status(HttpStatus.OK).body(rs);
	return re;
}



public ResponseEntity<?> findMatch(int id, int top) {
   
	Optional<User> op=	dao.findUserById(id);
    if(op.isEmpty()) {
    	throw new RuntimeException("invalid user id unable to find top matches");
    }
    User user=op.get();
    String gf= null;
    if(user.getGender().equalsIgnoreCase("male")) {
    	gf="female";
    }else {
    	gf="male";
    }
    List<User>users=dao.findByGender(gf);
    List<MatchingUser> matchingUsers=new ArrayList<>();
    
    for(User u:users) {
    	MatchingUser mu = new MatchingUser();
    	
    	mu.setId(u.getId());
    	mu.setName(u.getName());
    	mu.setEmail(u.getEmail());
    	mu.setPhone(u.getPhone());
    	mu.setPassword(u.getPassword());
    	mu.setAge(u.getAge());
    	mu.setInterests(u.getInterests());
    	mu.setGender(u.getGender());
    	
    	int ad=user.getAge() - u.getAge();
    	int aad=Math.abs(ad);
    	mu.setAgeDifference(aad);
    	
    	int mic=0;
    	
    	List<String> interests1 = user.getInterests();
    	List<String> interests2 = u.getInterests();
    	
    	for(String i:interests1) {
    		if(interests2.contains(i)) {
    			mic++;
    		}
    	}
    	mu.setMatchingInterestCount(mic);
    	matchingUsers.add(mu);
    }
    Collections.sort(matchingUsers,new SortByAge());
   
    
    List<MatchingUser> result = new ArrayList<>();
    
    for(MatchingUser mu:matchingUsers) {
    	if(top==0) {
    		break;
    	}
    	else {
    		result.add(mu); 
    		top--;
    		}
    }
    
    ResponseStructure rs = new ResponseStructure(HttpStatus.OK.value(),"top matching user found",result);
	ResponseEntity re=ResponseEntity.status(HttpStatus.OK).body(rs);
	return re;
}


}
