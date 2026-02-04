package com.Swadeshi.bank.Repository;

import com.Swadeshi.bank.models.CardModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends JpaRepository<CardModel, Long> {
}
