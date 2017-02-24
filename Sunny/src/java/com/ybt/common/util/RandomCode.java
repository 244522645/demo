package com.ybt.common.util;

import java.util.Random;

public class RandomCode {
	public static String getCode(){
		 Random r = new Random(); 
		 Double d = r.nextDouble(); 
		 String s = d + ""; 
		 s=s.substring(3,3+6); 
		 return s;
	}
	
	public static void main(String args[]){
    }
}
