package chcs.common.net;

public class Host {
	private String host;
	private int port;
	
	public Host(String host, int port) {
		setHost(host);
		setPort(port);
	}
	
	public Host(String host) {
		this(host,0);
	}
	
	public Host(String port) {
		this("",port);
	}
	
	public Host() {
		this("",0);
	}
	
	public void setHost(String host) {
		this.host = host;
	}
	
	public String getHost() {
		return(this.host);
	}
	
	public void setPort(int port) {
		this.port = port;
	}
	
	public int getPort() {
		return(this.port);
	}
}