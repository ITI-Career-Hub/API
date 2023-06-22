package gov.iti.career.hub.config;

import org.jose4j.jwa.AlgorithmConstraints;
import org.jose4j.jwk.RsaJsonWebKey;
import org.jose4j.jwk.RsaJwkGenerator;
import org.jose4j.jws.AlgorithmIdentifiers;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.consumer.JwtConsumer;
import org.jose4j.jwt.consumer.JwtConsumerBuilder;
import org.jose4j.lang.JoseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.security.PrivateKey;
import java.security.PublicKey;

@Configuration
public class RegistrationTokenConfig {

    @Value("registration.kid")
    private String kid;

    @Value("registration.kid")
    private String hostname;
    private int minutesToTokenExpiry = 1440; //TODO: FROM PROPERTIES FILE
    @Bean
    public RsaJsonWebKey rsaJsonWebKey() throws JoseException {
        RsaJsonWebKey rsaJsonWebKey = RsaJwkGenerator.generateJwk(2048);
        rsaJsonWebKey.setKeyId(kid);
        return rsaJsonWebKey;
    }

    @Bean
    public PrivateKey registrationPrivateKey(RsaJsonWebKey rsaJsonWebKey){
        return rsaJsonWebKey.getPrivateKey();
    }

    @Bean
    public PublicKey registrationPublicKey(RsaJsonWebKey rsaJsonWebKey){
        return rsaJsonWebKey.getPublicKey();
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public JsonWebSignature signedRegistrationToken(PrivateKey registrationPrivateKey){
        JsonWebSignature jws = new JsonWebSignature();
        jws.setKeyIdHeaderValue(kid);
        jws.setKey(registrationPrivateKey);
        jws.setAlgorithmHeaderValue(AlgorithmIdentifiers.RSA_PSS_USING_SHA512);
        return jws;
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public JwtClaims registrationClaims(){
        JwtClaims claims = new JwtClaims();
        claims.setIssuer(hostname);
        claims.setIssuedAtToNow();
        claims.setExpirationTimeMinutesInTheFuture(minutesToTokenExpiry);
        return claims;
    }

    @Bean
    public JwtConsumer jwtConsumer(RsaJsonWebKey rsaJsonWebKey){
        JwtConsumer jwtConsumer = new JwtConsumerBuilder()
                .setRequireExpirationTime()
                .setAllowedClockSkewInSeconds(30)
                .setRequireSubject()
                .setExpectedIssuer(hostname)
//                .setExpectedAudience(audience) TODO: WE CAN ADD THIS LATER DENOTING THE CLIENT_ID
                .setEnableRequireIntegrity()
                .setVerificationKey(rsaJsonWebKey.getKey())
                .setJwsAlgorithmConstraints(
                        AlgorithmConstraints.ConstraintType.PERMIT, AlgorithmIdentifiers.RSA_PSS_USING_SHA512)
                .build();
        return jwtConsumer;
    }

}
