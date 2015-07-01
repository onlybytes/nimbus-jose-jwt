package com.nimbusds.jwt.proc;


import java.text.ParseException;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.proc.BadJOSEException;
import com.nimbusds.jose.proc.SecurityContext;
import com.nimbusds.jwt.EncryptedJWT;
import com.nimbusds.jwt.JWT;
import com.nimbusds.jwt.PlainJWT;
import com.nimbusds.jwt.SignedJWT;


/**
 * Interface for parsing and processing {@link com.nimbusds.jwt.PlainJWT
 * unsecured} (plain), {@link com.nimbusds.jwt.SignedJWT signed} and
 * {@link com.nimbusds.jwt.EncryptedJWT encrypted} JSON Web Tokens (JWTs).
 *
 * @author Vladimir Dzhuvinov
 * @version 2015-07-01
 */
public interface JWTProcessor<T, C extends SecurityContext> {


	/**
	 * Parses and processes the specified JWT (unsecured, signed or
	 * encrypted).
	 *
	 * @param jwtString The JWT, compact-encoded to a URL-safe string. Must
	 *                  not be {@code null}.
	 * @param context   Optional context of the JOSE object, {@code null}
	 *                  if not required.
	 *
	 * @return An application-specific object (the JWT claims) on success,
	 *         or {@code null} if no return value is necessary.
	 *
	 * @throws ParseException   If the string couldn't be parsed to a valid
	 *                          JWT.
	 * @throws BadJOSEException If the JWT is rejected.
	 * @throws JOSEException    If an internal processing exception is
	 *                          encountered.
	 */
	T process(final String jwtString, final C context)
		throws ParseException, BadJOSEException, JOSEException;


	/**
	 * Processes the specified JWT (unsecured, signed or encrypted).
	 *
	 * @param jwt     The JWT. Must not be {@code null}.
	 * @param context Optional context of the JOSE object, {@code null} if
	 *                not required.
	 *
	 * @return An application-specific object (the JWT claims) on success,
	 *         or {@code null} if no return value is necessary.
	 *
	 * @throws BadJOSEException If the JWT is rejected.
	 * @throws JOSEException    If an internal processing exception is
	 *                          encountered.
	 */
	T process(final JWT jwt, final C context)
		throws BadJOSEException, JOSEException;


	/**
	 * Processes the specified unsecured (plain) JWT, typically by checking
	 * its context.
	 *
	 * @param plainJWT The unsecured (plain) JWT. Not {@code null}.
	 * @param context  Optional context of the unsecured JWT, {@code null}
	 *                 if not required.
	 *
	 * @return An application-specific object (the JWT claims) on success,
	 *         or {@code null} if no return value is necessary.
	 *
	 * @throws BadJOSEException If the unsecured (plain) JWT is rejected,
	 *                          after examining the context or due to the
	 *                          payload not being a JSON object.
	 * @throws JOSEException    If an internal processing exception is
	 *                          encountered.
	 */
	T process(final PlainJWT plainJWT, final C context)
		throws BadJOSEException, JOSEException;


	/**
	 * Processes the specified signed JWT by verifying its signature. The
	 * key candidate(s) are selected by examining the JWS header and / or
	 * the message context.
	 *
	 * @param signedJWT The signed JWT. Not {@code null}.
	 * @param context   Optional context of the signed JWT, {@code null} if
	 *                  not required.
	 *
	 * @return An application-specific object (the JWT claims) on success,
	 *         or {@code null} if no return value is necessary.
	 *
	 * @throws BadJOSEException If the signed JWT is rejected, typically
	 *                          due to a bad signature or the payload not
	 *                          being a JSON object.
	 * @throws JOSEException    If an internal processing exception is
	 *                          encountered.
	 */
	T process(final SignedJWT signedJWT, final C context)
		throws BadJOSEException, JOSEException;


	/**
	 * Processes the specified encrypted JWT by decrypting it. The key
	 * candidate(s) are selected by examining the JWS header and / or the
	 * message context.
	 *
	 * @param encryptedJWT The encrypted JWT. Not {@code null}.
	 * @param context      Optional context of the encrypted JWT,
	 *                     {@code null} if not required.
	 *
	 * @return An application-specific object (the JWT claims) on success,
	 *         or {@code null} if no return value is necessary.
	 *
	 * @throws BadJOSEException If the encrypted JWT is rejected, typically
	 *                          due to failed decryption or the payload not
	 *                          being a JSON object.
	 * @throws JOSEException    If an internal processing exception is
	 *                          encountered.
	 */
	T process(final EncryptedJWT encryptedJWT, final C context)
		throws BadJOSEException, JOSEException;
}