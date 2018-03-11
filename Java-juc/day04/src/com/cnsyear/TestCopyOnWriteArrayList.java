package com.cnsyear;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/*
 * CopyOnWriteArrayList/CopyOnWriteArraySet : ��д�벢���ơ�
 * ע�⣺��Ӳ�����ʱ��Ч�ʵͣ���Ϊÿ�����ʱ������и��ƣ������ǳ��Ĵ󡣲�������������ʱ����ѡ��
 */
public class TestCopyOnWriteArrayList {

	public static void main(String[] args) {
		HelloThread ht = new HelloThread();
		
		for (int i = 0; i < 10; i++) {
			new Thread(ht).start();
		}
	}
	
}

class HelloThread implements Runnable{
	
	//private static List list = new ArrayList<>();
	//private static List<String> list = Collections.synchronizedList(new ArrayList<String>());a
	private static CopyOnWriteArrayList list = new CopyOnWriteArrayList<>();
	static {
		list.add("1");
		list.add("2");
		list.add("3");
	}

	@Override
	public void run() {
		 Iterator it = list.iterator();
		 
		 while (it.hasNext()) {
			 System.out.println(it.next()+"----");
			 list.add("1110");
		}
		
	}
 
}