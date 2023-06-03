package com.hungdc.watchstore.services.template;

import com.hungdc.watchstore.dtos.user.UserDto;
import com.hungdc.watchstore.entities.User;
import com.hungdc.watchstore.exceptions.NotFoundException;
import com.hungdc.watchstore.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeleteUserApi extends UserApiTemplate{
    @Autowired
    private UserRepository userRepository;
    @Override
    protected User createUser() {
        return null;
    }

    @Override
    protected void responseUser(User user, UserDto dto) {
        user.setEmail(user.getEmail());
        user.setPassword(user.getPassword());
        user.setName(user.getName());
        user.setTelephoneNumber(user.getTelephoneNumber());
    }

    @Override
    protected void saveUser(User user) {
        userRepository.delete(user);
    }

    @Override
    protected void performRequestSpecificAction(User user) {

    }

    @Override
    protected void mapUserDtoToUser(UserDto dto, User user) {

    }

    @Override
    protected User getUserFromDatabase(String id) {
        User user = getTaiKhoan(id);
        return user;
    }
    private User getTaiKhoan(String id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String
                        .format("Tài khoản có id %s không tồn tại", id)));
    }
    @Override
    protected void validationUserDTO(UserDto dto) {

    }
}
