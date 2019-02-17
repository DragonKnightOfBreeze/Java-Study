package com.windea.study.mybatis.integration.dao;

import com.windea.study.mybatis.integration.domain.User;
import org.springframework.stereotype.Repository;

/**
 * 用户管理的dao接口
 */
@Repository
public interface UserDao {
	/**
	 * 根据id查询用户信息。
	 */

	User findUserById(int id) throws Exception;

}
