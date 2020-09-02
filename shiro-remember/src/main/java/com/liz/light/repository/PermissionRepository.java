package com.liz.light.repository;

import com.liz.light.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author liz
 * @Description:
 * @date: 2020/7/14 15:47
 */
@Repository
public interface PermissionRepository extends JpaRepository<Permission,Integer> {

    @Query(value = "select t1.* from permission t1 " +
            "inner join role_permission t2 on t1.id = t2.permission_id " +
            "inner join user_role t3 on t3.role_id = t2.role_id " +
            "inner join user t4 on t4.id = t3.user_id " +
            "where t4.username = :username",nativeQuery = true)
    List<Permission> findPermissionsByUsername(String username);

}
