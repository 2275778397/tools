package com.cnsyear;

/**
 * volatile�ؼ���
 * 
 * @author jiebaobao
 *
 */
public class juc01 {

	/**
	 * Main�߳�
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		MyThread td = new MyThread();
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
class MyThread implements Runnable {
	private boolean flag = false;// һ������Ĭ��false

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
