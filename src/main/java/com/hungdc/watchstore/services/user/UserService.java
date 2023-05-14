package com.hungdc.watchstore.services.user;

import com.hungdc.watchstore.dtos.user.UserDto;
import com.hungdc.watchstore.entities.User;

public interface UserService {
    User getTaiKhoan(String id);
    User create (UserDto dto);
    User update (String id, UserDto dto);
    User delete (String id);
}
