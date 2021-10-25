package com.sweta.learn.beans;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name="users")
@Data
@AllArgsConstructor
@NoArgsConstructor
// for soft deleting user
@SQLDelete(sql = "UPDATE users SET deleteflag = 1  WHERE id=?")
//show only data for deleteflag=0 
@Where(clause = "deleteflag=0")
public class User {

@GeneratedValue(strategy = GenerationType.IDENTITY)
@Id
private Long id;
@Column(nullable = false, length = 20)
	private String name;
	private String address;
	@Column(nullable = false, length = 9)
	private int phoneno;
	private String gender;
	 @Column(nullable = false, unique = true, length = 45)
	private String email;
	@Column(nullable = false, length = 64)
	private String password;
	private String joiningdate;
	private String dob;
	private int deleteflag=0; //0=not deleted and 1 = deleted
}
