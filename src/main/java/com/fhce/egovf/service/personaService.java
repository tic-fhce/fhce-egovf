package com.fhce.egovf.service;

import java.util.List;

import com.fhce.egovf.dto.personaDtoRequest;
import com.fhce.egovf.dto.personaDtoResponse;

public interface personaService {
	personaDtoResponse getPersona(Long cif);
	List<personaDtoResponse>getPersonas();
	personaDtoResponse addPersona(personaDtoRequest personaDtoRequest)throws Exception;
	personaDtoResponse updatePersona(personaDtoResponse personaDtoResponse);
}
