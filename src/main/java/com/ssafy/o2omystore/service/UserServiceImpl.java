package com.ssafy.o2omystore.service;

import org.springframework.stereotype.Service;

import com.ssafy.o2omystore.dao.UserDao;
import com.ssafy.o2omystore.dto.User;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void registerUser(User user) {
        if (userDao.selectUserById(user.getUserId()) != null) {
            throw new IllegalStateException("유저가 이미 존재합니다.");
        }
        userDao.insertUser(user);
    }

    @Override
    public User login(String userId, String password) {

        User user = userDao.selectUserForLogin(userId);

        if (user == null) {
            throw new IllegalStateException("유저를 찾을 수 없습니다");
        }

        if (!user.getPassword().equals(password)) {
            throw new IllegalStateException("패스워드가 일치하지 않습니다");
        }

        user.setPassword(null);
        return user;
    }

    @Override
    public User getUserById(String userId) {
        User user = userDao.selectUserById(userId);
        if (user != null) {
            user.setPassword(null);
        }
        return user;
    }

    @Override
    public void updateUser(User user) {
        userDao.updateUser(user);
    }

    @Override
    public void updatePoint(String userId, int point) {
        userDao.updatePoint(userId, point);
    }

	@Override
	public boolean isUsed(String userId) {
		
		boolean exists = userDao.isUsed(userId) == 1;
		
		return !exists;
	}
}
