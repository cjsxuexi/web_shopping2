package com.zrgj.listener;

import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * 	订单状态改变
 * 		应用程序的监听器
*/
public class OrderStatusListener implements ServletContextListener{

	@Override
	public void contextInitialized(ServletContextEvent sce) {

		// 1、从数据库中查询出符合条件的订单(符合条件的定义:这个订单是待审核并且这个订单的支付方式是在线支付)
		
		// 2、新建一个定时器
		Timer timer = new Timer();
		// 3、做任务
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				// 就是我们具体的任务(就是更新订单的状态),以用户下单时间为准到30分钟如果还没有被付款,就取消
			}
		},0);
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
	
	}
}


