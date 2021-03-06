package com.cssgenerator.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table

public class CssStyle implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	//private static final long serialVersionUID = 1L;
	private Long id;
	private String type;
	private String styleName;
	private String css;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getStyleName() {
		return styleName;
	}
	public void setStyleName(String styleName) {
		this.styleName = styleName;
	}
	public String getCss() {
		return css;
	}
	public void setCss(String css) {
		this.css = css;
	}
	@Override
	public String toString() {
		return "CssStyle [id=" + id + ", type=" + type + ", styleName=" + styleName + ", css=" + css + "]";
	}
	@Override
	public boolean equals(Object other) {
		 return other instanceof CssStyle && (id != null) ? id.equals(((CssStyle) other).id) : (other == this);
	}
	@Override
	public int hashCode() {
		return id != null ? this.getClass().hashCode() + id.hashCode() : super.hashCode();
	}
	
	
}
