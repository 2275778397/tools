package com.cnsyear;

import java.util.Iterator;

/**
 * ģ��򵥵�CAS�㷨
 * @author jiebaobao
 *
 */
public class TestCas {
	public static void main(String[] args) {
		CompareAndSwap compareAndSwap = new  CompareAndSwap();
		
		for (int i = 0; i < 10;i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					int expectedValue = compareAndSwap.getValue();
					//ģ�����÷���
					boolean flag = compareAndSwap.compareAndSet(expectedValue, (int)(Math.random()*100));
					System.out.println(flag);
				}
			}).start();
		}
	}
}

class CompareAndSwap{
	
	private int value;//��ʼֵ
	
	/**
	 * ��ȡ�ڴ�ֵ
	 * @return
	 */
	public synchronized int getValue() {
		return this.value;
	}
	
	/**
	 * �Ƚ�ֵ
	 * @param expectedValue ����ֵ
	 * @param newValue ��ֵ
	 * @return
	 */
	public synchronized int compareAndSwap(int expectedValue,int newValue) {
		int oldValue = this.value;//��ȡһ���ڴ�ֵ
		if(oldValue == expectedValue) {//����ڴ�ֵ������ֵ��ȣ�ִ���޸�
			this.value = newValue;
		}
		return oldValue;		
	}
	
	/**
	 * ����ֵ
	 * @param expectedValue
	 * @param newValue
	 * @return
	 */
	public boolean compareAndSet(int expectedValue,int newValue) {
		return expectedValue == compareAndSwap(expectedValue, newValue);
	}
	
}
