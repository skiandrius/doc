package com.stormy23.services;

import com.stormy23.model.Card;
import com.stormy23.model.Document;
import com.stormy23.repositories.CardRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CardService {
    private final CardRepository cardRepository;

    public CardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    public List<Card> list() {
        return cardRepository.findAll();
    }

    public Card getByID(Long id) {
        return cardRepository.findById(id).orElse(null);
    }

    public void create(Card card) {
        cardRepository.save(card);
    }

    public void externByID(Long id, String externNumber) {
        Card card = getByID(id);
        card.setExtern_number(externNumber);
        card.setDate_extern(new Date());
        cardRepository.save(card);
    }
}
