package mts.mtech.apiinterceptor.dto.biblereading;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BibleReadingDto {
    private Long id;
    private Book book;
    private Long chapterId;
    private Long verseId;
    private String verse;
}
