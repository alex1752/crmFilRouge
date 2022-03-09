package crm.utils;

import java.io.IOException;
import java.util.Properties;

public class Configuration {
	
	public static Properties getConfig() throws IOException {
		Properties properties = new Properties();
		
		properties.put("salt", "RVd6ekFRWjV4S1lhZGpZNkl2cVlvWHlmbmswdkRDOVB5dDBudG9lRw==");
		properties.put("api_key", "asdfSFS34wfsdfsdfSDSD32dfsddDDerQSNCK34SOWEK5354fdgdf4");
		
		return properties;
	}
}
