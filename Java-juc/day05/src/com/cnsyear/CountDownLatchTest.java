package com.cnsyear;

import java.security.Timestamp;
import java.sql.Time;
import java.util.Date;
import java.util.concurrent.CountDownLatch;

/**
 * ������ �����ĳ������ʱ��ֻ�е����е��̶߳�������ִ�У�
 * 
 * @author jiebaobao
 *
 */
public class CountDownLatchTest {
	public static void main(String[] args) {
		CountDownLatch latch = new CountDownLatch(500);//�߳�������ͳ�ʼ����һ��
		MyLatch myLatch = new MyLatch(latch);
		long start = System.currentTimeMillis();

		for (int i = 0; i < 500; i++) {
			new Thread(myLatch).start();
		}

		try {
			latch.await();// �ȴ�
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		long end = System.currentTimeMillis();
		System.out.println("�ܺ�ʱ��" + (end - start));

	}
}

/**
 * ���߳�
 * 
 * @author jiebaobao
 *
 */
class MyLatch implements Runnable {
	private CountDownLatch latch;

	public MyLatch(CountDownLatch latch) {
		this.latch = latch;
	}

	@Override
	public void run() {
		synchronized (this) {// ��ֹ��������
			try {
				Thread.sleep(20);
				System.out.println(Thread.currentThread().getName() + ">>>> �������");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				// ûִ����һ����-1
				latch.countDown();
			}
		}

	}

}
