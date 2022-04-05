package com.property.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="property_maintenance_files")
public class PropertyMaintenanceFile {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="Id")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="property_maintenance_id")
	private PropertyMaintenance propertyMaintenance;
	
	@ManyToOne
	@JoinColumn(name="file_id")
	private File file;

	@Column(name="description")
	private String description;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public PropertyMaintenance getPropertyMaintenance() {
		return propertyMaintenance;
	}

	public void setPropertyMaintenance(PropertyMaintenance propertyMaintenance) {
		this.propertyMaintenance = propertyMaintenance;
	}


}
