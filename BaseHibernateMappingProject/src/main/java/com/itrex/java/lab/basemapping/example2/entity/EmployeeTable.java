package com.itrex.java.lab.basemapping.example2.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "employee_table", schema = "public")
public class EmployeeTable {

    @Id
    @GeneratedValue(generator = "employee_gen", strategy = GenerationType.TABLE)
    @TableGenerator(name = "employee_gen", table = "table_sequence",
                schema = "public", valueColumnName = "pk_value",
                pkColumnName = "table_name", allocationSize = 1, initialValue = 1)
    private Long id;

    @Column(name = "name")
    private String name;

    public EmployeeTable(String name) {
        this.name = name;
    }

}
