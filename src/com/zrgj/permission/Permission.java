package com.zrgj.permission;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
*/
@Retention(RetentionPolicy.RUNTIME)
@Target(value=ElementType.METHOD)
public @interface Permission {
	
	// Ä£¿é
	String model();
	
	// Â·¾¶
	String url();
	
	// ÃèÊö
	String desc() default "";
}
