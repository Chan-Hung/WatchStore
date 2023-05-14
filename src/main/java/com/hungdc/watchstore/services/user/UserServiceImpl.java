package com.hungdc.watchstore.services.user;

import com.hungdc.watchstore.dtos.user.UserDto;
import com.hungdc.watchstore.entities.User;
import com.hungdc.watchstore.exceptions.InvalidException;
import com.hungdc.watchstore.exceptions.NotFoundException;
import com.hungdc.watchstore.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Collections;

import static com.hungdc.watchstore.utils.EnumRole.ROLE_USER;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getTaiKhoan(String id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String
                        .format("Tài khoản có id %s không tồn tại", id)));
    }

    @Override
    public User create(UserDto dto) {
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
        User user = new User();
        user.setName(dto.getName().trim());
        user.setEmail(dto.getEmail().trim());
        user.setPassword(dto.getPassword());
        user.setTelephoneNumber(dto.getTelephoneNumber().trim());
        user.setRoles(Collections.singletonList(ROLE_USER.name()));
        userRepository.save(user);

        return user;
    }

    @Override
    public User update(String id, UserDto dto) {
        User user = getTaiKhoan(id);
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
        if (!user.getEmail().equalsIgnoreCase(dto.getEmail().trim())
                && this.userRepository.validateEmail(dto.getEmail().trim())){
            throw new InvalidException(String.format("Email %s đã tồn tại",
                    dto.getEmail()));
        }
        user.setName(dto.getName().trim());
        user.setEmail(dto.getEmail().trim());
        user.setPassword(dto.getPassword());
        user.setTelephoneNumber(dto.getTelephoneNumber().trim());
        user.setRoles(Collections.singletonList(ROLE_USER.name()));
        userRepository.save(user);
        return user;
    }

    @Override
    public User delete(String id) {
        User user = getTaiKhoan(id);
        this.userRepository.delete(user);
        return user;
    }
}
