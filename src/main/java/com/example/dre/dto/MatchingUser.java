package com.example.dre.dto;

import java.util.List;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
@Data
public class MatchingUser {
   private int id;
   private String name;
   private String email;
   private long phone;
   private String password;
   private String gender;
   private int age;
   private List<String> interests;
   private int ageDifference;
   private int matchingInterestCount;
}
