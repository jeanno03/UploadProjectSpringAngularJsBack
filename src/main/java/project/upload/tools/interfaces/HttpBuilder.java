package project.upload.tools.interfaces;

import java.util.HashMap;

public interface HttpBuilder {
	
	public static HashMap<String, String> getHttpResponse(String key, String value) {	
		HashMap<String, String> httpResponse = new HashMap();
		httpResponse.put(key, value);
		return httpResponse;
	}

}
