package mts.mtech.apiinterceptor.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import mts.mtech.apiinterceptor.dto.datasets.AthletesDto;
import mts.mtech.apiinterceptor.services.datasets.DataSetService;
import mts.mtech.apiinterceptor.utils.Constants;
import mts.mtech.apiinterceptor.utils.Response;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/datasets")
@CrossOrigin
@Tag(name = "Dataset APIs", description = "Retrieve data from different datasets")
public class DataSetRestController {
    private final DataSetService dataSetService;

    public DataSetRestController(DataSetService dataSetService) {
        this.dataSetService = dataSetService;
    }

    @GetMapping("/athletes")
    public Response<AthletesDto> getAthletes() {
        try{
            AthletesDto athletesDto = dataSetService.getAthletes();
            return new Response<AthletesDto>().buildSuccessResponse(Constants.SUCCESS, athletesDto);
        } catch (Exception e){
            return new Response<AthletesDto>().buildErrorResponse(Constants.FAILURE);
        }
    }
}
