package com.dimafeng.cards.service;


import com.dimafeng.cards.model.User;
import com.dimafeng.cards.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.List;

@Service
public class UserService implements UserDetailsService, PasswordEncoder {
    private static final String SALT = "sdyhbce456!@#$";

    @Autowired
    private UserRepository userRepository;

    /**
     * TODO redo or remove
     *
     * @return
     */
    public List<User> getLastUsers()
    {
        return userRepository.findAll(new PageRequest(0, 10, new Sort(new Sort.Order(Sort.Direction.DESC, "id")))).getContent();
    }

    @Override
    public String encode(CharSequence rawPassword)
    {
        return DigestUtils.md5DigestAsHex((rawPassword.toString() + SALT).getBytes());
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword)
    {
        return encodedPassword.equals(encode(rawPassword));
    }

    @Override
    public UserPrincipal loadUserByUsername(String username) throws UsernameNotFoundException
    {
        final User user = userRepository.findByEmail(username);
        return new UserPrincipal(user);
    }
}
