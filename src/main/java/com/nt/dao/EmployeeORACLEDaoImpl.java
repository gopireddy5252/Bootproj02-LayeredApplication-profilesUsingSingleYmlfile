package com.nt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.nt.model.Employee;
@Repository("empORACLEDAO")
@Profile({"uat","prod"})
public class EmployeeORACLEDaoImpl implements IEmployeeDAO {
	private static final String GET_EMPS_BY_DESGS="select eno,sname,job,sal,deptno from empboot1 where job in(?,?,?)";
	private static final String INSERT_EMP_DETAILS="insert into empboot1(eno,sname,job,sal,deptno)values(?,?,?,?,?)"; 
	@Autowired
	private DataSource ds;
	@Override
	public List<Employee> getEmployeesByDesgs(String desg1, String desg2, String desg3) throws Exception {
		List<Employee> list=null;
		try(
				Connection con=ds.getConnection();
				PreparedStatement ps=con.prepareStatement(GET_EMPS_BY_DESGS);
				){
				ps.setString(1, desg1);
				ps.setString(2, desg2);
				ps.setString(3, desg3);
				try(
					ResultSet rs=ps.executeQuery();
						){
					list=new ArrayList<>();
					while(rs.next())
					{
						//copy each record into java bean class object
						Employee emp=new Employee();
						emp.setEno(rs.getInt(1));
						emp.setSname(rs.getString(2));
						emp.setJob(rs.getString(3));
						emp.setSal(rs.getDouble(4));
						emp.setDeptno(rs.getInt(5));
						//add each java bean class object into list collection
						list.add(emp);
					}
					
				}
		}
		catch (SQLException e) {
			System.out.println("Some db problem, wait a moment..");
			throw e;
		}
		catch (Exception e) {
			System.out.println("Some unkwon problem, wait a moment...");
		}
		
		return list;
	}
	@Override
	public int insertEmployees(Employee emp) throws Exception {
		int result=0;
		try(
				Connection con=ds.getConnection();
				PreparedStatement ps=con.prepareStatement(INSERT_EMP_DETAILS);){
			//set the values to query param
			ps.setInt(1, emp.getEno());
			ps.setString(2, emp.getSname());
			ps.setString(3,emp.getJob());
			ps.setDouble(4, emp.getSal());
			ps.setInt(5, emp.getDeptno());
			
			//execute query
			result=ps.executeUpdate();
		}
		catch(SQLException se)
		{
			se.printStackTrace();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return result;
	}
	

}
