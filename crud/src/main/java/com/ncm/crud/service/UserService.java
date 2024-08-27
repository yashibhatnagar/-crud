package com.ncm.crud.service;

import com.ncm.crud.entity.CreateUser;
import com.ncm.crud.vo.UserVo;

public interface UserService {
	

	public CreateUser save1(UserVo user);
	public boolean authenticate(CreateUser user) ;

}
