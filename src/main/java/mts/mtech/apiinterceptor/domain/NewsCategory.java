//package mts.mtech.apiinterceptor.domain;
//
//import lombok.*;
//import org.springframework.data.jpa.domain.support.AuditingEntityListener;
//
//import javax.persistence.*;
//
//@Entity
//@Table(name = "user_account")
//@TableGenerator(name = "category_id_generator",
//        table = "id_generator",
//        pkColumnName = "id_name",
//        pkColumnValue = "users",
//        valueColumnName = "id_value")
//@Getter
//@Setter
//@ToString
//@Builder
//@NoArgsConstructor
//@AllArgsConstructor
//@EntityListeners(AuditingEntityListener.class)
//public class NewsCategory {
//    @Id
//    @GeneratedValue(generator = "category_id_generator")
//    @Column(name = "id", nullable = false, updatable = false)
//    private Long id;
//
//
//}
