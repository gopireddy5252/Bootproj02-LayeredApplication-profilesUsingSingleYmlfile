package com.nt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.nt.dao.IEmployeeDAO;
import com.nt.model.Employee;
@Service("empService")
public class EmployeeServiceImpl implements IEmployeeService {
	@Autowired
	private IEmployeeDAO dao;
	@Override
	public List<Employee> fetchAllEmpsByDesgs(String desg1, String desg2, String desg3) throws Exception {
//		//convert the recived signaturs to UPPERCASE lettters
//		desg1=desg1.toUpperCase();
//		desg2=desg2.toUpperCase();
//		desg3=desg3.toUpperCase();
		
		List<Employee> list=dao.getEmployeesByDesgs(desg1, desg2, desg3);
		list.sort((t1,t2)->t1.getEno().compareTo(t2.getEno()));
		
		 //calculate the gross salary and net salary
		list.forEach(emp->{
			emp.setGrossSalary(emp.getSal() + (emp.getSal() * 0.50)); // 50% increase in salary
			emp.setNetSalary(emp.getGrossSalary() - (emp.getGrossSalary() * 0.20)); // 20% reduction in gross salary

		}
		);
		return list;
	}
	@Override
	public String registerEmployee(Employee emp) throws Exception {
		int result=dao.insertEmployees(emp);
		return result==0?"Employee not registerd":"Employee is registerd";
	}
	

}
