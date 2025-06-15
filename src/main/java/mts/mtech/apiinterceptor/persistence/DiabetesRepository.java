package mts.mtech.apiinterceptor.persistence;

import mts.mtech.apiinterceptor.domain.Diabetes;

import java.util.Optional;

public interface DiabetesRepository extends BaseRepository<Diabetes>{
    Optional<Diabetes> findByReferenceId(String referenceId);
}
