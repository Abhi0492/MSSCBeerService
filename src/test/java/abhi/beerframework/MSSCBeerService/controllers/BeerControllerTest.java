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
import static org.springframework.restdocs.request.RequestDocumentation.requestParameters;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
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
		mockMvc.perform(get("/api/v1/beer/{beerId}",UUID.randomUUID().toString())
				.param("iscold", "yes")
				.accept(MediaType.APPLICATION_JSON))
	         	.andExpect(MockMvcResultMatchers.status().isOk())
	         	.andDo(document("v1/beer", 
	         			pathParameters(
	         					parameterWithName("beerId").description("UUID of desired beer to get.")
	         			),
	         			requestParameters(
	         					parameterWithName("iscold").description("Is Beer Cold Query parameter.")
	         			),
	         			responseFields(
	         					fieldWithPath("id").description("Id of Beer."),
	         					fieldWithPath("version").description("Version of Beer."),
	         					fieldWithPath("createdDate").description("Created Date of Beer."),
	         					fieldWithPath("lastModifiedDate").description("Last Modified Date of Beer."),
	         					fieldWithPath("beerName").description("Name of Beer."),
	         					fieldWithPath("beerStyle").description("Style of Beer."),
	         					fieldWithPath("upc").description("UPC of Beer."),
	         					fieldWithPath("price").description("Price of Beer."),
	         					fieldWithPath("quantityOnHand").description("Quantity On Hand of Beer.")
	         			)
	         		));
		
	}
	
	@Test
	void saveNewBeer() throws Exception {
		
		//BeerDto beerDto = BeerDto.builder().build();
		BeerDto beerDto = getValidBeer();
		String beerDtoJson = objectMapper.writeValueAsString(beerDto);
		
		mockMvc.perform(post("/api/v1/beer/")
				.contentType(MediaType.APPLICATION_JSON)
				.content(beerDtoJson))
				.andExpect(MockMvcResultMatchers.status().isCreated())
				.andDo(document("v1/beer", 
						requestFields(
								fieldWithPath("id").ignored(),
								fieldWithPath("version").ignored(),
								fieldWithPath("createdDate").ignored(),
								fieldWithPath("lastModifiedDate").ignored(),
								fieldWithPath("beerName").description("Name of the Beer."),
								fieldWithPath("beerStyle").description("Style of the Beer."),
								fieldWithPath("upc").description("UPC of the Beer."),
								fieldWithPath("price").description("Price of the Beer."),
								fieldWithPath("quantityOnHand").ignored()
							)
					));
		
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
