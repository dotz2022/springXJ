package lecturermanagement.impl;


import lecturermanagement.ILecturerService;
import mycampus.IMyCampusService;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;

import repository.IRepositoryService;


	public class Activator implements BundleActivator {

		private ILecturerService identification;

		private ServiceRegistration<ILecturerService> 
			ILecturerServiceRegistration;
		
		public void start(BundleContext context)
			throws Exception {

			/*------MYCAMPUS SIDE ----*/
			ServiceReference<IMyCampusService> mycampusServiceReference =
					context.getServiceReference(IMyCampusService.class);	
		    
			IMyCampusService campusService = context.getService(mycampusServiceReference);
			/*------END OF MYCAMPUS SIDE ----*/
			
			
			/*------REPOSITORY SIDE ----*/
			
			ServiceReference<IRepositoryService> repositoryServiceReference =
					context.getServiceReference(IRepositoryService.class);	
		    
			IRepositoryService repositoryService = context.getService(repositoryServiceReference);
			
			/*------END OF REPOSITORY SIDE ----*/
			
			
			/*------LECTURER INTERFACE SIDE ----*/
		
		    
			LecturerHandler handler = new LecturerHandler(campusService, repositoryService);
	
		 	identification = handler;

		 	ILecturerServiceRegistration =
		         context.registerService(
						ILecturerService.class, identification, null);
		 	/*------END OF LECTURER INTERFACE SIDE ----*/
		}

		
		public void stop(BundleContext context)
		throws Exception {
		
			ILecturerServiceRegistration.unregister();
			

	    }
	
	
	
}
