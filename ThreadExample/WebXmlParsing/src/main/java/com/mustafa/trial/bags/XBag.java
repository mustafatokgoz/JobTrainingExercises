package com.mustafa.trial.bags;
import java.util.HashMap;
import java.util.Map;

public class XBag{

	Map<String,Object> map =  new HashMap<String,Object>();

	public Map<String, Object> getMap() {
		return map;
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}
	
	public Object getValue(String key) {
		return map.get(key);
	}
	
	public void put(String key,Object value) {
		map.put(key, value);
	}
	
}
