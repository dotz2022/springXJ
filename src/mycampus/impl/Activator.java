package mycampus.impl;

import mycampus.IMyCampusService;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class Activator implements BundleActivator {


	private DBMS dbms;
	private IMyCampusService identification;

	
	private ServiceRegistration<IMyCampusService> 
		IMYCampusServiceRegistration;

	
	public void start(BundleContext context)
		throws Exception {


		dbms = new DBMS();

		MyCampusHandler handler = new MyCampusHandler(dbms);
		
	 	identification = handler;
		
	 	
	 	IMYCampusServiceRegistration =
	          context.registerService(
					IMyCampusService.class, identification, null);

	}
	

	
	public void stop(BundleContext context)
	throws Exception {
	
		IMYCampusServiceRegistration.unregister();

    }
		
}