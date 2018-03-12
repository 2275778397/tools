package com.cnsyear;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException; 
import java.util.concurrent.FutureTask;

/**
 * ʵ���̵߳ĵ����ַ��� -- ʹ��Callable�ӿڴ������߳�
 * 
 * @author jiebaobao
 *
 */
public class TestCallable {
	public static void main(String[] args) {
		MyCallable myCallable = new MyCallable();
		//1.ִ��Callable����ҪFutureTaskʵ�����֧�֣����ڽ�����������
		FutureTask<Integer> future = new FutureTask<>(myCallable);
		
		new Thread(future).start();
		
		try {
			System.out.println("-----------��û��ִ�����̵߳�ʱ�����߳��ڵȴ���------------");
			int sum = future.get();//��ȡֵ
		
			System.out.println("�ܺͣ�"+sum);
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}

/**
 * �Զ���ʵ��Callable�߳��� �ַ���ֵ �����쳣
 * 
 * @author jiebaobao
 *
 */
class MyCallable implements Callable<Integer> {

	@Override
	public Integer call() throws Exception {
		int sum = 0;
Thread.sleep(5000);
		for (int i = 0; i < 100000; i++) {
			sum += i;
		}
		System.out.println(Thread.currentThread().getName() + "-----������ɣ�");
		return sum;
	}
}