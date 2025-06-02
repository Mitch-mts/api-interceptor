package mts.mtech.apiinterceptor.services.diabetes;

import mts.mtech.apiinterceptor.dto.diabetes.DiabetesRequest;
import mts.mtech.apiinterceptor.dto.diabetes.DiabetesResponse;
import mts.mtech.apiinterceptor.dto.diabetes.Result;
import org.springframework.stereotype.Service;

@Service
public class DiabetesServiceImpl implements DiabetesService{
    @Override
    public DiabetesResponse getDiabetesPrediction(String name) {
        return null;
    }

    @Override
    public Result initiateDiabetesPrediction(DiabetesRequest request) {
        return null;
    }

    @Override
    public DiabetesRequest getDiabetesPredictionRequest() {
        return null;
    }
}
