package com.nt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.nt.model.Employee;
import com.nt.service.IEmployeeService;

@Controller("empController")
public class EmployeeOperationController {
	@Autowired
	private IEmployeeService empService;
	public List<Employee> processEmployeeByDesg(String desg1,String desg2,String desg3)throws Exception{
		List<Employee> list=empService.fetchAllEmpsByDesgs(desg1, desg2, desg3);
		return list;
	}
	public String registerEmployee(Employee emp) throws Exception{
		String list=empService.registerEmployee(emp);
		return list;
	}
}
