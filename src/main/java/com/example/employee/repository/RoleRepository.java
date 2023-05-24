package com.example.employee.repository;

import com.example.employee.model.ERole;
import com.example.employee.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
    Optional<Role> findByIdAndDeleted(Long id,boolean deleted);
    Set<Role> findByIdInAndDeleted(List<Long> roleIds, boolean deleted);



    Optional<Role> findByNameAndDeleted(ERole name, boolean deleted);
//    @Query(value = "select * from user u where u.deleted = 0 and u.id != 1",nativeQuery = true)
//    Page<Role> getAllUserPageable(Pageable pageable);
}
