package com.bluetoolcase.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoClientFactoryBean;

import com.bluetoolcase.demo.datalayer.UserService;
import com.infisical.sdk.InfisicalSdk;
import com.infisical.sdk.config.SdkConfig;
import com.infisical.sdk.util.InfisicalException;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

@Configuration
public class ApplicationConfiguration {

    @Value("${btc.mongo.connection.uri}")
    private String mongoConnectionString;

    @Value("${btc.infisical.uri}")
    private String infisicalUri;

    @Value("${btc.infisical.client.id}")
    private String infisicalClientId;

    @Value("${btc.infisical.client.secret}")
    private String infisicalClientSecret;

    @Value("${btc.infisical.env}")
    private String infisicalEnv;

    @Bean()
	public ApplicationRunner initRunner(final UserService userService){
		return args -> {
			userService.clearAllUsers();
			userService.createUser("galjoey", "galjoey@gmail.com");
			userService.createUser("jgaleamt", "jgaleamt@outlook.com");
			userService.createUser("toastmasterjoegalea", "toastmasterjoegalea@gmail.com");
		};
	}

    @Bean
	public InfisicalSdk initInfisicalClient() throws InfisicalException{
		var sdk = new InfisicalSdk(
				new SdkConfig.Builder()
					.withSiteUrl(this.infisicalUri) 
					.build()
			);
		
		sdk.Auth().UniversalAuthLogin(
			this.infisicalClientId,
			this.infisicalClientSecret
		);
		return sdk;
    }

    @Bean
    public MongoClient mongoClient(final InfisicalSdk sdk) throws InfisicalException {
        final String username = fetchUsernameFromSecureStore(sdk);
        final String password = fetchPasswordFromSecureStore(sdk);

        String uri = this.mongoConnectionString;
        uri = uri.replace("<username>", username);
        uri = uri.replace("<password>", password);
        final ConnectionString connectionString = new ConnectionString(uri);

        final MongoClientSettings settings = MongoClientSettings.builder()
            .applyConnectionString(connectionString)
            .build();

        return MongoClients.create(settings);
    }

    private String fetchUsernameFromSecureStore(final InfisicalSdk sdk) throws InfisicalException {
        var secret = sdk.Secrets().GetSecret(
            "USER",
            "6c293bdb-b71d-4c18-8ee1-ce2739ceea14",
            this.infisicalEnv,
            "/mongo/",
            null, // Expand Secret References (boolean, optional)
            null, // Include Imports (boolean, optional)
            null  // Secret Type (shared/personal, defaults to shared, optional)
            );
        return secret.getSecretValue();
    }

    private String fetchPasswordFromSecureStore(final InfisicalSdk sdk) throws InfisicalException {
         var secret = sdk.Secrets().GetSecret(
            "PASSWORD",
            "6c293bdb-b71d-4c18-8ee1-ce2739ceea14",
            this.infisicalEnv,
            "/mongo/",
            null, // Expand Secret References (boolean, optional)
            null, // Include Imports (boolean, optional)
            null  // Secret Type (shared/personal, defaults to shared, optional)
            );
        return secret.getSecretValue();
    }
}
