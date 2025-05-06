package com.lwj.service;

import com.lwj.dto.EmployeeDTO;
import com.lwj.dto.EmployeeLoginDTO;
import com.lwj.dto.EmployeePageQueryDTO;
import com.lwj.dto.PasswordEditDTO;
import com.lwj.entity.Employee;
import com.lwj.result.PageResult;

public interface EmployeeService {
    Employee login(EmployeeLoginDTO employeeLoginDTO);

    PageResult pageQuery(EmployeePageQueryDTO employeePageQueryDTO);

    void save(EmployeeDTO employeeDTO);

    void startOrStop(Integer status, Long id);

    Employee getById(Long id);

    void update(EmployeeDTO employeeDTO);

    void newPassword(PasswordEditDTO passwordEditDTO);
}
