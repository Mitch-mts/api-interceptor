package mts.mtech.apiinterceptor.services.biblereading;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BibleReadingRequest {
    private Long chapter;
    private Long book;
}
