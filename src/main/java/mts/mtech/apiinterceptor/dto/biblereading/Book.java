package mts.mtech.apiinterceptor.dto.biblereading;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    private Long id;
    private String name;
    private String testament;
}
