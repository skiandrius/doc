package com.stormy23.repositories;

import com.stormy23.model.Card;
import com.stormy23.model.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {
    Card getCardByDocument(Document document);
}
