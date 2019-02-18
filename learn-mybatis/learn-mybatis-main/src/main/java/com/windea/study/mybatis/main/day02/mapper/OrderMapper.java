package com.windea.study.mybatis.main.day02.mapper;

import com.windea.study.mybatis.main.day02.domain.*;

import java.util.List;

public interface OrderMapper {
	/**
	 * 查询订单关联查询用户信息。
	 */
	List<ExtendedOrder> findOrderWithUser() throws Exception;

	/**
	 * 查询订单关联查询用户信息。使用resultMap。
	 */
	List<O2O_Order> findOrderWithUser2() throws Exception;

	List<O2M_Order> findOrderInDetail() throws Exception;

	/**
	 * 延迟加载：查询订单信息，关联查询用户信息。
	 */
	List<O2O_Order> findOrderWithUserLazyLoading()
	throws Exception;
}
