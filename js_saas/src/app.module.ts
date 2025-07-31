import { Module } from '@nestjs/common';
import { MongooseModule } from '@nestjs/mongoose';
import { UsersModule } from './users/users.module';
import { UsersService } from './users/users.service';
import { UsersController } from './users/users.controller';

@Module({
  imports: [
    MongooseModule.forRoot('mongodb://root:example@localhost/nestdb?authSource=admin'), // Change as needed
    UsersModule,
  ],
  controllers: [],
  providers: [],
})
export class AppModule {}
