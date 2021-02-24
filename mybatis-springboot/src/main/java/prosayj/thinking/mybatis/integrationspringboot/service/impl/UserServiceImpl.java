package prosayj.thinking.mybatis.integrationspringboot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import prosayj.thinking.mybatis.integrationspringboot.service.UserService;
import prosayj.thinking.mybatis.simple.mapper.UserMapper;
import prosayj.thinking.mybatis.simple.model.SysUser;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;

	@Override
	public SysUser findById(Long id) {
		return userMapper.selectById(id);
	}
	
	@Override
	public List<SysUser> findAll() {
		return userMapper.selectAll();
	}
	
}
