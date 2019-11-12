package com.example.demo.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Kamrul_Food_Product")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int student_id;
	int student_status;
	String designation;
	String email;
	String gender;
	String name;
	String password;

	public int getStudent_id() {
		return student_id;
	}

	public void setStudent_id(int student_id) {
		this.student_id = student_id;
	}

	public int getStudent_status() {
		return student_status;
	}

	public void setStudent_status(int student_status) {
		this.student_status = student_status;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Product() {
		super();
	} // TODO Auto-generated constructor stub }

	public Product(int student_id, int student_status, String designation, String email, String gender, String name,
			String password) {
		super();
		this.student_id = student_id;
		this.student_status = student_status;
		this.designation = designation;
		this.email = email;
		this.gender = gender;
		this.name = name;
		this.password = password;
	}

}
