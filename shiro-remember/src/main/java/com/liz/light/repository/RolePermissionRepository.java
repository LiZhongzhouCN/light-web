package com.liz.light.repository;

import com.liz.light.entity.RolePermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author liz
 * @Description:
 * @date: 2020/7/8 15:40
 */
@Repository
public interface RolePermissionRepository extends JpaRepository<RolePermission,Integer> {

}
