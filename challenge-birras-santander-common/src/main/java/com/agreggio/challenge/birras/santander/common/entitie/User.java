package com.agreggio.challenge.birras.santander.common.entitie;


import com.agreggio.challenge.birras.santander.common.Util.EncodeUtil;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;


@Entity
@Table(name="USER")
@EntityListeners(AuditingEntityListener.class)
public class User extends AuditableEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID")
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;

	@Column(name="FIRST_NAME", nullable = false)
	private String firstName;

	@Column(name = "USER_NAME", unique=true, nullable = false)
	private String userName;

	@Column(name = "LAST_NAME", nullable = false)
	private String lastName;

	@Column(name = "PASSWORD", nullable = false)
	private String password;

	@ManyToOne
	@NotNull
	@JoinColumn(name = "USER_TYPE_ID")
	private UserType userType;

	public User() {

	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userTypeSet) {
		this.userType = userTypeSet;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = EncodeUtil.encode(password);
	}


	public User(String firstName, String userName, String lastName, String password, UserType userType) {
		this.firstName = firstName;
		this.userName = userName;
		this.lastName = lastName;
		this.password = EncodeUtil.encode(password);
		this.userType = userType;
	}
}
