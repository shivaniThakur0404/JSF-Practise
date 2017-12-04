package com.jsfPro.bean;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.jsfPro.data.*;

@ManagedBean
@SessionScoped
public class ViewEmployeesManagedBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public List<Employee> getEmploye() throws ClassNotFoundException, SQLException {

		Connection connect = null;

		String url = "jdbc:mysql://localhost:3306/JSFdb";

		String username = "root";
		String password = "root";

		try {

			Class.forName("com.mysql.jdbc.Driver");

			connect = DriverManager.getConnection(url, username, password);
			// System.out.println("Connection established"+connect);

		} catch (SQLException ex) {
			System.out.println("in exec");
			System.out.println(ex.getMessage());
		}

		List<Employee> emp = new ArrayList<Employee>();
		PreparedStatement pstmt = connect.prepareStatement("select emp_id, e_name from Employee");
		ResultSet rs = pstmt.executeQuery();

		while (rs.next()) {

			Employee e = new Employee();
			e.setEmployeeId(rs.getString("emp_id"));
			e.setEmployeeName(rs.getString("e_name"));
			emp.add(e);

		}

		// close resources
		rs.close();
		pstmt.close();
		connect.close();

		return emp;

	}

	// private List<Employee> employees = new ArrayList<Employee>();
	//
	// public ViewEmployeesManagedBean(){
	//
	// }
	//
	// @PostConstruct
	// public void populateEmployeeList(){
	// for(int i = 1 ; i <= 10 ; i++){
	// Employee emp = new Employee();
	// emp.setEmployeeId(String.valueOf(i));
	// emp.setEmployeeName("Employee#"+i);
	// this.employees.add(emp);
	// }
	// }
	//
	// public List<Employee> getEmployees() {
	// return employees;
	// }
	//
	// public void setEmployees(List<Employee> employees) {
	// this.employees = employees;
	// }
}