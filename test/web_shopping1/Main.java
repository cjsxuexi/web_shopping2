package web_shopping1;

import java.io.IOException;

public class Main {
	
	public static void main(String[] args) throws IOException {
		String command = "cmd /c mysqldump -uroot -p123456 day1224>d:/xxhe.sql" ;
		
		Runtime.getRuntime().exec(command);
	}
}
