package com.fhce.egovf.service;

import com.fhce.egovf.dto.loginDtoRequest;
import com.fhce.egovf.dto.loginDtoResponse;

public interface authenticationService {
	loginDtoResponse login(loginDtoRequest loginDtoRequest);
	//userDto signup(signUpRequest signUpRequest);
	//jwtAuthenticationResponse signin(SigninRequest signinRequest);
	//jwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest);

}
