package com.cnsyear;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Lock��
 * 
 * @author jiebaobao
 *
 */
public class TestLock {
	public static void main(String[] args) {
		Ticket ticket = new Ticket();
		for (int i = 0; i < 500; i++) {
			new Thread(ticket).start();
		}
	}
}

/**
 * ��Ʊ
 * 
 * @author jiebaobao
 *
 */
class Ticket implements Runnable {

	private int tick = 100;

	@Override
	public void run() {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (tick > 0) {
				System.out.println("��ʼ��Ʊ��ʣ��" + --tick);
			}
		

	}

}
