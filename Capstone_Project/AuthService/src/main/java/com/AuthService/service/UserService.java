package com.AuthService.service;
import com.AuthService.dto.SignUpRequestDTO;
import com.AuthService.entity.UserDE;
import com.AuthService.exception.NotFoundException;
import com.AuthService.repository.RefreshTokenRepository;
import com.AuthService.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RefreshTokenRepository refreshTokenRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserDE saveUser(SignUpRequestDTO signUpRequestDTO)  {
        UserDE userregistered = null;
        UserDE userDE = new UserDE();
        userDE.setLastName(signUpRequestDTO.getLastName());
        userDE.setFirstName(signUpRequestDTO.getFirstName());
        userDE.setMobile(signUpRequestDTO.getMobile());
        userDE.setEmailId(signUpRequestDTO.getEmailId());

        userDE.setUserType(signUpRequestDTO.getUserType());
        userDE.setPassword(passwordEncoder.encode(signUpRequestDTO.getPassword()));
        userregistered = userRepository.save(userDE);

        return userregistered;
    }

    public UserDE getByEmailId(String emailId) {
        return userRepository.findByEmail(emailId);
    }

    public UserDE getByEmailIdAndMobileNumber(String emailId, String mobile) {
        return userRepository.findUserByEmailAndPhoneNumber(emailId, mobile);
    }

    public List<UserDE> getAllDetails() {
        return userRepository.findAllDetails();
    }

    public void deleteUser(Long id) throws NotFoundException {
        Optional<UserDE> userDE = userRepository.findById(id);

        if (userDE.isPresent()) {
            refreshTokenRepository.deleteByUser(userDE.get());
            userRepository.delete(userDE.get());
        } else {
            throw new NotFoundException("User not found with id: " + id);
        }
    }
}
