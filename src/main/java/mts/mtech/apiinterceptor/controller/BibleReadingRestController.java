package mts.mtech.apiinterceptor.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import mts.mtech.apiinterceptor.dto.biblereading.BibleReadingDto;
import mts.mtech.apiinterceptor.services.biblereading.BibleReadingRequest;
import mts.mtech.apiinterceptor.services.biblereading.BibleReadingService;
import mts.mtech.apiinterceptor.utils.Constants;
import mts.mtech.apiinterceptor.utils.Response;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/bible-reader")
@Tag(name = " Bible reading APIs", description = "Bible reading API for bible chapters and verses")
@Slf4j
public class BibleReadingRestController {

    private final BibleReadingService bibleReadingService;

    public BibleReadingRestController(BibleReadingService bibleReadingService) {
        this.bibleReadingService = bibleReadingService;
    }

    @CrossOrigin
    @GetMapping("/book/{bookId}/chapter/{chapterId}")
    public Response<List<BibleReadingDto>> getReading(@PathVariable Long chapterId, @PathVariable Long bookId){
        BibleReadingRequest request = BibleReadingRequest.builder()
                .book(bookId)
                .chapter(chapterId)
                .build();

        var response = bibleReadingService.getReading(request);
        return new Response<List<BibleReadingDto>>().buildSuccessResponse(Constants.SUCCESS, response);
    }
}
