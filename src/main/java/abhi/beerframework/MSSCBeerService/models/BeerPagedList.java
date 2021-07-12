package abhi.beerframework.MSSCBeerService.models;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageImpl;

public class BeerPagedList extends PageImpl<BeerDto> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7147439657516847555L;

	public BeerPagedList(List<BeerDto> content, Pageable pageable, long total) {
		super(content, pageable, total);

	}
	
	public BeerPagedList(List<BeerDto> content) {
		super(content);
	}

}
