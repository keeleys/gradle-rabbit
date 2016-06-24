package com.ttianjun.listen;

import org.springframework.stereotype.Component;

/**
 * @user tianjun
 * @date 16/6/24
 */
@Component
public class ListenDemo {
	public void handleMessage(String message) {
		System.out.println(message);
	}
}
