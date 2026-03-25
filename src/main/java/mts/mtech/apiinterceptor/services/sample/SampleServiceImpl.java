package mts.mtech.apiinterceptor.services.sample;

import mts.mtech.apiinterceptor.dto.sample.SearchCriteria;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SampleServiceImpl implements SampleService{

    @Override
    public List<SearchCriteria> getSoccerTeams() {
        List<SearchCriteria> teams = new ArrayList<>();

        teams.add(new SearchCriteria("TEAMNAME", "Real Madrid"));
        teams.add(new SearchCriteria("KEYPLAYER", "Kyllian Mbappe"));
        teams.add(new SearchCriteria("COACH", "Carlo Ancelloti"));

        teams.add(new SearchCriteria("TEAMNAME", "Chelsea"));
        teams.add(new SearchCriteria("KEYPLAYER", "Cole Palmer"));
        teams.add(new SearchCriteria("COACH", "Enzo Maresca"));

        teams.add(new SearchCriteria("TEAMNAME", "Manchester City"));
        teams.add(new SearchCriteria("KEYPLAYER", "Erling Haaland"));
        teams.add(new SearchCriteria("COACH", "Pep Guardiola"));

        return teams;
    }
}
