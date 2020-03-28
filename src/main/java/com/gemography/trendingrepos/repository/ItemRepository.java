package com.gemography.trendingrepos.repository;

import com.gemography.trendingrepos.entity.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<ItemEntity, Long> {

    List<ItemEntity> findByLanguage(String language);

    Long countByLanguage(String language);

    @Query("SELECT DISTINCT item.language as lang FROM ItemEntity item ORDER BY lang ASC")
    List<String> getAllLanguages();
}
