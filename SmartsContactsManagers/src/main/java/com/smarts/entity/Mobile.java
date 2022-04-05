package com.smarts.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name="MOBILE")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Mobile {

	@Id
	@GeneratedValue(strategy =GenerationType.AUTO)
	private Integer mobileId;

	@Size(min=10,max=10)
	@Pattern(regexp="(^$|[0-9]{10})")
	private String mobileNo;

	private String countryCd;
	private String type;
	private String createdBy;
	private Date createdOn;
	private String updatedBy;
	private Date updatedOn;

}


	