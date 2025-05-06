package com.lwj.mapper;

import com.lwj.dto.EmployeePageQueryDTO;
import com.lwj.entity.Employee;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface EmployeeMapper {

    @Select("select * from employee where username = #{username}")
    Employee getByUsername(String username);

    @Select("select * from employee where name like concat('%', #{name}, '%')")
    List<Employee> queryByName(EmployeePageQueryDTO employeePageQueryDTO);

    List<Employee> query(EmployeePageQueryDTO employeePageQueryDTO);

    @Select("select count(id) from employee")
    long getTotal();

    void insert(Employee employee);

    void update(Employee employee);

    @Select("select * from employee where id = #{id}")
    Employee getById(Long id);
}
