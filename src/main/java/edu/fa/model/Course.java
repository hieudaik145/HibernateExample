package edu.fa.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

@Entity(name="course" )
public class Course {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column
	private String name;
	
	@Temporal(TemporalType.DATE)
	private Date createDate;
	
	@ElementCollection(fetch = FetchType.LAZY)
	private List<Syllabus> syllabus = new ArrayList<Syllabus>();


	public List<Syllabus> getSyllabus() {
		return syllabus;
	}


	public void setSyllabus(List<Syllabus> syllabus) {
		this.syllabus = syllabus;
	}


	public Date getCreateDate() {
		return createDate;
	}


	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}


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
	@Override
	public String toString() {
		return "Course [name=" + name + "]";
	}
	public Course(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public Course() {
		super();
	}


	public Course(String name, Date createDate) {
		super();
		this.name = name;
		this.createDate = createDate;
	}


	public Course(String name, Date createDate, List<Syllabus> syllabus) {
		super();
		this.name = name;
		this.createDate = createDate;
		this.syllabus = syllabus;
	}


	public Course(String name) {
		super();
		this.name = name;
	}
	


	
	

	
}
