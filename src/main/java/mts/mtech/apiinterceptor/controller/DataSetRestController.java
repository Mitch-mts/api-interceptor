package mts.mtech.apiinterceptor.controller;

import mts.mtech.apiinterceptor.dto.datasets.AthletesDto;
import mts.mtech.apiinterceptor.dto.news.Response;
import mts.mtech.apiinterceptor.services.datasets.DataSetService;
import mts.mtech.apiinterceptor.utils.Constants;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/datasets")
@CrossOrigin
public class DataSetRestController {
    private DataSetService dataSetService;

    public DataSetRestController(DataSetService dataSetService) {
        this.dataSetService = dataSetService;
    }

    @GetMapping("/athletes")
    public Response<AthletesDto> getAthletes() {
        try{
            AthletesDto athletesDto = dataSetService.getAthletes();
            return new Response<AthletesDto>().buildSuccessResponse(Constants.SUCCESS, athletesDto);
        } catch (Exception e){
            return new Response<AthletesDto>().buildErrorResponse(Constants.FAILURE,null);
        }
    }
}
