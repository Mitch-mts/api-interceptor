package mts.mtech.apiinterceptor.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import mts.mtech.apiinterceptor.dto.quotes.QuoteDto;
import mts.mtech.apiinterceptor.services.quotes.QuotesService;
import mts.mtech.apiinterceptor.utils.Constants;
import mts.mtech.apiinterceptor.utils.Response;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/quotes")
@CrossOrigin
@Tag(name = "Quotes from inspirational speakers", description = "Random daily quotes ")
public class QuotesRestController {
    private final QuotesService quotesService;

    public QuotesRestController(QuotesService quotesService) {
        this.quotesService = quotesService;
    }

    @GetMapping
    public Response<QuoteDto> getQuote() {
        var response = quotesService.getQuote();
        return new Response<QuoteDto>().buildSuccessResponse(Constants.SUCCESS, response);
    }
}
