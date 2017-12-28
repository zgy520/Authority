package com.zgy.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.beans.factory.config.YamlProcessor.ResolutionMethod;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
* @author 张广义 E-mail:a442391947@outlook.com
* @version 创建时间：2017年11月30日 下午8:12:24
*/
@Entity
@Table(name="trole")
@NamedEntityGraph(name="graph.Role.colUsers",
	attributeNodes=@NamedAttributeNode(value="colUsers")
)	
public class Role extends Model implements IModel,Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 517901796926099092L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Id")
	private long Id;
	private String roleName;
	private int serial;
	private String creator;
	@CreationTimestamp
	@Column(updatable = false)
	private Date createTime;
	private String updator;
	@UpdateTimestamp
	private Date updateTime;
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name="tmap_roleuser",joinColumns= {@JoinColumn(name="roleId")},
	   inverseJoinColumns= {@JoinColumn(name="userId")})
	@JsonIgnore
	private Set<User> colUsers = new HashSet<User>();
	
	public void addUser(User user) {
		if (user==null) {
			throw new NullPointerException("用户不能为空");
		}
		this.colUsers.add(user);
		if (!user.getSetRoles().contains(this)) {
			user.addRole(this);
		}
	}
	
	public void clearUser() {
		this.colUsers.clear();
	}
	
	public void removeUser(User user) {
		if (user == null) {
			throw new NullPointerException("不能移除空的角色");
		}
		if (colUsers.contains(user)) {
			colUsers.remove(user);
		}
		if (user.getSetRoles().contains(this)) {
			user.removeRole(this);
		}
	}
	
	public long getId() {
		return Id;
	}
	public void setId(long id) {
		Id = id;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public int getSerial() {
		return serial;
	}
	public void setSerial(int serial) {
		this.serial = serial;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getUpdator() {
		return updator;
	}
	public void setUpdator(String updator) {
		this.updator = updator;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Set<User> getColUsers() {
		return Collections.unmodifiableSet(colUsers);
	}
	@Override
	public String toString() {
		return this.roleName+","+this.serial;
	}
	@Override
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof Role)) {
			return false;
		}
		final Role role = (Role)other;
		if (!role.getRoleName().equals(getRoleName())) {
			return false;
		}
		return true;
	}
	@Override
	public int hashCode() {
		int result;
		result = getRoleName().hashCode();
		result =  10 * result;
		return result;
	}
	
}
