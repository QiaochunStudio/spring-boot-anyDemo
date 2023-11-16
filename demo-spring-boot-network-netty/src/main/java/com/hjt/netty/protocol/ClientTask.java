package com.hjt.netty.protocol;

/**
 * 说明：
 *
 *
 */
public class ClientTask implements Runnable {

	/**
	 * 
	 */
	public ClientTask() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			ProtocolClient client = new ProtocolClient("192.168.0.68", 8082);
			
			client.run();
 
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
