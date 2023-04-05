package com.bulbul.ribana.repository;

import com.bulbul.ribana.entity.Role;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends BaseRepository<Role, Long>, RoleRepositoryCustom {
}
