package studentmanagement.impl;


import studentmanagement.IStudentService;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;


import org.osgi.framework.ServiceRegistration;
import repository.IRepositoryService;

public class Activator implements BundleActivator {


	IStudentService studentService;
	IRepositoryService systemService;


	private ServiceRegistration<IStudentService> 
		IStudentServiceRegistration;
	
	

	public void start(BundleContext context)
		throws Exception {

			
			ServiceReference<IRepositoryService> serviceLecturerReference =
					context.getServiceReference(IRepositoryService.class);	
			
		    
			IRepositoryService lecturerQuery = context.getService(serviceLecturerReference);
		
			StudentHandler handler = new StudentHandler(lecturerQuery);
			
			studentService = handler;
			
			IStudentServiceRegistration =
			        context.registerService(
							IStudentService.class, studentService, null);

	}
	
	

	public void stop(BundleContext context)
	throws Exception {
		
		IStudentServiceRegistration.unregister();
    }
		
}