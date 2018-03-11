package day02;

/**
 * i++ ԭ���Ե�����:
 *
 * 
 * @author jiebaobao
 *
 */
public class TestAtomicDemo {
	public static void main(String[] args) {
		MyThread td = new MyThread();
		for (int i = 0; i < 10; i++) {
			new Thread(td).start();
		}
	}
}

class MyThread implements Runnable {

	private int num = 0;
//	private volatile int num = 0; //ʹ��volatileҲ�׷�

	@Override
	public void run() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(getNum() + "============== \t" + Thread.currentThread().getName());
	}

	/**
	 * ��++����
	 * 
	 * @return
	 */
	public int getNum() {
		return num++;
	}

}
