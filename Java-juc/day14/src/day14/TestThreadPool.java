package day14;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * �̵߳ĵ���
 * 
 * --ScheduledExecutorService �ӽӿڣ������̵߳ĵ��� 
 * |--ScheduledThreadPoolExecutor ���̳�
 * ThreadPoolExecutor�� ʵ�� ScheduledExecutorService
 * 
 * ScheduledExecutorService newScheduledThreadPool() : �����̶���С���̣߳������ӳٻ�ʱ��ִ������
 *
 * @author jiebaobao
 *
 */
public class TestThreadPool {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10);

		for (int i = 0; i < 10; i++) {
			Future<Integer> result = scheduledExecutorService.schedule(new Callable<Integer>() {

				@Override
				public Integer call() throws Exception {
					int num = new Random().nextInt(100);
					System.out.println(Thread.currentThread().getName() + "ִ�С���");
					return num;
				}

			}, 3, TimeUnit.SECONDS);// ÿ����ִ��

			System.out.println(result.get());
		}

		scheduledExecutorService.shutdown();
	}
}
