package com.example.dre.responsestructure;

import com.example.dre.entity.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class ResponseStructure<T>  {
	private int status;
	private String message;
	private T body;
	
	
	

}
