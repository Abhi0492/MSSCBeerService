package abhi.beerframework.MSSCBeerService.repositories;

import java.util.UUID;

import org.springframework.data.repository.PagingAndSortingRepository;

import abhi.beerframework.MSSCBeerService.domain.Beer;

public interface BeerRepository extends PagingAndSortingRepository<Beer, UUID> {
	
	

}
