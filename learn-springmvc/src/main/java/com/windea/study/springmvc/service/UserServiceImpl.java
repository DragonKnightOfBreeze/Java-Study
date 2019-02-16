package com.windea.study.springmvc.service;

import com.windea.study.springmvc.domain.*;
import com.windea.study.springmvc.mapper.UserMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	private final UserMapper userMapper;

	@Autowired
	public UserServiceImpl(UserMapper userMapper) {this.userMapper = userMapper;}


	@Override
	public void insert(User user) throws Exception {
		if(user == null)
			throw new RuntimeException();

		userMapper.insert(user);
	}

	@Override
	public void deleteById(Integer id) throws Exception {
		if(id == null)
			throw new RuntimeException();

		userMapper.deleteById(id);
	}

	@Override
	public void updateById(Integer id, User user) throws Exception {
		//添加业务校验
		if(id == null || user == null)
			throw new RuntimeException();

		userMapper.updateById(id, user);
	}

	@Override
	public UserEx findById(Integer id) throws Exception {
		if(id == null)
			throw new RuntimeException();

		User user = userMapper.findById(id);
		UserEx userEx = new UserEx();
		BeanUtils.copyProperties(user, userEx);
		return userEx;
	}

	@Override
	public List<User> findAll() throws Exception {
		return userMapper.findAll();
	}


	@Override
	public List<User> findByUsername(String username) throws Exception {
		return userMapper.findByUsername(username);
	}

	@Override
	public List<User> findByUsernameLike(String likeUsername) throws Exception {
		return userMapper.findByUsernameLike(likeUsername);
	}


	@Override
	public List<UserEx> findByConditions(UserQueryVo query) throws Exception {
		return userMapper.findByConditions(query);
	}


}
