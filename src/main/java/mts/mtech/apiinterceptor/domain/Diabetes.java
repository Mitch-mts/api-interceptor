package mts.mtech.apiinterceptor.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import mts.mtech.apiinterceptor.dto.diabetes.Gender;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;


@Entity
@Table(name = "diabetes_details")
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Diabetes implements Serializable {
    @Id
    @GeneratedValue(generator = "diabetes_id_generator")
    @Column
    private Long id;
    @Column
    private String firstName;
    @Column
    private String lastName;
    @Column
    private Integer age;
    @Column
    private Gender gender;
    @Column
    private Double bodyMassIndex;
    @Column
    private Double bloodPressure;
    @Column
    private Double glucoseLevel;
    @Column
    private Double insulinLevel;
    @Column
    private String skinThickness;
    @Column
    private Integer numberOfPregnancies;
    @Column
    private Double diabetesPedigreeFunction;
    @Column
    private boolean predictionResult;
    @Column
    @JsonFormat(pattern = "yyyy-MM-dd : HH:mm")
    private LocalDateTime dateCreated;
}
