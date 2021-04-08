package com.spiet.auth.entity;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "permission")
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Permission implements GrantedAuthority, Serializable {
    private static final long serialVersionUID = 221541306687116548L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    private String description;

    @Override
    public String getAuthority() {
        return this.description;
    }
    
    public String getDescription() {
    	return this.description;
    }
}
