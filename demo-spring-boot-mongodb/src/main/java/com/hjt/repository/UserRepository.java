package com.hjt.repository;


import com.hjt.entity.User;

/**
 * Created by hjt on 2017/5/5.
 */
public interface UserRepository {

    public void saveUser(User user);

    public User findUserByUserName(String userName);

    public long updateUser(User user);

    public void deleteUserById(Long id);



}
