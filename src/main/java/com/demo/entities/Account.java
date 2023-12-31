package com.demo.entities;
// Generated Nov 21, 2023, 12:19:37 AM by Hibernate Tools 4.3.6.Final

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Account generated by hbm2java
 */
@Entity
@Table(name = "account")
public class Account implements java.io.Serializable {

	private Integer id;
	private String username;
	private String password;
	private String email;
	private Date created;
	private boolean verify;
	private String securitycode;
	private boolean status;
	private int role;
	private Set<Post> posts = new HashSet<Post>(0);
	private Set<Feedback> feedbacks = new HashSet<Feedback>(0);
	private Set<Accountdetails> accountdetailses = new HashSet<Accountdetails>(0);

	public Account() {
	}

	public Account(String username, String password, String email, Date created, boolean verify, String securitycode,
			boolean status, int role) {
		this.username = username;
		this.password = password;
		this.email = email;
		this.created = created;
		this.verify = verify;
		this.securitycode = securitycode;
		this.status = status;
		this.role = role;
	}

	public Account(String username, String password, String email, Date created, boolean verify, String securitycode,
			boolean status, int role, Set<Post> posts, Set<Feedback> feedbacks, Set<Accountdetails> accountdetailses) {
		this.username = username;
		this.password = password;
		this.email = email;
		this.created = created;
		this.verify = verify;
		this.securitycode = securitycode;
		this.status = status;
		this.role = role;
		this.posts = posts;
		this.feedbacks = feedbacks;
		this.accountdetailses = accountdetailses;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "username", nullable = false, length = 250)
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "password", nullable = false, length = 250)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "email", nullable = false, length = 250)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "created", nullable = false, length = 10)
	public Date getCreated() {
		return this.created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	@Column(name = "verify", nullable = false)
	public boolean isVerify() {
		return this.verify;
	}

	public void setVerify(boolean verify) {
		this.verify = verify;
	}

	@Column(name = "securitycode", nullable = false, length = 10)
	public String getSecuritycode() {
		return this.securitycode;
	}

	public void setSecuritycode(String securitycode) {
		this.securitycode = securitycode;
	}

	@Column(name = "status", nullable = false)
	public boolean isStatus() {
		return this.status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	@Column(name = "role", nullable = false)
	public int getRole() {
		return this.role;
	}

	public void setRole(int role) {
		this.role = role;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "account")
	public Set<Post> getPosts() {
		return this.posts;
	}

	public void setPosts(Set<Post> posts) {
		this.posts = posts;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "account")
	public Set<Feedback> getFeedbacks() {
		return this.feedbacks;
	}

	public void setFeedbacks(Set<Feedback> feedbacks) {
		this.feedbacks = feedbacks;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "account")
	public Set<Accountdetails> getAccountdetailses() {
		return this.accountdetailses;
	}

	public void setAccountdetailses(Set<Accountdetails> accountdetailses) {
		this.accountdetailses = accountdetailses;
	}

}
