package com.liz.light.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author liz
 * @Description:
 * @date: 2020/7/14 15:32
 */
@Data
@Entity
@Table(name = "user_role")
public class UserRole implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Long userId;

    private Integer roleId;

}
