package dss.service.locator;

import dss.service.DssService;
import dss.service.impl.DssServiceImpl;

public class ServiceLocator {

	
	private static DssService dssService;
	
	public static DssService getDssService() {
		if(dssService == null) {
			dssService = new DssServiceImpl();
		}
		return dssService; 
	}
	
}
