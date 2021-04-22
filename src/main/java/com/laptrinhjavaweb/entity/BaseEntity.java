package com.laptrinhjavaweb.entity;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/*
 * This annotation help these fields will be applied in subclass (Entity) 
 */
@MappedSuperclass 
@EntityListeners(AuditingEntityListener.class)
public abstract  class BaseEntity {
	//primary key + not null
	@Id
	//auto increase so that variable 'id' will not have the setter method
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	/*
	 * If you want to generate the SAME name from spring data JPA to database, use
	 * @Column(name = "createBy")
	 */
	/*
	 * createBy (spring boot) --> create_by (field of table in database)
	 * so that variables must be set in Camel role
	 */
	
	@Column//mandatory
	@CreatedBy
	private String createdBy;
	@Column
	@CreatedDate
	private java.util.Date createdDate;
	
	@Column
	@LastModifiedBy
	private String modifiedBy;
	@Column
	@LastModifiedDate
	private java.util.Date  modifiedDate;
	
	//createdBy and createdDate automatically get the value of the username by jpa auditing cogfig 
	//specifically, class AuditorAwareImpl
	
	
	public Long getId() {
		return id;
	}




	public String getCreatedBy() {
		return createdBy;
	}




	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}




	public java.util.Date getCreatedDate() {
		return createdDate;
	}




	public void setCreatedDate(java.util.Date createdDate) {
		this.createdDate = createdDate;
	}




	public String getModifiedBy() {
		return modifiedBy;
	}




	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}




	public java.util.Date getModifiedDate() {
		return modifiedDate;
	}




	public void setModifiedDate(java.util.Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	
	
	
	

}
