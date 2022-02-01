/*
 * Copyright 2018-2019, https://beingtechie.io.
 *
 * File: JwtTokenProvider.java
 * Date: May 5, 2018
 * Author: Thribhuvan Krishnamurthy
 */
package com.upgrad.bookmyconsultation.provider.token;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.upgrad.bookmyconsultation.exception.GenericErrorCode;
import com.upgrad.bookmyconsultation.exception.UnexpectedException;

import java.time.ZonedDateTime;
import java.time.temporal.ChronoField;
import java.util.Date;
import java.util.UUID;

/**
 * Provider to serialize/deserialize the JWT specification based tokens.
 */
public class JwtTokenProvider {

	private static final String TOKEN_ISSUER = "https://bookmyconsultation.com";

	private final Algorithm algorithm;

	public JwtTokenProvider(final String secret) {
		try {
			algorithm = Algorithm.HMAC512(secret);
		} catch (IllegalArgumentException e) {
			throw new UnexpectedException(GenericErrorCode.GEN_001);
		}
	}

	public String generateToken(final String userUuid, final ZonedDateTime issuedDateTime, final ZonedDateTime expiresDateTime) {

		final Date issuedAt = new Date(issuedDateTime.getLong(ChronoField.INSTANT_SECONDS));
		final Date expiresAt = new Date(expiresDateTime.getLong(ChronoField.INSTANT_SECONDS));

		return JWT.create().withIssuer(TOKEN_ISSUER) //
				.withKeyId(UUID.randomUUID().toString())
				.withAudience(userUuid) //
				.withIssuedAt(issuedAt).withExpiresAt(expiresAt).sign(algorithm);
	}

}