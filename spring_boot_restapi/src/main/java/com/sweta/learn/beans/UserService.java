package com.sweta.learn.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;

	public Page<User> getUserPagination(Integer pageNumber, Integer pageSize,String sortProperty)
	{
		Pageable pageable = null;
		if(null!=sortProperty)
		{
			 pageable = PageRequest.of(pageNumber, pageSize,Sort.Direction.ASC,sortProperty);
		}
		else
		{
			pageable = PageRequest.of(pageNumber, pageSize,Sort.Direction.ASC,"name");
		}
		
		return userRepository.findAll(pageable);
	}


	
}
