package com.AuthService.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;

@Entity
@Table(name = "auth_users")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Audited
public class UserDE {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_generator")
    @SequenceGenerator(name = "user_generator", sequenceName = "user_s", allocationSize = 1)
    private long userId;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "mobile", unique = true)
    private String mobile;

    @Column(name = "emailId", unique = true)
    private String emailId;

    @Column(name = "userType")
    private String userType;

    @Column(name = "password")
    private String password;

    @Column(name = "status")
    private String status;

    @Column(name = "reset_password_token")
    private String resetPasswordToken;
}
