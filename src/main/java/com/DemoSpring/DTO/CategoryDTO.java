package com.DemoSpring.DTO;

import java.util.List;

public class CategoryDTO {
	private int id;
	private String name;
	private String description;
	private List<Integer> list_pro;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<Integer> getList_pro() {
		return list_pro;
	}
	public void setList_pro(List<Integer> list_pro) {
		this.list_pro = list_pro;
	}
	
}
