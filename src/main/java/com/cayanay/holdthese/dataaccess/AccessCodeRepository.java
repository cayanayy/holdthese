package com.cayanay.holdthese.dataaccess;

import com.cayanay.holdthese.entities.AccessCode;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AccessCodeRepository extends JpaRepository<AccessCode, Long> {
    @Transactional
    @Modifying
    @Query("UPDATE AccessCode a SET a.expired = ?2 WHERE a.code = ?1")
    void updateExpire(String code, Boolean isExpired);

    Boolean existsByCode(String code);

    AccessCode findAccessCodeByCode(String code);
}
