package project.upload.services;

import java.util.HashMap;

import org.springframework.stereotype.Service;

@Service
public class HttpService implements HttpServiceInterface{

	@Override
	public HashMap<String, String> getHttpResponse(String key, String value) {	
		HashMap<String, String> httpResponse = new HashMap();
		httpResponse.put(key, value);
		return httpResponse;
	}

}
