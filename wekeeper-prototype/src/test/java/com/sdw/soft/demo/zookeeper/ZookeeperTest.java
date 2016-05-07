package com.sdw.soft.demo.zookeeper;

import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.junit.Test;

/**
 * author shangyd
 * date 2016年2月3日
 **/
public class ZookeeperTest {

	private static final int SESSION_TIMEOUT = 5000;
	private static final String CONNECTION_STRING = "127.0.0.1:2181,127.0.0.1:2182,127.0.0.1:2183";
	private static CountDownLatch countDown = new CountDownLatch(1);
	@Test
	public void test01() throws Exception{
		final ZooKeeper zookeeper = new ZooKeeper(CONNECTION_STRING, SESSION_TIMEOUT, new Watcher() {
			@Override
			public void process(WatchedEvent event) {
				if(KeeperState.SyncConnected == event.getState()){
					System.out.println(event.getState().name()+","+event.getType().name());
					if(Event.EventType.None == event.getType() && event.getPath() == null){
						countDown.countDown();
					}else if(Event.EventType.NodeCreated == event.getType()){
						System.out.println("has found the node path =[" + event.getPath() + "] has been created.");
					}
				}
			}
		});
		countDown.await();
		System.out.println("deal start ... ...");
		Stat stat = zookeeper.exists("/zookeeper", false);
		if(stat == null){
			System.out.println("there is not this path...");
		}else{
			System.out.println("aversion="+stat.getAversion() + ",ctime=" + stat.getCtime() + ",cversion=" + stat.getCversion() + ",czxid=" + stat.getCzxid() + ",datalength=" +
					stat.getDataLength() + ",ephemeralOwner=" + stat.getEphemeralOwner() + ",mtime=" + stat.getMtime() + ",mzxid=" + stat.getMzxid() + ",numChildren=" + stat.getNumChildren()
					+ ",pzxid=" + stat.getPzxid() + ",version=" + stat.getVersion());
		}
		String path = "/newyear";
		Stat st = zookeeper.exists(path, true);
		if(st == null){
			zookeeper.addAuthInfo("digest", "foo:true".getBytes());
			String result = zookeeper.create(path, "newyears".getBytes(), Ids.CREATOR_ALL_ACL, CreateMode.EPHEMERAL_SEQUENTIAL);
			System.out.println("create path [" + path + "] successfully! result=" + result);
			try {
				byte[] data = zookeeper.getData(path, false, null);
				System.out.println("get data =" + new String (data));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		new Thread(new Runnable(){

			@Override
			public void run() {
				try {
					String result  = zookeeper.create("/thread", "one thread".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
					System.out.println("one thread create a ephemeral path= [" + result + "]");
				} catch (KeeperException e) {
					e.printStackTrace();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
		}).start();
//		Thread.sleep(Integer.MAX_VALUE);
		while(true){}
	}
}
