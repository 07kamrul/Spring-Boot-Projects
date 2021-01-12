package com.restapi.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "restapi_usertype")
public class UserType implements Serializable {
	private static final long serialCersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String typeName;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "userType", fetch = FetchType.LAZY)
	private List<User> userList;

	public UserType() {

	}

	public UserType(Integer id) {
		this.id = id;
	}

	public UserType(Integer id, String typeName, List<User> userList) {
		this.id = id;
		this.typeName = typeName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

}
