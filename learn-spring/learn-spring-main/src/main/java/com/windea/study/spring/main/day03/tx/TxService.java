package com.windea.study.spring.main.day03.tx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TxService {
	private final TxDao dao;

	@Autowired
	public TxService(TxDao dao) {
		this.dao = dao;
	}

	//转账操作
	public void transfer() {
		dao.addMoney("小王", 1000);
		//int a = 1/0;
		dao.subMoney("小马", 1000);
	}
}
