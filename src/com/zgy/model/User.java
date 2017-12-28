package com.zgy.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="tuser")
@NamedStoredProcedureQueries({
	@NamedStoredProcedureQuery(
			name = "addUser_sp",
			procedureName = "addUser",
			parameters = {
					@StoredProcedureParameter(name = "loginName",mode = ParameterMode.IN, type = String.class),
					@StoredProcedureParameter(name = "userName", mode = ParameterMode.IN,type = String.class),
					@StoredProcedureParameter(name="serial",mode = ParameterMode.IN,type = Integer.class)
			}
		),
	@NamedStoredProcedureQuery(
			name = "getCount_sp",
			procedureName = "getCountByUserName",
			parameters = {
					@StoredProcedureParameter(name = "userName",mode=ParameterMode.IN,type=String.class),
					@StoredProcedureParameter(name="userCount",mode=ParameterMode.OUT,type=Integer.class)
			}
		),
	@NamedStoredProcedureQuery(
				name = "getAllUsers",
				procedureName = "getAllUsers"
			)
})
@NamedEntityGraphs({
	@NamedEntityGraph(name="graph.User.setRoles",
			attributeNodes=@NamedAttributeNode(value="setRoles")
			),
	@NamedEntityGraph(name="graph.User.originations",
			attributeNodes=@NamedAttributeNode(value="originations"))
})

public class User extends Model implements IModel,Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public User() {
		// TODO Auto-generated constructor stub
	}
	public User(String loginName,String userName) {
		this.loginName = loginName;
		this.userName  = userName;
	}
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Id")
	private long Id;
	@NotNull
	@Size(
			min = 3,
			max = 20,
			message = "登录名的长度再3到20个字节之间"
			)
	private String loginName;
	private String userName;
	private String userPassword;
	private String email;
	private Integer serial;
	private Integer loginCount;
	private String creator;
	@CreationTimestamp  //创建时间，即创建时的时间，创建的时间才会变更
	@Column(name="createTime",updatable=false)//此处，如果不添加updatable=false语句，将不起作用（导致的后果就是每次更新时该时间也会被更新）
	private Timestamp createTime;
	private String updator;
	@UpdateTimestamp  //更新时间，更新数据的时候才会变更
	@Column(name="updateTime")
	private Timestamp updateTime;
	@ManyToMany(fetch=FetchType.LAZY,cascade=CascadeType.REFRESH)
	@JoinTable(name="tmap_roleuser",joinColumns= {@JoinColumn(name="userId")},
			   inverseJoinColumns= {@JoinColumn(name="roleId")})
	@JsonIgnore
	private Set<Role> setRoles = new HashSet<Role>();
	@ManyToMany(fetch=FetchType.LAZY,mappedBy="users")
	@JsonIgnore
	private Set<Origination> originations;
	public long getId() {
		return Id;
	}
	public void setId(long id) {
		Id = id;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public String getUpdator() {
		return updator;
	}
	public void setUpdator(String updator) {
		this.updator = updator;
	}
	public Timestamp getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}
	
	
	public Set<Role> getSetRoles() {
		return Collections.unmodifiableSet(setRoles);
	}
	/**
	 * 为用户添加角色
	 * @param role
	 */
	public void addRole(Role role) {
		if (role==null) {
			throw new NullPointerException("can not add null role");
		}
		if (!this.setRoles.contains(role)) {
			setRoles.add(role);
		}
		if (!role.getColUsers().contains(this)) {
			role.addUser(this);
		}
	}
	/**
	 * 移除某个角色
	 * @param role
	 */
	public void removeRole(Role role) {
		if (role == null) {
			throw new NullPointerException("不能移除空的角色");
		}
		if (setRoles.contains(role)) {
			setRoles.remove(role);
		}
		if (role.getColUsers().contains(this)) {
			role.removeUser(this);
		}
	}
	/**
	 * 清空角色
	 */
	public void clearRoles() {
		setRoles.clear();
	}
	
	public Set<Origination> getOriginations() {
		return originations;
	}
	public void setOriginations(Set<Origination> originations) {
		this.originations = originations;
	}
	public void setSerial(Integer serial) {
		this.serial = serial;
	}
	public void setLoginCount(Integer loginCount) {
		this.loginCount = loginCount;
	}
	public void setSetRoles(Set<Role> setRoles) {
		this.setRoles = setRoles;
	}
	@Override
	public String toString() {
		return "loginName="+loginName+",userName="+userName+",email="+email+
				",loginCount="+loginCount;
	}
	@Override
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof User)) {
			return false;
		}
		final User user = (User)other;
		if (!user.getLoginName().equals(getLoginName())) {
			return false;
		}
		return true;
	}
	@Override
	public int hashCode() {
		int result;
		result = getLoginName().hashCode();
		result =  10 * result;
		return result;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Integer getSerial() {
		return serial;
	}
	public Integer getLoginCount() {
		return loginCount;
	}
	public void addOrg(Origination org) {
		if (org==null) {
			throw new NullPointerException("关联的组织机构不能为空");
		}
		if (!originations.contains(org)) {
			this.originations.add(org);
		}
		if (!org.getUsers().contains(this)) {
			org.getUsers().add(this);
		}			
	}
	public void clearOrg() {
		this.originations.clear();
	}
}
