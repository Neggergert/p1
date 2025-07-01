package com.example.bookexchange.service;

import com.example.bookexchange.dto.CreateTradeRequest;
import com.example.bookexchange.dto.TradeDto;
import com.example.bookexchange.model.Book;
import com.example.bookexchange.model.Trade;
import com.example.bookexchange.repository.TradeRepository;
import com.example.bookexchange.model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TradeService {
    private final TradeRepository tradeRepository;
    private final BookService bookService;

    public TradeService(TradeRepository tradeRepository, BookService bookService) {
        this.tradeRepository = tradeRepository;
        this.bookService = bookService;
    }

    public TradeDto makeTrade(CreateTradeRequest request) {
        Book offered = bookService.getBook(request.offeredBookId());
        Book requested = bookService.getBook(request.requestedBookId());

        User offeredOwner = offered.getOwner();
        offered.setOwner(requested.getOwner());
        requested.setOwner(offeredOwner);

        Trade trade = new Trade(offered, requested);
        tradeRepository.save(trade);
        return DtoMapper.toDto(trade);
    }

    @Transactional(readOnly = true)
    public List<TradeDto> getTrades() {
        return tradeRepository.findAll().stream().map(DtoMapper::toDto).toList();
    }
}
