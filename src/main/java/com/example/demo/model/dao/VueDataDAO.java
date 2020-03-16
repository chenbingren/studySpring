package com.example.demo.model.dao;

import com.example.demo.model.bean.VueData;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface VueDataDAO {

	/**
	 * 通过类型查找
	 * @param type
	 * @return
	 */
	public List<VueData> findByType(@Param("type") String type);

	/**
	 * 通过关联关系查找
	 * @param pid
	 * @return
	 */
	public List<VueData> findByPid(@Param("pid") Integer pid);
}
