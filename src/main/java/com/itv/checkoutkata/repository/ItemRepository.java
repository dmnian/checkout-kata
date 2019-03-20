package com.itv.checkoutkata.repository;

import com.itv.checkoutkata.entities.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<ItemEntity, Integer> {
}
