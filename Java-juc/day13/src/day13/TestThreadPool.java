package day13;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * һ���̳߳أ��ṩ��һ���̶߳��У������б��������еȴ�״̬���̡߳������˴��������ٶ��⿪�����������Ӧ���ٶȡ�
 * 
 * �����̳߳ص���ϵ�ṹ��
 * 	java.util.concurrent.Executor : �����̵߳�ʹ������ȵĸ��ӿ�
 * 		|--**ExecutorService �ӽӿ�: �̳߳ص���Ҫ�ӿ�
 * 			|--ThreadPoolExecutor �̳߳ص�ʵ����
 * 			|--ScheduledExecutorService �ӽӿڣ������̵߳ĵ���
 * 				|--ScheduledThreadPoolExecutor ���̳� ThreadPoolExecutor�� ʵ�� ScheduledExecutorService
 * 
 * ���������� : Executors 
 * ExecutorService newFixedThreadPool() : �����̶���С���̳߳�
 * ExecutorService newCachedThreadPool() : �����̳߳أ��̳߳ص��������̶������Ը��������Զ��ĸ���������
 * ExecutorService newSingleThreadExecutor() : ���������̳߳ء��̳߳���ֻ��һ���߳�
 * 
 * ScheduledExecutorService newScheduledThreadPool() : �����̶���С���̣߳������ӳٻ�ʱ��ִ������
 *
 * @author jiebaobao
 *
 */
public class TestThreadPool {
	public static void main(String[] args) {
		ThreadDemo td = new ThreadDemo();	
		//1.�����̳߳�
		ExecutorService pool  = Executors.newFixedThreadPool(5);
		//2.Ϊ�̳߳��е��̷߳�������
		for (int i = 0; i < 10; i++) {
			pool.submit(td);
		}
		//3.�ر��̳߳�
		pool.shutdown();
		
		
	}
}

/**
 * �߳�
 * 
 * @author jiebaobao
 *
 */
class ThreadDemo implements Runnable {

	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName() + "ִ�С���");
	}

}


//���
/*
pool-1-thread-2ִ�С���
pool-1-thread-5ִ�С���
pool-1-thread-2ִ�С���
pool-1-thread-3ִ�С���
pool-1-thread-3ִ�С���
pool-1-thread-3ִ�С���
pool-1-thread-4ִ�С���
pool-1-thread-1ִ�С���
pool-1-thread-2ִ�С���
pool-1-thread-5ִ�С���*/
