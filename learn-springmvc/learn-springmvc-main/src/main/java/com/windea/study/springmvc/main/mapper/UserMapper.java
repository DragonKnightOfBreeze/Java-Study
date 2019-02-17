package com.windea.study.springmvc.main.mapper;

import com.windea.study.springmvc.main.domain.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户管理的mapper接口
 */
@Mapper
public interface UserMapper {

	void insert(User user) throws Exception;

	int deleteById(@Param("id") Integer id) throws Exception;

	void updateById(@Param("id") int id, User user) throws Exception;

	User findById(@Param("id") int id) throws Exception;

	List<User> findByUsername(@Param("username") String username) throws Exception;

	List<User> findByUsernameLike(@Param("likeUsername") String likeUsername) throws Exception;

	List<UserEx> findByConditions(UserQueryVo query) throws Exception;

	List<User> findAll() throws Exception;
}
