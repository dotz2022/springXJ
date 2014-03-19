package adminmanagement.impl;


import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;

import repository.IRepositoryService;
import adminmanagement.IAdminService;


	public class Activator implements BundleActivator {

		private IAdminService identification;

		private ServiceRegistration<IAdminService> 
			IAdminServiceRegistration;
		
		public void start(BundleContext context)
			throws Exception {

			ServiceReference<IRepositoryService> serviceReference =
					context.getServiceReference(IRepositoryService.class);	
		    
			IRepositoryService query = context.getService(serviceReference);

			AdminHandler handler = new AdminHandler (query);
	
		 	identification = handler;

		 	IAdminServiceRegistration =
		         context.registerService(
						IAdminService.class, identification, null);
		 	
		}

		
		public void stop(BundleContext context)
		throws Exception {
		
			IAdminServiceRegistration.unregister();

	    }
	
	
	
}
