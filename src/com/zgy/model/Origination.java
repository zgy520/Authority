package com.zgy.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

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
import javax.persistence.NamedEntityGraphs;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
* @author 张广义 E-mail:a442391947@outlook.com
* @version 创建时间：2017年12月23日 下午9:18:35
*/
@Entity
@Table(name="torigination")
@NamedEntityGraphs({
	@NamedEntityGraph(name="graph.Origination.users",
				attributeNodes=@NamedAttributeNode(value="users"))
})
public class Origination extends Model implements IModel, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2469414825911769099L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long Id;
	private Long supOrgId;
	@Column(name="orgName")
	private String text;
	private Integer serial;
	private String creator;
	@CreationTimestamp
	@Column(updatable=false)
	private Date createTime;
	private String updator;
	@UpdateTimestamp
	private Date updateTime;
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name="tmaporguser",joinColumns= {@JoinColumn(name="OrgId")},
			inverseJoinColumns= {@JoinColumn(name="UserId")})
	@JsonIgnore
	private Set<User> users;
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	public Long getSupOrgId() {
		return supOrgId;
	}
	public void setSupOrgId(Long supOrgId) {
		this.supOrgId = supOrgId;
	}
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Integer getSerial() {
		return serial;
	}
	public void setSerial(Integer serial) {
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
	public Set<User> getUsers() {
		return users;
	}
	public void setUsers(Set<User> users) {
		this.users = users;
	}
	public void addUser(User user) {
		if (user==null) {
			throw new NullPointerException("关联的用户不能为空");
		}
		if (!this.users.contains(user)) {
			this.users.add(user);
		}
		if (!user.getOriginations().contains(this)) {
			user.getOriginations().add(this);
		}		
	}
	public void clearUser() {
		this.users.clear();		
	}
}
