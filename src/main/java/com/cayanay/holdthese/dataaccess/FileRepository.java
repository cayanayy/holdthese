package com.cayanay.holdthese.dataaccess;

import com.cayanay.holdthese.entities.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends JpaRepository<File, Long> {
}
