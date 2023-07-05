package mts.mtech.apiinterceptor.services.biblereading;

import mts.mtech.apiinterceptor.dto.biblereading.BibleReadingDto;

import java.util.List;

public interface BibleReadingService {
    List<BibleReadingDto> getReading(BibleReadingRequest request);
}
