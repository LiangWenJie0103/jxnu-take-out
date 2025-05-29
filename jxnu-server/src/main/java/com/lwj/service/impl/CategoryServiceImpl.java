package com.lwj.service.impl;

import com.lwj.constant.StatusConstant;
import com.lwj.dto.CategoryDTO;
import com.lwj.dto.CategoryPageQueryDTO;
import com.lwj.entity.Category;
import com.lwj.entity.Employee;
import com.lwj.mapper.CategoryMapper;
import com.lwj.result.PageResult;
import com.lwj.service.CategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;
    @Override
    public PageResult pageQuery(CategoryPageQueryDTO categoryPageQueryDTO) {
        List<Category> records;
        long total;

        if (categoryPageQueryDTO.getName() == null && categoryPageQueryDTO.getType() == null) {
            // 说明无查询条件，直接全部查询
            categoryPageQueryDTO.setPage((categoryPageQueryDTO.getPage()-1) * categoryPageQueryDTO.getPageSize());
            records = categoryMapper.query(categoryPageQueryDTO);
            total = categoryMapper.getTotal(categoryPageQueryDTO);
        } else {
            records = categoryMapper.queryBy(categoryPageQueryDTO);
            total = categoryMapper.getTotalBy(categoryPageQueryDTO);
        }
        return new PageResult(total, records);
    }

    @Override
    public void save(CategoryDTO categoryDTO) {
        Category category = new Category();
        BeanUtils.copyProperties(categoryDTO, category);
        category.setStatus(StatusConstant.DISABLE);
        categoryMapper.insert(category);
    }

    @Override
    public void delete(Long id) {
        categoryMapper.delete(id);
    }

    @Override
    public void startOrStop(Integer status, Long id) {
        Category category = new Category();
        category.setStatus(status);
        category.setId(id);
        // 直接修改对应id员工的状态即可
        categoryMapper.update(category);
    }

    @Override
    public void update(CategoryDTO categoryDTO) {
        Category category = new Category();
        BeanUtils.copyProperties(categoryDTO, category);
        categoryMapper.update(category);
    }
}
