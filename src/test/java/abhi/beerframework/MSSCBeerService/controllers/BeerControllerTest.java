package abhi.beerframework.MSSCBeerService.controllers;


import java.math.BigDecimal;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
//import static org.mockito.ArgumentMatcher.any;
import static org.mockito.BDDMockito.given;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.xml.txw2.Document;

import abhi.beerframework.MSSCBeerService.models.BeerDto;
import abhi.beerframework.MSSCBeerService.models.BeerStyleEnum;
import abhi.beerframework.MSSCBeerService.repositories.BeerRepository;


@ExtendWith(RestDocumentationExtension.class)
@AutoConfigureRestDocs
@WebMvcTest(BeerController.class)
@ComponentScan(basePackages =  "abhi.beerframework.MSSCBeerService.mappers")
public class BeerControllerTest {

	@Autowired
	MockMvc mockMvc;
	
	@Autowired
	ObjectMapper objectMapper;
	
	@MockBean
	BeerRepository beerRepository;
	
	@Test
	void getBeerById() throws Exception {
		
		
		//mockMvc.perform(get("/api/v1/beer/" + UUID.randomUUID().toString()).accept(MediaType.APPLICATION_JSON))
		mockMvc.perform(get("/api/v1/beer/{beerId}",UUID.randomUUID().toString()).accept(MediaType.APPLICATION_JSON))
	         	.andExpect(MockMvcResultMatchers.status().isOk())
	         	.andDo(document("v1/beer", pathParameters(
	         			parameterWithName("beerId").description("UUID of desired beer to get.")
	         			)));
		
	}
	
	@Test
	void saveNewBeer() throws Exception {
		
		//BeerDto beerDto = BeerDto.builder().build();
		BeerDto beerDto = getValidBeer();
		String beerDtoJson = objectMapper.writeValueAsString(beerDto);
		
		mockMvc.perform(post("/api/v1/beer/")
				.contentType(MediaType.APPLICATION_JSON)
				.content(beerDtoJson))
				.andExpect(MockMvcResultMatchers.status().isCreated());
		
	}
	
	@Test
	void updateByBeerId() throws Exception {
		
		//BeerDto beerDto = BeerDto.builder().build();
		BeerDto beerDto = getValidBeer();
		String beerDtoString = objectMapper.writeValueAsString(beerDto);
		
		mockMvc.perform(put("/api/v1/beer/" + UUID.randomUUID().toString())
				.contentType(MediaType.APPLICATION_JSON)
				.content(beerDtoString))
				.andExpect(MockMvcResultMatchers.status().isNoContent());
		
	}
	
	BeerDto getValidBeer() {
		return BeerDto.builder()
				.beerName("My Beer")
				.beerStyle(BeerStyleEnum.AMERICAN_LAGER)
				.price(new BigDecimal(160.34))
				.upc(123123123123L)
				.build();
	}

}
