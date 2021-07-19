package abhi.beerframework.MSSCBeerService.bootstrap;

import java.math.BigDecimal;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import abhi.beerframework.MSSCBeerService.domain.Beer;
import abhi.beerframework.MSSCBeerService.repositories.BeerRepository;

@Component
public class BeerLoader implements CommandLineRunner {
	
	private final BeerRepository beerRepository;
	

	public BeerLoader(BeerRepository beerRepository) {
		super();
		this.beerRepository = beerRepository;
	}


	@Override
	public void run(String... args) throws Exception {
		
		loadBeerObjects();
	}
	
	
	public void loadBeerObjects() {
		
		if(beerRepository.count() == 0) {
			
			beerRepository.save(Beer
					.builder()
					.beerName("Corona")
					.beerStyle("Pale Lager")
					.qtyToBrew(200)
					.minOnHand(12)
					.upc(3370100000000L)
					.price(new BigDecimal("12.95"))
					.build());
			
			beerRepository.save(Beer
					.builder()
					.beerName("Heineken")
					.beerStyle("Pale Lager")
					.qtyToBrew(200)
					.minOnHand(12)
					.upc(3380100000000L)
					.price(new BigDecimal("11.95"))
					.build());
			
		}
		
		System.out.println("Loaded Beers " + beerRepository.count());
	}

}
