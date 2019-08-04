package com.energy.consumption.service.client.imp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.energy.consumption.model.CounterDetail;
import com.energy.consumption.service.client.CounterDetailClient;

/**
 * This class make the communication with the counter detail API
 *
 * @author Wanessa Nascimento
 */
@Service
public class CounterDetailClientImp implements CounterDetailClient {

	private static final Logger LOGGER = LoggerFactory.getLogger(CounterDetailClientImp.class);

	@Autowired
	private RestTemplate restTemplate;

	@Value("${counter.detail.url.api}")
	private String urlCounterDetailAPI;
	
	@Value("${counter.detail.id.param}")
	private String idParam;

	@Override
	public CounterDetail getCounterDetail(Long id) {
		LOGGER.info("Calling web service counter detail");
		UriComponents uri = UriComponentsBuilder.fromUriString(urlCounterDetailAPI)
				.queryParam(idParam, id).build();
		return restTemplate.getForObject(uri.toUriString(), CounterDetail.class);
	}
}