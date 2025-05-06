package com.lwj.service.impl;

import com.lwj.constant.MessageConstant;
import com.lwj.dto.EmployeeDTO;
import com.lwj.dto.EmployeePageQueryDTO;
import com.lwj.dto.PasswordEditDTO;
import com.lwj.exception.BaseException;
import com.lwj.mapper.EmployeeMapper;
import com.lwj.result.PageResult;
import com.lwj.service.EmployeeService;
import com.lwj.dto.EmployeeLoginDTO;
import com.lwj.entity.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeMapper employeeMapper;

    /**
     * 员工登录
     * @param employeeLoginDTO
     * @return
     */
    @Override
    public Employee login(EmployeeLoginDTO employeeLoginDTO) {
        // 先根据用户名判断是否存在，不存在抛出异常提示
        Employee employee = employeeMapper.getByUsername(employeeLoginDTO.getUsername());
        if (employee == null) {
            throw new BaseException(MessageConstant.ACCOUNT_NOT_FOUND);
        }

        // 存在则对比密码,密码错误抛出异常提示
        if (!employeeLoginDTO.getPassword().equals(employee.getPassword())) {
            throw new BaseException(MessageConstant.PASSWORD_ERROR);
        }
        // 密码正确则返回用户信息
        return employee;
    }

    /**
     * 分页查询
     * @param employeePageQueryDTO
     * @return
     */
    @Override
    public PageResult pageQuery(EmployeePageQueryDTO employeePageQueryDTO) {
        List<Employee> records;
        long total;

        // 先看看有没有姓名查询，根据页数查询
        // 无姓名，根据页数查
        if (employeePageQueryDTO.getName() == null || employeePageQueryDTO.getName().equals("")) {
            employeePageQueryDTO.setPage((employeePageQueryDTO.getPage()-1) * employeePageQueryDTO.getPageSize());
            records = employeeMapper.query(employeePageQueryDTO);
            total = employeeMapper.getTotal();
        } else {
            records = employeeMapper.queryByName(employeePageQueryDTO);
            total = records.size();
        }
        return new PageResult(total, records);
    }

    /**
     * 新增员工
     * @param employeeDTO
     * @return
     */
    @Override
    public void save(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeDTO, employee);

        employee.setPassword("123456");
        employee.setStatus(1);
        employeeMapper.insert(employee);
    }

    @Override
    public void startOrStop(Integer status, Long id) {
        Employee employee = new Employee();
        employee.setId(id);
        employee.setStatus(status);
        employeeMapper.update(employee);
    }

    @Override
    public Employee getById(Long id) {
        Employee employee = employeeMapper.getById(id);
        return employee;
    }

    @Override
    public void update(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeDTO, employee);
        employeeMapper.update(employee);
    }

    @Override
    public void newPassword(PasswordEditDTO passwordEditDTO) {
        Employee employeeOld = employeeMapper.getById(passwordEditDTO.getEmpId());
        if (!employeeOld.getPassword().equals(passwordEditDTO.getOldPassword())) {
            throw new BaseException(MessageConstant.PASSWORD_EDIT_FAILED);
        }
        Employee employeeNew = new Employee();
        employeeNew.setId(passwordEditDTO.getEmpId());
        employeeNew.setPassword(passwordEditDTO.getNewPassword());
        employeeMapper.update(employeeNew);
    }
}
