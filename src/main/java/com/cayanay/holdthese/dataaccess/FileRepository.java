package com.cayanay.holdthese.dataaccess;

import com.cayanay.holdthese.entities.File;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<File, Long> {
}
