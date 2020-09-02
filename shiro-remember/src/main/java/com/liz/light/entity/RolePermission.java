package com.liz.light.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author liz
 * @Description:
 * @date: 2020/7/8 15:33
 */
@Data
@Entity
@Table(name = "role_permission")
public class RolePermission implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer roleId;

    private Integer permissionId;
}
