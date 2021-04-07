package com.spiet.auth.VOs;

import lombok.*;

import javax.persistence.Column;
import java.io.Serializable;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class UserVO implements Serializable {
    private static final long serialVersionUID = -6515579048893451833L;

    private String username;

    private String password;


}
