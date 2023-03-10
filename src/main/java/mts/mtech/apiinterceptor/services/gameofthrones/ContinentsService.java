package mts.mtech.apiinterceptor.services.gameofthrones;

import mts.mtech.apiinterceptor.dto.gameofthrones.Continents;

import java.util.List;

public interface ContinentsService {
    List<Continents> getContinents();
    Continents getContinentById(Long id);

}
