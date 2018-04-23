package com.zrgj.system.web.servlet.ui;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zrgj.system.web.servlet.BaseServlet;

/**
 * 	数据库管理
*/
public class DBServlet extends BaseServlet{

	private static final long serialVersionUID = 23556013105853041L;
	
	// 数据备份
	public void backup(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			// 1、首先获取到要备份到哪个目录
			String backUpDir = req.getServletContext().getRealPath("/backup");
			
			// 2、生成备份文件的名称
			String fileName = System.currentTimeMillis() + ".sql";
			
			// 3、备份命令
			String command = "cmd /c mysqldump -uroot -p123456 day1224 > " + backUpDir + "//" + fileName;
			
			// 4、执行备份命令
			Runtime.getRuntime().exec(command);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	

}
