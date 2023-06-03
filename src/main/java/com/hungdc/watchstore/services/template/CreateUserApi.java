package com.hungdc.watchstore.services.template;

import com.hungdc.watchstore.dtos.user.UserDto;
import com.hungdc.watchstore.entities.User;
import com.hungdc.watchstore.exceptions.InvalidException;
import com.hungdc.watchstore.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.Collections;

import static com.hungdc.watchstore.utils.EnumRole.ROLE_USER;

@Component
public class CreateUserApi extends UserApiTemplate{
    @Autowired
    private UserRepository userRepository;

    @Override
    protected User createUser() {
        User user=new User();
        return user;
    }

    @Override
    protected void responseUser(User user, UserDto dto) {
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setName(dto.getName());
        user.setTelephoneNumber(dto.getTelephoneNumber());
    }

    @Override
    protected void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    protected void performRequestSpecificAction(User user) {

    }

    @Override
    protected void mapUserDtoToUser(UserDto dto, User user) {
        user.setName(dto.getName().trim());
        user.setEmail(dto.getEmail().trim());
        user.setPassword(dto.getPassword());
        user.setTelephoneNumber(dto.getTelephoneNumber().trim());
        user.setRoles(Collections.singletonList(ROLE_USER.name()));
    }

    @Override
    protected User getUserFromDatabase(String id) {
        return null;
    }

    @Override
    protected void validationUserDTO(UserDto dto) {
        if (ObjectUtils.isEmpty(dto.getName())) {
            throw new InvalidException("Tên tài khoản không được bỏ trống");
        }
        if (ObjectUtils.isEmpty(dto.getEmail())) {
            throw new InvalidException("Email không được bỏ trống");
        }
        if (ObjectUtils.isEmpty(dto.getPassword())) {
            throw new InvalidException("Mật khẩu không được bỏ trống");
        }
        if (ObjectUtils.isEmpty(dto.getTelephoneNumber())) {
            throw new InvalidException("Điện thoại không được bỏ trống");
        }

        if (userRepository.validateEmail(dto.getEmail().trim())) {
            throw new InvalidException(String.format("Email %s đã tồn tại",
                    dto.getEmail()));
        }
    }
}
