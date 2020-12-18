package io.prospace.galaxymerchant.utils;
/**
 * @author azzuarezh
 *
 */
import java.util.Map;

public class Response {
	
	public Response() {
		
	}
	public Response(int status, Map<String, Object> data) {
		super();
		this.status = status;
		this.data = data;
	}
	
	public int status;
	public Map<String,Object> data;
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Map<String, Object> getData() {
		return data;
	}
	public void setData(Map<String, Object> data) {
		this.data = data;
	}
	

}
