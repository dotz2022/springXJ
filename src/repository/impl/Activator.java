package repository.impl;


import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

import repository.IRepositoryService;

public class Activator implements BundleActivator {


	private DBMS dbms;
	private IRepositoryService identification;

	
	private ServiceRegistration<IRepositoryService> 
	IRepositoryLecturerServiceRegistration;

	
	public void start(BundleContext context)
		throws Exception {

		dbms = new DBMS();

		RepositoryHandler handler = new RepositoryHandler(dbms);
		
	 	identification = handler;
		
	 	
	 	
	 	IRepositoryLecturerServiceRegistration =
	          context.registerService(
	        		  IRepositoryService.class, identification, null);

	}
	

	
	public void stop(BundleContext context)
	throws Exception {
	
		IRepositoryLecturerServiceRegistration.unregister();

    }
		
}