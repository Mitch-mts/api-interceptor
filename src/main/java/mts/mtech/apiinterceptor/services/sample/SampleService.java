package mts.mtech.apiinterceptor.services.sample;

import mts.mtech.apiinterceptor.dto.sample.SearchCriteria;

import java.util.List;

public interface SampleService {
    List<SearchCriteria> getSoccerTeams();
}
