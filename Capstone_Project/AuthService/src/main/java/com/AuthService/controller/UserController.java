package com.AuthService.controller;

import com.AuthService.dto.*;
import com.AuthService.entity.RefreshToken;
import com.AuthService.entity.UserDE;
import com.AuthService.exception.ExistingUserFoundException;
import com.AuthService.exception.MissingFieldException;
import com.AuthService.exception.NotFoundException;
import com.AuthService.exception.TokenRefreshException;
import com.AuthService.jwt.JwtUtils;
import com.AuthService.repository.UserRepository;
import com.AuthService.service.RefreshTokenService;
import com.AuthService.service.UserDetailsImpl;
import com.AuthService.service.UserService;
import com.AuthService.utility.Constants;
import com.AuthService.validator.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/auth")
public class UserController {


    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    RefreshTokenService refreshTokenService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @PostMapping("/signup")
    public ResponseEntity<?> saveUser(@RequestBody SignUpRequestDTO signUpRequestDTO)
            throws MissingFieldException, ExistingUserFoundException {

        try {
            ValidateUserFields validateUserFields = new ValidateUserFields();
            validateUserFields.validateSignUpFields(signUpRequestDTO);
            UserDE userRegistered = null;

            if (userService.getByEmailIdAndMobileNumber(signUpRequestDTO.getEmailId(), signUpRequestDTO.getMobile()) == null) {
                userRegistered = userService.saveUser(signUpRequestDTO);
                return ResponseEntity.status(HttpStatus.CREATED)
                        .body(new SavedRecordResponseDto(userRegistered, new ResponseDto(201, "Record saved successfully")));
            } else {
                throw new ExistingUserFoundException("User already exists");
            }
        } catch (ExistingUserFoundException e) {
            throw new ExistingUserFoundException(e.getMessage());
        } catch (MissingFieldException e) {
            throw new MissingFieldException("Fields missing");
        }

    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequestDTO loginRequestDTO) throws Exception {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequestDTO.getEmailId(), loginRequestDTO.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);
        RefreshToken refreshToken = refreshTokenService.updateRefreshToken(userDetails.getId());
        ResponseCookie jwtRefreshCookie = jwtUtils.generateRefreshJwtCookie(refreshToken.getToken());

        UserDE user = userService.getByEmailId(loginRequestDTO.getEmailId());

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
                .header(HttpHeaders.SET_COOKIE, jwtRefreshCookie.toString())
                .body(new JwtResponse(jwtCookie.getValue(), user.getUserType()));
    }
    @PostMapping("/change-password")
    public ResponseEntity<?> changePassword(@RequestBody ChangePasswordDTO changePasswordDto) {

        UserDE user = userService.getByEmailId(changePasswordDto.getEmailId());

        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseDto(404, "User not found"));
        }
        user.setPassword(passwordEncoder.encode(changePasswordDto.getPassword()));
        userRepository.save(user);

        return ResponseEntity.ok(new ResponseDto(200, "Password updated successfully"));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) throws NotFoundException {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/refreshtoken")
    public ResponseEntity<?> refreshtoken(HttpServletRequest request) {
        String refreshToken = jwtUtils.getJwtRefreshFromCookies(request);

        if ((refreshToken != null) && (refreshToken.length() > 0)) {
            return refreshTokenService.findByToken(refreshToken)
                    .map(refreshTokenService::verifyExpiration)
                    .map(RefreshToken::getUser)
                    .map(user -> {
                        ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(user);

                        return ResponseEntity.ok()
                                .header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
                                .body(new ResponseDto(200, Constants.TOKEN_REFRESHED_SUCCESSFULLY));
                    })
                    .orElseThrow(() -> new TokenRefreshException(refreshToken,
                            Constants.TOKEN_REFRESHED_NOT_AVAILABLE));
        }

        return ResponseEntity.badRequest().body(new ResponseDto(4, Constants.TOKEN_EMPTY));
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserDE>> getAllDetails() {
        List<UserDE> usersResponses = userService.getAllDetails();
        return ResponseEntity.ok(usersResponses);
    }

    @GetMapping("/test")
    public String test(){
        System.out.println("test method for aws pipeline");
        System.out.println("  ");
        return "returning test endpoint";
    }
}

