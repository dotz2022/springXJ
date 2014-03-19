package dashboard;

import mycampus.IMyCampusService;
import studentmanagement.*;
import studentmanagement.impl.StudentHandler;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;


import adminmanagement.IAdminService;
import repository.IRepositoryService;
import lecturermanagement.ILecturerService;

public class Activator implements BundleActivator {

	IAdminService adminService;
	IStudentService studentService;
	ILecturerService lecturerService;

	public void start(BundleContext context)
		throws Exception {

		/*------MYCAMPUS SIDE ----*/
		ServiceReference<IMyCampusService> serviceReference =
				context.getServiceReference(IMyCampusService.class);	
	    
		IMyCampusService query = context.getService(serviceReference);

		dashboardHandler handler = new dashboardHandler(query);
		/*------END OF MYCAMPUS SIDE ----*/
		
		/*------ADMIN SIDE ----*/
		ServiceReference<IAdminService> adminserviceReference =
				context.getServiceReference(IAdminService.class);	
		
		IAdminService adminquery = context.getService(adminserviceReference);
		
		/*------END OF ADMIN SIDE ----*/
		
		
		/*------STUDENT SIDE ----*/
			ServiceReference<IStudentService> studentServiceReference =
			context.getServiceReference(IStudentService.class);	
		
		IStudentService studentquery = context.getService(studentServiceReference);
		
		
		/*------END OF STUDENT SIDE ----*/
		
	
	
		ServiceReference<IRepositoryService> serviceRepositoryReference =
				context.getServiceReference(IRepositoryService.class);	
		
		IRepositoryService repositoryQuery = context.getService(serviceRepositoryReference);
	
		
		/*----------------Lecturer Service ---------------*/
		ServiceReference<ILecturerService> serviceLecturerReference =
				context.getServiceReference(ILecturerService.class);	
		
	    
		ILecturerService lecturerQuery = context.getService(serviceLecturerReference);
	
		/*------END OF Lecturer SIDE ----*/
		int getRole = handler.loginAccount().getRoleID();
		
		System.out.println("Role is " + getRole);
		
		
		if (getRole == 1) {  //lecturer
			
			//LecturerHandler lectuerHandler = new LecturerHandler(query, repositoryQuery, );
			this.lecturerService = lecturerQuery;
			lecturerService.generatelecturerMenu();
			
			
		} else if (getRole == 2) {   //admin
			
			this.adminService = adminquery;
		     adminService.adminMenu();
		     
		} else if (getRole == 3) {       //student
			
			this.studentService = studentquery;
			studentService.studentMenu();

		}
		
		
		
	/*	if (handler.loginAccount().getRoleID() == 1) {
			
			//LecturerHandler lectuerHandler = new LecturerHandler(query, lecturerQuery,adminquery);
			//LecturerHandler lectuerHandler = new LecturerHandler(query, lecturerQuery);
		} else if  (handler.loginAccount().getRoleID() == 2) {
	
			//dashboardHandler adminhandler = new dashboardHandler(adminquery);
			//handler1.adminMenu();
			
			
		}*/
	

	}
	

	
	public void stop(BundleContext context)
	throws Exception {
	
		//IMYCampusServiceRegistration.unregister();
		//ILecturerServiceRegistration.unregister();
    }
		
}