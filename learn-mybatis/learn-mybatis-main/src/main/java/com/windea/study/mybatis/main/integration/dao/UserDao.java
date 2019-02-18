package com.windea.study.mybatis.main.integration.dao;

import com.windea.study.mybatis.main.integration.domain.User;
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
