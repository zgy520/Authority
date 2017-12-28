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
import org.hibernate.annotations.ManyToAny;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
* @author 张广义 E-mail:a442391947@outlook.com
* @version 创建时间：2017年12月23日 下午9:17:58
*/
@Entity
@NamedEntityGraph(name="graph.Menu.buttons",
		attributeNodes=@NamedAttributeNode(value="buttons"))
@Table(name="tmenu")
public class Menu extends Model implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4878492103867194483L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long Id;
	private Long supMenuId;  //上级菜单的Id  supMenuID
	@Column(name="menuName")
	private String text;
	private String menuUrl;
	private Boolean menuVisible;
	private Boolean menuEnable;
	private String iconName;
	private String iconClass;
	private Integer serial;
	private String creator;
	@CreationTimestamp
	@Column(updatable=false)
	private Date createTime;
	private String updator;
	@UpdateTimestamp
	private Date updateTime;
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name="tmap_menubtn",joinColumns= {@JoinColumn(name="meunId")},
				inverseJoinColumns= {@JoinColumn(name="btnId")})
	@JsonIgnore
	private Set<Button> buttons;
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	public Long getSupMenuId() {
		return supMenuId;
	}
	public void setSupMenuId(Long supMenuId) {
		this.supMenuId = supMenuId;
	}
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Boolean getMenuVisible() {
		return menuVisible;
	}
	public Boolean getMenuEnable() {
		return menuEnable;
	}
	public String getMenuUrl() {
		return menuUrl;
	}
	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}
	public Boolean isMenuVisible() {
		return menuVisible;
	}
	public void setMenuVisible(Boolean menuVisible) {
		this.menuVisible = menuVisible;
	}
	public Boolean isMenuEnable() {
		return menuEnable;
	}
	public void setMenuEnable(Boolean menuEnable) {
		this.menuEnable = menuEnable;
	}
	public String getIconName() {
		return iconName;
	}
	public void setIconName(String iconName) {
		this.iconName = iconName;
	}
	public String getIconClass() {
		return iconClass;
	}
	public void setIconClass(String iconClass) {
		this.iconClass = iconClass;
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
	public Set<Button> getButtons() {
		return buttons;
	}
	public void setButtons(Set<Button> buttons) {
		this.buttons = buttons;
	}
	public void addBtn(Button button) {
		if (button==null) {
			throw new NullPointerException("按钮不能为空");
		}
		this.buttons.add(button);
		if (!button.getMenus().contains(this)) {
			button.addMenu(this);
		}
	}
	public void clearBtn() {
		this.buttons.clear();
	}
	public void removeBtn(Button button) {
		
	}
}
