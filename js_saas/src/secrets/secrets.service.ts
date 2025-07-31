import { Injectable } from '@nestjs/common';
import { InfisicalSDK, Secret } from '@infisical/sdk'

@Injectable()
export class SecretsService {
  async getCredentials(uri:string, clientId:string, infisicalSecret:string, env:string, server:string): Promise<{username:string; password: string}> {
    
    const client = new InfisicalSDK({
      siteUrl: uri 
    });

    // Authenticate with Infisical
    await client.auth().universalAuth.login({
      clientId: clientId,
      clientSecret: infisicalSecret
    });

        
    const secretUser = await client.secrets().getSecret({
      environment: env, 
      secretName:"USER",
      projectId: "6c293bdb-b71d-4c18-8ee1-ce2739ceea14",
      secretPath: `/${server}/`,
    });

    const secretPassword = await client.secrets().getSecret({
      environment: env, 
      secretName:"PASSWORD",
      projectId: "6c293bdb-b71d-4c18-8ee1-ce2739ceea14",
      secretPath: `/${server}/`,
    });
    return {username:secretUser.secretValue, password: secretPassword.secretValue};
  }
}
