import { Module } from '@nestjs/common';
import { MongooseModule } from '@nestjs/mongoose';
import { UsersModule } from './users/users.module';
import { ConfigModule, ConfigService } from '@nestjs/config';
import { SecretsModule } from './secrets/secrets.module';
import { SecretsService } from './secrets/secrets.service';
/*
@Module({
  imports: [
    ConfigModule.forRoot(), // Loads .env or other config sources
    MongooseModule.forRoot('mongodb://root:example@localhost/nestdb?authSource=admin'), // Change as needed
    UsersModule,
  ],
  controllers: [],
  providers: [AppService],
})
*/

/**
 * @Module({
  imports: [
    MongoConfigModule,
    MongooseModule.forRootAsync({
      imports: [MongoConfigModule],
      useFactory: async (configService: MongoConfigService) => ({
        uri: configService.getMongoUri(),
      }),
      inject: [MongoConfigService],
    }),
  ],
})
 */

@Module({
  imports: [
    SecretsModule,
    ConfigModule.forRoot(), // Loads .env or other config sources
    MongooseModule.forRootAsync({
      imports: [ConfigModule, SecretsModule],
      useFactory: async (configService: ConfigService, secretsService: SecretsService) => {
        const mongoHost = configService.get<string>('MONGO_HOST');
        const mongoPort = configService.get<string>('MONGO_PORT');
        const mongoDbName = configService.get<string>('MONGO_DBNAME');
        const infisicalUri = configService.get<string>('INFISICAL_URI')??'';
        const infisicalClientId = configService.get<string>('INFISICAL_CLIENTID')??'';
        const infisicalSecret = configService.get<string>('INFISICAL_SECRET')??'';
        const infiscalEnv = configService.get<string>('INFISICAL_ENV')??'';

        const secret = await secretsService.getCredentials(infisicalUri,infisicalClientId,infisicalSecret,infiscalEnv,"mongo");

        const connectionString = `mongodb://${secret.username}:${secret.password}@${mongoHost}:${mongoPort}/${mongoDbName}?authSource=admin`;

        return {
          uri: connectionString,
        };
      },
      inject: [ConfigService, SecretsService],
    }),
    UsersModule, 
  ],
  controllers: [],
  providers: [],
})

export class AppModule {}
