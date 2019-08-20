package hx.insist.demo.entity;

import hx.insist.demo.enums.UserSexEnum;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String username;

    private String password;

    private UserSexEnum userSex;

    private String nickName;

    public User() {
    }
}