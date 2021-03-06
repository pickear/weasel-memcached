package com.weasel.memcached.memcached.schooner;

import org.apache.commons.pool.BasePoolableObjectFactory;
import org.apache.commons.pool.impl.GenericObjectPool;

import com.weasel.memcached.memcached.schooner.SchoonerSockIOPool.TCPSockIO;
import com.weasel.memcached.memcached.schooner.SchoonerSockIOPool.UDPSockIO;

/**
 * @author Meng Li
 * @since 2.6.0
 * @see SchoonerSockIOFactory
 */
@SuppressWarnings("rawtypes")
public class SchoonerSockIOFactory extends BasePoolableObjectFactory {

	protected GenericObjectPool sockets;

	protected String host;

	protected int bufferSize;

	protected int socketTO;

	protected int socketConnectTO;

	protected boolean isTcp;

	protected boolean nagle;

	public SchoonerSockIOFactory(String host, boolean isTcp, int bufferSize, int socketTO, int socketConnectTO,
			boolean nagle) {
		super();
		this.host = host;
		this.isTcp = isTcp;
		this.bufferSize = bufferSize;
		this.socketTO = socketTO;
		this.socketConnectTO = socketConnectTO;
		this.nagle = nagle;
	}

	@Override
	public Object makeObject() throws Exception {
		SchoonerSockIO socket = createSocket(host);
		return socket;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void destroyObject(Object obj) throws Exception {
		super.destroyObject(obj);
		((SchoonerSockIO) obj).trueClose();
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean validateObject(Object obj) {
		return super.validateObject(obj);
	}

	/**
	 * Creates a new SockIO obj for the given server.
	 * 
	 * If server fails to connect, then return null and do not try<br/>
	 * again until a duration has passed. This duration will grow<br/>
	 * by doubling after each failed attempt to connect.
	 * 
	 * @param host
	 *            host:port to connect to
	 * @return SockIO obj or null if failed to create
	 */
	protected final SchoonerSockIO createSocket(String host) throws Exception {
		SchoonerSockIO socket = null;

		if (isTcp) {
			socket = new TCPSockIO(sockets, host, bufferSize, socketTO, socketConnectTO, nagle);
		} else {
			socket = new UDPSockIO(sockets, host, bufferSize, socketTO);
		}

		return socket;
	}

	public void setSockets(GenericObjectPool sockets) {
		this.sockets = sockets;
	}

}
