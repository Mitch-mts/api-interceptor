package mts.mtech.apiinterceptor.services.diabetes;

import mts.mtech.apiinterceptor.dto.diabetes.DiabetesRequest;
import mts.mtech.apiinterceptor.dto.diabetes.DiabetesResponse;
import mts.mtech.apiinterceptor.dto.diabetes.Result;

public interface DiabetesService {
    DiabetesResponse getDiabetesPrediction(String name);
    Result initiateDiabetesPrediction(DiabetesRequest request);
    DiabetesRequest getDiabetesPredictionRequest();
}
