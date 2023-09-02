package com.heshanthenura.ion.Database.Repositories;

import com.heshanthenura.ion.Database.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUuid(String uuid);
}