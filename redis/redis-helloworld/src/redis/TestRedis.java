package redis;

import org.junit.Test;

/**
 * Jedis����
 * @author jiebaobao
 *
 */
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class TestRedis {
	/**
	 * ��������
	 */
	@Test
	public void testConn() {

		// ��ȡjedis���� ����ip�Ͷ˿ں�
		Jedis jedis = new Jedis("192.168.2.102", 6379);
		System.out.println("���ӳɹ�������");
		jedis.set("test", "Hello Jedis!");
		String test = jedis.get("test");
		System.out.println(test);

	}
	
	
	/**
	 * �������ӳ�
	 */
	@Test
	public void testPool() {
		// ��ȡ���ӳص����ö���
		JedisPoolConfig conf = new JedisPoolConfig();
		// �������������
		conf.setMaxTotal(100);
		// ����������������
		conf.setMaxIdle(10);
		JedisPool jedisPool = new JedisPool(conf, "192.168.2.102", 6379);
		//��ȡ���Ķ���
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			jedis.set("test1", "Hello JedisPool!");
			
			String str = jedis.get("test1");
			System.out.println(str);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(jedis!=null) {
				jedis.close();
			}
		}
		
	}
}
