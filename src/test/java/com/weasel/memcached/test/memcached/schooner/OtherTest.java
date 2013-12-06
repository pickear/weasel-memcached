package com.weasel.memcached.test.memcached.schooner;

import junit.framework.TestCase;

import com.weasel.memcached.memcached.schooner.SchoonerSockIOPool;

public class OtherTest extends TestCase {
	private String[] serverList;

	@Override
	protected void setUp() throws Exception {
		String servers = System.getProperty("memcached.host");
		serverList = servers.split(",");
		super.setUp();
	}

	public void testInitilize() {
		// initialize the pool for memcache servers
		SchoonerSockIOPool pool = SchoonerSockIOPool.getInstance("test");
		pool.setServers(serverList);
		pool.setNagle(false);
		pool.setHashingAlg(SchoonerSockIOPool.CONSISTENT_HASH);
		pool.initialize();
		pool.shutDown();
		pool = SchoonerSockIOPool.getInstance("test");
		assertNotNull(pool);
		pool = SchoonerSockIOPool.getInstance("test");
		assertNotNull(pool);
		pool = SchoonerSockIOPool.getInstance("test", true);
		assertNotNull(pool);
		pool = SchoonerSockIOPool.getInstance("test", false);
		assertNull(pool);
		pool = SchoonerSockIOPool.getInstance();
		pool.setServers(serverList);
		pool.setNagle(false);
		pool.setHashingAlg(SchoonerSockIOPool.OLD_COMPAT_HASH);
		pool.initialize();
		pool.shutDown();
		assertNotNull(pool);
		pool = SchoonerSockIOPool.getInstance(true);
		assertNotNull(pool);
		try {
			pool.initialize();
		} catch (Exception e) {
			e.printStackTrace();
		}
		pool.getHost("test");
		pool.getSock("aa");
		pool.getAliveCheck();
		pool.getServers();
		try {
			pool.getConnection("localhost");
		} catch (Exception e) {

		}
		pool.getFailover();
		pool.getHashingAlg();
		pool.getInitConn();
		pool.getMaxBusy();
		pool.getNagle();
		pool.getWeights();
		pool.getSocketTO();
		pool.getSocketConnectTO();
		pool.setFailover(false);
		pool.setInitConn(10);
		pool.setMaxBusyTime(1000);
		pool.setNagle(true);
		pool.setWeights(new Integer[] { 1 });
		pool.setSocketConnectTO(1000);
		pool.setSocketTO(1000);
		pool.setAliveCheck(true);
		pool.isInitialized();
	}

	// public void testUDPSockIO() {
	// try {
	// UDPSockIO io = new UDPSockIO(SchoonerSockIOPool.getInstance(),
	// serverList[0], 1024 * 105, 100, true);
	// io.getHost();
	// io.isAlive();
	// io.write(null);
	// io.flush();
	// io.trueClose();
	// } catch (UnknownHostException e) {
	// e.printStackTrace();
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	// }
	//
	// public void testTcpSockIO() {
	// try {
	// TCPSockIO io = new TCPSockIO(SchoonerSockIOPool.getInstance(),
	// serverList[0], 1024 * 1025, 3000, 3000,
	// false, true);
	// io.isAlive();
	// io.toString();
	// io.readBytes(0);
	// io.hashCode();
	// io.getResponse((short) 0);
	// io.preWrite();
	// io.isConnected();
	// } catch (UnknownHostException e) {
	// e.printStackTrace();
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	// }

}
