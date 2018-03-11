package com.cnsyear;

/**
 * volatile�ؼ���
 * 
 * �����������volatile 
 * @author jiebaobao
 *
 */
public class juc03 {

	/**
	 * Main�߳�
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		MyThread3 td = new MyThread3();
		new Thread(td).start();//����һ�����߳�ִ��д�Ĳ���
		
		
		while(true) {
				if(td.isFlag()) {
					System.out.println("Main�߳�ִ��==="+td.isFlag());
					break;
				}
		}
				
	}

}


/**
 * ����һ���߳�
 * 
 * @author jiebaobao
 *
 */
class MyThread3 implements Runnable {
	private volatile boolean flag = false;// һ������Ĭ��false

	@Override
	public void run() {
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//����ִ��д�Ĳ���
		flag = true;
		System.out.println("�߳�һִ��==="+flag);
	}
	

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

}

