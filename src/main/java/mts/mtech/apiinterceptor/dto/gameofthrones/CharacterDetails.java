package mts.mtech.apiinterceptor.dto.gameofthrones;

public record CharacterDetails(
         Long id,
         String firstName,
         String lastName,
         String fullName,
         String title,
         String family,
         String image,
         String imageUrl
) {

}
