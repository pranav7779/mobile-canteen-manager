package com.sjct.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sjct.common.entity.Member;
import com.sjct.core.dao.MemberDao;

@Service
public class MemberService {

	@Autowired
	private MemberDao memberDao;

	public Member getMemberById(Long id) {
		return (Member) memberDao.getById(id);
	}

}
