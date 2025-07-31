import { Injectable, NotFoundException, OnModuleInit } from '@nestjs/common';
import { InjectModel } from '@nestjs/mongoose';
import { Model } from 'mongoose';
import { User, UserDocument } from './schemas/user.schema';
import { UserDto } from './dto/user.dto';

@Injectable()
export class UsersService implements OnModuleInit {
  constructor(@InjectModel(User.name) private userModel: Model<UserDocument>) {}

  async onModuleInit() {
    await this.userModel.deleteMany({});
    await this.userModel.insertMany([
      { username: 'alice', email: 'alice@example.com' },
      { username: 'bob', email: 'bob@example.com' },
      { username: 'charlie', email: 'charlie@example.com' },
    ]);
    console.log('User collection initialized.');
  }

  async getUserByEmail(email: string): Promise<UserDto> {
    var user = await this.userModel.findOne({ email });
    if(user){
        var result =new UserDto();
        result.email = user.email;
        result.username = user.username;
        result.id = user._id;
        return result;
    } else {
        throw new NotFoundException(`User with email ${email} was not found`);
    }
  }
}
