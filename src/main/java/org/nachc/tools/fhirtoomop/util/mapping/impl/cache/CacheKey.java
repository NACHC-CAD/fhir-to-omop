package org.nachc.tools.fhirtoomop.util.mapping.impl.cache;

import lombok.Getter;

@Getter
public class CacheKey {

	private String system;
	
	private String code;

	public CacheKey(String system, String code) {
		super();
		this.system = system;
		this.code = code;
	}
	
	@Override
	public boolean equals(Object other) {
		if(other == null || other instanceof CacheKey == false) {
			return false;
		}
		CacheKey otherKey = (CacheKey) other;
		if(system != null && code != null && this.system.equals(otherKey.system) && this.code.equals(otherKey.code)) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public int hashCode() {
	    return (this.system + "|" + this.code).hashCode();
	}
	
}
