package com.hotel.booking.service.impl;

import com.hotel.booking.Repository.UserRepository;
import com.hotel.booking.model.User;
import com.hotel.booking.service.UserService;
import org.springframework.stereotype.Service;

//@Service annotation is used in your service layer and annotates classes that perform service tasks


@Service
public class UserServiceImpl implements UserService {
    public UserRepository userRepository;
    UserServiceImpl(UserRepository userRepository)
    {
        super();
        this.userRepository=userRepository;
    }



    @Override
    public User saveUser(User user)
    {
        return userRepository.save(user);
    }
}
