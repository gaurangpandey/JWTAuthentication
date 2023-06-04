package com.jwt.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JwtController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserDetailsService userDetail;

	@Autowired
	private JwtHelper helper;

	@PostMapping("/token")
	public ResponseEntity<?> generateToken(@RequestBody JwtRequestModel jwtRequest) throws Exception {
		System.out.println(jwtRequest);

		try {

			this.authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(jwtRequest.getUserName(), jwtRequest.getPassword()));

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("bad credential");
		}
		UserDetails userDetail = this.userDetail.loadUserByUsername(jwtRequest.getUserName());

		String token = this.helper.generateToken(userDetail);
		System.out.println("JWt =" + token);
		JwtResponseModal jwtResponseModal = new JwtResponseModal();
		jwtResponseModal.setToken(token);
		return ResponseEntity.ok(jwtResponseModal);
	}

}
