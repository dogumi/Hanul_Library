package com.project.library.admin.model.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Employee {
	private int empNo;
	private int deptNo;
	private String empName;
	private String empPhone;
	private String empPosition;
	private String empDuties;
	private String deptName;
	
}
