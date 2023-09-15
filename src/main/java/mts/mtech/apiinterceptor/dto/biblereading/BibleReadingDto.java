package mts.mtech.apiinterceptor.dto.biblereading;

public record BibleReadingDto(Long id, Book book, Long chapterId, Long VerseId, String verse) {
}
