package com.zrgj.listener;

import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * 	����״̬�ı�
 * 		Ӧ�ó���ļ�����
*/
public class OrderStatusListener implements ServletContextListener{

	@Override
	public void contextInitialized(ServletContextEvent sce) {

		// 1�������ݿ��в�ѯ�����������Ķ���(���������Ķ���:��������Ǵ���˲������������֧����ʽ������֧��)
		
		// 2���½�һ����ʱ��
		Timer timer = new Timer();
		// 3��������
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				// �������Ǿ��������(���Ǹ��¶�����״̬),���û��µ�ʱ��Ϊ׼��30���������û�б�����,��ȡ��
			}
		},0);
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
	
	}
}


