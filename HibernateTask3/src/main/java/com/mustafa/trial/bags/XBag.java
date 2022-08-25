package com.mustafa.trial.bags;
import java.util.HashMap;
import java.util.Map;

public class XBag{

	Map<BagKey,Object> map =  new HashMap<BagKey,Object>();

	public Map<BagKey, Object> getMap() {
		return map;
	}

	public void setMap(Map<BagKey, Object> map) {
		this.map = map;
	}
	
	
}
