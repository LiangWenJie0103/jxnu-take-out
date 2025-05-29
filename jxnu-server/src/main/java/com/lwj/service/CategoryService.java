package com.lwj.service;

import com.lwj.dto.CategoryDTO;
import com.lwj.dto.CategoryPageQueryDTO;
import com.lwj.result.PageResult;

public interface CategoryService {
    PageResult pageQuery(CategoryPageQueryDTO categoryPageQueryDTO);

    void save(CategoryDTO categoryDTO);

    void delete(Long id);

    void startOrStop(Integer status, Long id);

    void update(CategoryDTO categoryDTO);
}
