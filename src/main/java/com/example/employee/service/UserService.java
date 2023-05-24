package com.example.employee.service;


import com.example.employee.Dto.UserRegisterDTO;
import com.example.employee.Util.ValidateUtil;
import com.example.employee.model.ERole;
import com.example.employee.model.Role;
import com.example.employee.model.User;
import com.example.employee.repository.RoleRepository;
import com.example.employee.repository.UserRepository;
import com.example.employee.service.Impl.UserDetailsImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class UserService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    RoleRepository roleRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        if (ValidateUtil.regexValidation(username)) return findByEmail(username);
//        if (ValidateUtil.regexValidation(username)) return findByUsername(username);
//        if (ValidateUtil.regexValidation(username)) return findByMobile(username);
        User user = userRepository.findByUsername(username);
        if (username == null) {
            throw new UsernameNotFoundException("User Not Found with username: " + username);
        }

        return UserDetailsImpl.build(user);

    }

    public User createUser(UserRegisterDTO requestData) {
//       validateRegistration(requestData);
       User usercheck = userRepository.findByUsername(requestData.getUsername());
       if (usercheck != null){
           throw new IllegalArgumentException(ValidateUtil.MESSAGE_CODE.TRUNG);
       }
        userRepository.existsByMobileAndDeleted(requestData.getMobile(),false);
        if(!ObjectUtils.isEmpty(requestData.getEmail())){
            requestData.getEmail();
            String username = null;
            ValidateUtil.regexValidation(username, requestData.getEmail());
            userRepository.existsByEmailAndDeleted(requestData.getEmail(), false);
        }
        User user = new User();
        user.setUsername(requestData.getUsername());
        user.setMobile(requestData.getMobile());
        user.setEmail(requestData.getEmail() == null ? null : requestData.getEmail());
        user.setPassword(passwordEncoder.encode(requestData.getPassword()));
        user.setEnabled(false);
        user.getRoles();

        Set<Role> roles = new HashSet<>();
        Optional<Role> optionalRole = roleRepository.findByNameAndDeleted(ERole.USER, false);
        if (optionalRole.isPresent()) {
            roles.add(optionalRole.get());
            user.setRoles(roles);
        }
            userRepository.save(user);

        return user;
    }
//    private void validateRegistration(UserRegisterDTO requestData) {
//        if (!ObjectUtils.isEmpty(requestData.getEmail())) {
//            ValidateUtil.regexValidation(username, requestData.getEmail());
//              User user = userRepository.findOneByEmailIgnoreCaseAndDeleted(requestData.getEmail(), false).orElse(null);
//        }
//        ValidateUtil.regexValidation(username, requestData.getMobile());
//    }
}






