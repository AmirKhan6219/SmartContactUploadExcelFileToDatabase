package com.smarts.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.intellij.lang.annotations.Pattern;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name="CONTACT")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Contact {

	@Id
	@GeneratedValue(strategy =GenerationType.AUTO)
	private Integer contactId;

	@NotBlank(message = "Firstname should not be empty")
	@Size(min = 3, max = 20)
	private String firstName;

	@NotBlank(message = "Lastname should not be empty")
	@Size(min = 3, max = 20)
	private String lastName;

	@Email
	@Column(unique = true)
	private String emailAddress;
	
	private String createdBy;
	private Date createdOn;
	
	private String updatedBy;
	private Date updatedOn;
	
	private String isActive;
	
	@OneToMany(targetEntity = Mobile.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "contactId", referencedColumnName = "contactId")
	private List<Mobile> mobile = new ArrayList<>();

}
