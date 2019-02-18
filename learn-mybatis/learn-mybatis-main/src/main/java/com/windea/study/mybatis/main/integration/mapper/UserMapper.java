package com.windea.study.mybatis.main.integration.mapper;

import com.windea.study.mybatis.main.integration.domain.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户管理的mapper接口
 */
@Mapper
public interface UserMapper {
	/**
	 * 根据id查询用户信息。
	 */
	User findUserById(int id) throws Exception;
}
