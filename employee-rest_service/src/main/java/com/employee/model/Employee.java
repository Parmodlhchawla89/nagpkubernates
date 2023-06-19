package com.employee.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Table(name = "EMPLOYEE_DETAILS")
@Entity
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public LocalDate getJoiningDate() {
		return joiningDate;
	}
	public void setJoiningDate(LocalDate joiningDate) {
		this.joiningDate = joiningDate;
	}
	public String getOgName() {
		return ogName;
	}
	public void setogName(String ogName) {
		this.ogName = ogName;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	@Column(name = "EMP_ID", unique = true)
	private String employeeId;
	@Column(name = "EMP_NAME")
	private String name;
	@Column(name = "EMP_JOINING_DATE")
	private LocalDate joiningDate;
	@Column(name = "EMP_OGNAME")
	private String ogName;
	@Column(name = "EMP_DESIGNATION")
	private String designation;
	@Column(name = "EMP_LOCATION")
	private String location;
}
