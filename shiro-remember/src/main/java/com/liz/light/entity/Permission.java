package com.liz.light.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "permission")
public class Permission implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	/**
	 * url地址
	 */
	private String url;
	/**
	 * url描述
	 */
	private String description;
}
