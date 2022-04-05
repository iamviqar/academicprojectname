package com.property.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="gst_main_account")
public class GstMainAccount {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="Id")
	private Long id;
	
	@Column(name="gst_collected")
	private Float gstCollected;
		
	@Column(name="gst_paid")
	private Float gstPaid;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Float getGstCollected() {
		return gstCollected;
	}

	public void setGstCollected(Float gstCollected) {
		this.gstCollected = gstCollected;
	}

	public Float getGstPaid() {
		return gstPaid;
	}

	public void setGstPaid(Float gstPaid) {
		this.gstPaid = gstPaid;
	}
	
}
