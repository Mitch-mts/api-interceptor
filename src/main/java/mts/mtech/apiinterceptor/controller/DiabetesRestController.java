package mts.mtech.apiinterceptor.controller;

import mts.mtech.apiinterceptor.dto.diabetes.DiabetesRequest;
import mts.mtech.apiinterceptor.dto.diabetes.DiabetesResponse;
import mts.mtech.apiinterceptor.dto.diabetes.Result;
import mts.mtech.apiinterceptor.dto.news.Response;
import mts.mtech.apiinterceptor.services.diabetes.DiabetesService;
import mts.mtech.apiinterceptor.utils.Constants;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/v1/diabetes")
public class DiabetesRestController {
    private final DiabetesService diabetesService;


    public DiabetesRestController(DiabetesService diabetesService) {
        this.diabetesService = diabetesService;
    }

    @PostMapping(value = "/initiate-prediction")
    public Response<Result> initiatePrediction(@RequestBody @Valid DiabetesRequest diabetesRequest) {
        var response = diabetesService.initiateDiabetesPrediction(diabetesRequest);
        return new Response<Result>().buildSuccessResponse(Constants.SUCCESS, response);
    }

    @GetMapping
    public Response<DiabetesResponse> getDiabetesPrediction(@RequestParam Long recordId) {
        var response = diabetesService.getDiabetesPrediction(recordId);
        return new Response<DiabetesResponse>().buildSuccessResponse(Constants.SUCCESS, response);
    }
}
