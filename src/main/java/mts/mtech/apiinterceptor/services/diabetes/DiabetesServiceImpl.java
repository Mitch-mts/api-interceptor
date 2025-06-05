package mts.mtech.apiinterceptor.services.diabetes;

import lombok.extern.slf4j.Slf4j;
import mts.mtech.apiinterceptor.domain.Diabetes;
import mts.mtech.apiinterceptor.dto.diabetes.DiabetesPredictionRequestDto;
import mts.mtech.apiinterceptor.dto.diabetes.DiabetesRequest;
import mts.mtech.apiinterceptor.dto.diabetes.DiabetesResponse;
import mts.mtech.apiinterceptor.dto.diabetes.Result;
import mts.mtech.apiinterceptor.dto.gameofthrones.CharacterDetails;
import mts.mtech.apiinterceptor.persistence.DiabetesRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

@Service
@Slf4j
public class DiabetesServiceImpl implements DiabetesService{
    private final RestTemplate restTemplate;
    private final DiabetesRepository diabetesRepository;

    @Value("${api.diabetes-url}")
    private String DIABETES_PREDICTION_URL;

    public DiabetesServiceImpl(RestTemplate restTemplate, DiabetesRepository diabetesRepository) {
        this.restTemplate = restTemplate;
        this.diabetesRepository = diabetesRepository;
    }

    @Override
    public DiabetesResponse getDiabetesPrediction(Long recordId) {
        Diabetes diabetesRecord =  diabetesRepository.findById(recordId).orElseThrow(() -> new RuntimeException("Record not found"));
        return DiabetesResponse.builder()
                .dateCreated(diabetesRecord.getDateCreated())
                .firstName(diabetesRecord.getFirstName())
                .lastName(diabetesRecord.getLastName())
                .predictionResult(diabetesRecord.isPredictionResult())
                .build();
    }

    @Override
    public Result initiateDiabetesPrediction(DiabetesRequest request) {
        DiabetesPredictionRequestDto requestDto = DiabetesPredictionRequestDto.builder()
                                                .diabetesPedigreeFunction(request.getDiabetesPedigreeFunction())
                                                .numberOfPregnancies(request.getNumberOfPregnancies())
                                                .skinThickness(request.getSkinThickness())
                                                .gender(request.getGender())
                                                .age(request.getAge())
                                                .bloodPressure(request.getBloodPressure())
                                                .bodyMassIndex(request.getBodyMassIndex())
                                                .glucoseLevel(request.getGlucoseLevel())
                                                .build();

        Diabetes diabetesRecord = Diabetes.builder()
                                .age(request.getAge())
                                .bloodPressure(requestDto.getBloodPressure())
                                .bodyMassIndex(requestDto.getBodyMassIndex())
                                .dateCreated(LocalDateTime.now())
                                .diabetesPedigreeFunction(requestDto.getDiabetesPedigreeFunction())
                                .firstName(request.getFirstName())
                                .gender(request.getGender())
                                .glucoseLevel(requestDto.getGlucoseLevel())
                                .insulinLevel(requestDto.getInsulinLevel())
                                .userName(request.getFirstName() + "-" + request.getLastName())
                                .lastName(request.getLastName())
                                .build();
        Diabetes savedRecord = diabetesRepository.save(diabetesRecord);

        try{
            getDiabetesPredictionModel(requestDto, savedRecord.getId());

        } catch (Exception ex) {
            log.error("Error while getting diabetes prediction: ", ex);
            throw new RuntimeException("Error while getting diabetes prediction: " + ex.getMessage());
        }

        return Result.builder()
                .message("SUCCESS")
                .success(true)
                .id(savedRecord.getId())
                .build();
    }


    @Async
    void getDiabetesPredictionModel(DiabetesPredictionRequestDto requestDto, Long recordId ) {
        DiabetesResponse response = callDiabetesPredictionModel(requestDto);

        Diabetes savedRecord = diabetesRepository.findById(recordId).orElseThrow(() -> new RuntimeException("Diabetes record not found"));
        savedRecord.setPredictionResult(response.isPredictionResult());
        savedRecord.setDateCreated(LocalDateTime.now());
        diabetesRepository.save(savedRecord);
    }

    private DiabetesResponse callDiabetesPredictionModel(DiabetesPredictionRequestDto requestDto) {
        try {
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<DiabetesPredictionRequestDto> httpEntity = new HttpEntity<>(requestDto, httpHeaders);
            log.info("body: {}", httpEntity);

            var response = restTemplate.exchange(
                    DIABETES_PREDICTION_URL,
                    HttpMethod.POST,
                    httpEntity,
                    new ParameterizedTypeReference<DiabetesResponse>() {
                    }
            ).getBody();

            log.info("response: {}", response);
            return response;

        } catch (Exception ex) {
            log.error("Diabetes prediction model failed: ", ex);
            throw new RuntimeException(ex.getMessage());
        }
    }
}
