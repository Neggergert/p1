package com.example.bookexchange.controller;

import com.example.bookexchange.dto.CreateTradeRequest;
import com.example.bookexchange.dto.TradeDto;
import com.example.bookexchange.service.TradeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trades")
@Tag(name = "Trades")
public class TradeController {
    private final TradeService tradeService;

    public TradeController(TradeService tradeService) {
        this.tradeService = tradeService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Make trade")
    public TradeDto trade(@Validated @RequestBody CreateTradeRequest request) {
        return tradeService.makeTrade(request);
    }

    @GetMapping
    @Operation(summary = "List trades")
    public List<TradeDto> list() {
        return tradeService.getTrades();
    }
}
