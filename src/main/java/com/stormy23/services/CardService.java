package com.stormy23.services;

import com.stormy23.model.Card;
import com.stormy23.model.Document;
import com.stormy23.repositories.CardRepository;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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

    public Card get(Document document) {
        return cardRepository.getCardByDocument(document);
    }

    public Card create(Card card) {
        return cardRepository.save(card);
    }

    public void extern(Card card, String externNumber) {
        card.setExternnumber(externNumber);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dateWithoutTime;
        try {
             dateWithoutTime = sdf.parse(sdf.format(new Date()));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        card.setDateextern(dateWithoutTime);
        cardRepository.save(card);
    }
}
