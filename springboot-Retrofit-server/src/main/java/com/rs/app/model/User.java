package com.rs.app.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
	private static final long serialVersionUID = 478045970190796546L;
	private long id;
	private String empno;
	private String name;
	private String email;
	private String address;

	public User(String empno, String name, String email, String address) {
		super();
		this.empno = empno;
		this.name = name;
		this.email = email;
		this.address = address;
	}
}
