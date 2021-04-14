package com.hw.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="article")
public class ArticleEntity {
	
	@Id
	private int aid;
	
	private String a_title;
	
	private String a_author;


	public int getAid() {
		return aid;
	}

	public void setAid(int aid) {
		this.aid = aid;
	}

	public String getA_title() {
		return a_title;
	}

	public void setA_title(String a_title) {
		this.a_title = a_title;
	}

	public String getA_author() {
		return a_author;
	}

	public void setA_author(String a_author) {
		this.a_author = a_author;
	}

	@Override
	public String toString() {
		return "ArticleEntity [aid=" + aid + ", a_title=" + a_title + ", a_author=" + a_author + "]";
	}
	
}
