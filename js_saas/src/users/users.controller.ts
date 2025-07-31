import { Controller, Get, Query } from '@nestjs/common';
import { UsersService } from './users.service';
import { UserDto } from './dto/user.dto';

@Controller('users')
export class UsersController {
  constructor(private readonly usersService: UsersService) {}

  @Get()
  async verifyEmail(@Query('email') email: string): Promise<UserDto> {
    const result = await this.usersService.getUserByEmail(email);
    return result;
  }
}
