package abhi.beerframework.MSSCBeerService.controllers;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import abhi.beerframework.MSSCBeerService.models.BeerDto;

@RequestMapping("/api/v1/beer")
@RestController
public class BeerController {

	
	@GetMapping("/{beerId}")
	public ResponseEntity<BeerDto> getBeerById(@PathVariable("beerId") UUID beerId) {
		
		//todo impl
		return new ResponseEntity<BeerDto>(BeerDto.builder().build(), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<BeerDto> saveNewBeer(@RequestBody @Validated BeerDto beerDto) {
		
		//todo impl
		return new ResponseEntity<BeerDto>(HttpStatus.CREATED);
	}
	
	@PostMapping("/{beerId}")
	public ResponseEntity<BeerDto> updateByBeerId(@PathVariable("beerId") UUID beerId, @RequestBody @Validated BeerDto beerDto) {
		
		//todo impl
		return new ResponseEntity<BeerDto>(HttpStatus.NO_CONTENT);
		//return new ResponseEntity<BeerDto>(HttpStatus.CONFLICT);
	}
	
	
}
