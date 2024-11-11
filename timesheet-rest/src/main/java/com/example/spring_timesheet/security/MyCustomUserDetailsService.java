//package com.example.spring_timesheet.security;
//
//import com.example.spring_timesheet.model.User;
//import com.example.spring_timesheet.repository.UserRepository;
//import com.example.spring_timesheet.repository.UserRoleRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//
//@Component
//@RequiredArgsConstructor
//public class MyCustomUserDetailsService implements UserDetailsService {
//    private final UserRepository userRepository;
//    private final UserRoleRepository userRoleRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = userRepository.findByLogin(username).
//                orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));
//        List<SimpleGrantedAuthority> userRoles = userRoleRepository.findByUserId(user.getId())
//                .stream()
//                .map(it -> new SimpleGrantedAuthority(it.getRole().toString()))
//                .toList();
//        return new org.springframework.security.core.userdetails.User(
//                user.getLogin(),
//                user.getPassword(),
//                userRoles) ;
//
//    }
//}
