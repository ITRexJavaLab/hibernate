package com.itrex.java.lab.basemapping.example3;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Table;

import com.itrex.java.lab.basemapping.example3.entity.Employee3;
import org.junit.Test;

public class ReflectionTest {

    @Test
    public void test() {
        Class<Employee3> employeeClass = Employee3.class;
        Field[] declaredFields = employeeClass.getDeclaredFields();
        Arrays.stream(declaredFields).forEach(field -> System.out.println(field.getName()));

        if (employeeClass.isAnnotationPresent(Table.class)) {
            Table declaredAnnotation = employeeClass.getDeclaredAnnotation(Table.class);
            String insert = "INSERT INTO " + declaredAnnotation.schema() + "." + declaredAnnotation.name();
            System.out.println(insert);
        }

        Arrays.stream(declaredFields).limit(1)
                .forEach(field -> {
                    if (field.isAnnotationPresent(Column.class)) {
                        String name = field.getAnnotation(Column.class).name();
                        System.out.println(name);
                    }
                });
    }

    @Test
    public void checkConstructor() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<Employee3> employeeClass = Employee3.class;
        String name = "Ivan";
        Constructor<Employee3> constructor = employeeClass.getConstructor();
        Employee3 employee = constructor.newInstance();
        employee.setName(name);
    }
}
