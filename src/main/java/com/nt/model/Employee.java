package com.nt.model;

import lombok.Data;

@Data
public class Employee {
private Integer eno;
private String sname;
private String job;
private Double sal;
private Integer deptno;
private Double grossSalary;
private Double netSalary;
}