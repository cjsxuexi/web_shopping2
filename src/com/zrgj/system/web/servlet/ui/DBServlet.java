package com.zrgj.system.web.servlet.ui;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zrgj.system.web.servlet.BaseServlet;

/**
 * 	���ݿ����
*/
public class DBServlet extends BaseServlet{

	private static final long serialVersionUID = 23556013105853041L;
	
	// ���ݱ���
	public void backup(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			// 1�����Ȼ�ȡ��Ҫ���ݵ��ĸ�Ŀ¼
			String backUpDir = req.getServletContext().getRealPath("/backup");
			
			// 2�����ɱ����ļ�������
			String fileName = System.currentTimeMillis() + ".sql";
			
			// 3����������
			String command = "cmd /c mysqldump -uroot -p123456 day1224 > " + backUpDir + "//" + fileName;
			
			// 4��ִ�б�������
			Runtime.getRuntime().exec(command);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	

}
