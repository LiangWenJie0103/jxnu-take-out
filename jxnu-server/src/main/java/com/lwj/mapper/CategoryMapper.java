package com.lwj.mapper;

import com.lwj.dto.CategoryPageQueryDTO;
import com.lwj.entity.Category;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CategoryMapper {
    @Select("select * from category order by sort asc limit #{page}, #{pageSize}")
    List<Category> query(CategoryPageQueryDTO categoryPageQueryDTO);

    @Select("select count(id) from category")
    long getTotal(CategoryPageQueryDTO categoryPageQueryDTO);

    List<Category> queryBy(CategoryPageQueryDTO categoryPageQueryDTO);

    long getTotalBy(CategoryPageQueryDTO categoryPageQueryDTO);

    void insert(Category category);

    void delete(Long id);

    void update(Category category);
}
