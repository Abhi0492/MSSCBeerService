package abhi.beerframework.MSSCBeerService.mappers;

import org.mapstruct.Mapper;

import abhi.beerframework.MSSCBeerService.domain.Beer;
import abhi.beerframework.MSSCBeerService.models.BeerDto;



@Mapper(uses = {DateMapper.class})
public interface BeerMapper {
	
	BeerDto beerToBeerDto(Beer beer);
	
	Beer beerdtoToBeer(BeerDto beerDto);

}
