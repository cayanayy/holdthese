package com.cayanay.holdthese.dataaccess;

import com.cayanay.holdthese.entities.AccessCode;
import com.cayanay.holdthese.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findItemsByAccessCode(AccessCode accessCode);
}
