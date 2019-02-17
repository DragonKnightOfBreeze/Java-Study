package com.windea.study.springmvc.main.service;

import com.windea.study.springmvc.main.domain.*;

import java.util.List;

public interface UserService {
	void insert(User user) throws Exception;

	void deleteById(Integer id) throws Exception;

	void updateById(Integer id, User user) throws Exception;

	UserEx findById(Integer id) throws Exception;

	List<User> findAll() throws Exception;


	List<User> findByUsername(String username) throws Exception;

	List<User> findByUsernameLike(String likeUsername) throws Exception;

	List<UserEx> findByConditions(UserQueryVo query) throws Exception;


}
