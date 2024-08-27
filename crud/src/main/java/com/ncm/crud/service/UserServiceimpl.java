package com.ncm.crud.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ncm.crud.entity.CreateUser;
import com.ncm.crud.repo.UserRepo;
import com.ncm.crud.vo.UserVo;


	@Service
	public  class UserServiceimpl  implements UserService{
		@Autowired
		
		
		private UserRepo repo;

		public CreateUser save1(UserVo user) {
			CreateUser usercreate = new CreateUser();
			try {
				
				BeanUtils.copyProperties(user,usercreate);
				
				usercreate.setFirstname(user.getname());
				 repo.save(usercreate);
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			return usercreate;
		}
		public boolean authenticate(CreateUser user) {
			
			try {
				
				if (user == null || user.getEmail() == null || user.getPassword() == null) {
		            return false;
		        }
				CreateUser existingUser = repo.findByEmail(user.getEmail());
				
				
				 if (existingUser == null) {
			            return false; 
			        }
				 return existingUser.getPassword().equals(user.getPassword());
				  
				 
				 
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			return false;
		}
		}



