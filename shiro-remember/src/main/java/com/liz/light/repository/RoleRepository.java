package com.liz.light.repository;

import com.liz.light.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author liz
 * @Description:
 * @date: 2020/7/14 15:46
 */
@Repository
public interface RoleRepository extends JpaRepository<Role,Integer> {

    @Query(value = "select t1.* from role t1 " +
            "inner join user_role t2 on t1.id = t2.role_id " +
            "inner join user t3 on t3.id = t2.user_id " +
            "where t3.username = :username",nativeQuery = true)
    List<Role> findRolesByUsername(String username);
}

