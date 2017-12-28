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
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.web.jsf.FacesContextUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
* @author 张广义 E-mail:a442391947@outlook.com
* @version 创建时间：2017年12月23日 下午7:09:29
*/
@Entity
@NamedEntityGraph(name="graph.Button.menus",
		attributeNodes=@NamedAttributeNode(value="menus"))
@Table(name="tbutton")
public class Button extends Model implements IModel, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long Id;
	private String btnName;
	private String btnEvent;
	private boolean btnVisible;
	private boolean btnEnable;
	private Integer serial;
	private String creator;
	@CreationTimestamp
	@Column(updatable=false)
	private Date createTime;
	private String updator;
	@UpdateTimestamp
	private Date updateTime;
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name="tmap_menubtn",joinColumns= {@JoinColumn(name="btnId")},
				inverseJoinColumns= {@JoinColumn(name="meunId")})
	@JsonIgnore
	private Set<Menu> menus;
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	public String getBtnName() {
		return btnName;
	}
	public void setBtnName(String btnName) {
		this.btnName = btnName;
	}
	public String getBtnEvent() {
		return btnEvent;
	}
	public void setBtnEvent(String btnEvent) {
		this.btnEvent = btnEvent;
	}
	public boolean isBtnVisible() {
		return btnVisible;
	}
	public void setBtnVisible(boolean btnVisible) {
		this.btnVisible = btnVisible;
	}
	public boolean isBtnEnable() {
		return btnEnable;
	}
	public void setBtnEnable(boolean btnEnable) {
		this.btnEnable = btnEnable;
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
	
	public Set<Menu> getMenus() {
		return menus;
	}
	public void setMenus(Set<Menu> menus) {
		this.menus = menus;
	}
	public void addMenu(Menu menu) {
		if (menu==null) {
			throw new NullPointerException("菜单不能为空");
		}
		this.menus.add(menu);
		if (!menu.getButtons().contains(this)) {
			menu.addBtn(this);
		}
	}
	public void clearMenu() {
		this.menus.clear();		
	}
	
	public void removeMenu(Menu menu) {
		if (menu==null) {
			throw new NullPointerException("不能移除空菜单");
		}
		for(Menu m : this.menus) {
			m.removeBtn(this);
		}
	}

}
