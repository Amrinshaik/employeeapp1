package com.capgemini.employeeapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.capgemini.employeeapp.entities.Employee;
import com.capgemini.employeeapp.service.EmployeeService;

@Controller
public class EmployeeController {
    
	@Autowired
	private EmployeeService employeeService;
	
	// "/" as the url that we get when we run the application ends with "/" (i.e) localhost:8080/employeeapp/
	@RequestMapping(value="/", method=RequestMethod.GET) /*get method becoz it has to get home page*/
	public String getHomePage() {
	return "index";
	}
	@RequestMapping(value="/addEmployeePage", method=RequestMethod.GET)  /*get method becoz it has to get addemployeepage*/
	public String getAddEmployeePage(Model model) {
		model.addAttribute("employee",new Employee()); //1)we are sending empty object as object doesnt have data in it as of now
		return "addEmployeeForm"; /*this must represent employee object and hence we need to bond it*/
	}
	@RequestMapping(value="/addEmployee", method=RequestMethod.POST)  /*get method becoz it has to get addemployeepage*/
	public String addNewEmployee(@ModelAttribute Employee employee) { /*to say that employee obj is model attribute*/
		employeeService.addEmployee(employee);
		return "redirect:/findAllEmployees";
		//in order to add the employee and display that it has been added to the list we are redirecting the added employee obj to the 
		//findallemployees method where it returns all employees
	}
	
	@RequestMapping(value="/findAllEmployees", method=RequestMethod.GET)
	public String getAllEmployeeDetails(Model model) {
		List<Employee> employees=employeeService.findAllEmployees();  /*to share this list with the UI we need model*/ 
				model.addAttribute("allEmployees",employees);
		return "allEmployees";
	}
	
	@RequestMapping(value="/deleteEmployee/{employeeId}",method=RequestMethod.GET)
	public String deleteEmployee(@PathVariable int employeeId) {
		employeeService.deleteEmployee(employeeId);
		return "redirect:/findAllEmployees";
	}
	
	@RequestMapping(value="/editEmployeePage/{employeeId}", method=RequestMethod.GET)
	public String editEmployeePage(@PathVariable int employeeId,Model model) {
		Employee employee=employeeService.findEmployeeById(employeeId);  //returns employee obj which is to be put in model
		model.addAttribute("employee",employee);//1)we have obj with data hence we can send it and don't have to send empty object [refer (1)]
		return "updateEmployeeForm";
	}
	
	@RequestMapping(value="/editEmployeePage/updateEmployee", method=RequestMethod.POST)  
	public String editEmployee(@ModelAttribute Employee employee) { 
		employeeService.updateEmployee(employee);
		return "redirect:/findAllEmployees";
	}
	
	
	@RequestMapping(value="/findEmployeePage", method=RequestMethod.GET)  
	public String findEmployeePage(Model model) {
		model.addAttribute("employee",new Employee()); 
		return "findEmployee";
	}
	@RequestMapping(value="/findEmployee",method=RequestMethod.POST)
	public String findEmployeeById(@ModelAttribute Employee emp, Model model) { 
		Employee employee=employeeService.findEmployeeById(emp.getEmployeeId());
		model.addAttribute("employee",employee);
		return "findEmployeeDetails";
	}
}
