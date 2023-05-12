package com.hungdc.watchstore.services.user;

import com.hungdc.watchstore.repositories.UserRepository;
import com.hungdc.watchstore.dtos.user.UserDto;
import com.hungdc.watchstore.exceptions.InvalidException;
import com.hungdc.watchstore.exceptions.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public com.hungdc.watchstore.entities.User getTaiKhoan(String id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String
                        .format("Tài khoản có id %s không tồn tại", id)));
    }

    @Override
    public com.hungdc.watchstore.entities.User create(UserDto dto) {
        if (ObjectUtils.isEmpty(dto.getName())) {
            throw new InvalidException("Tên tài khoản không được bỏ trống");
        }
        if (ObjectUtils.isEmpty(dto.getEmail())) {
            throw new InvalidException("Email không được bỏ trống");
        }
        if (ObjectUtils.isEmpty(dto.getPassword())) {
            throw new InvalidException("Mật khẩu không được bỏ trống");
        }
        if (ObjectUtils.isEmpty(dto.getDienThoai())) {
            throw new InvalidException("Điện thoại không được bỏ trống");
        }
        if (ObjectUtils.isEmpty(dto.getRoles())) {
            throw new InvalidException("vai trò tài khoản không được bỏ trống");
        }
        if (userRepository.validateEmail(dto.getEmail().trim())) {
            throw new InvalidException(String.format("Email %s đã tồn tại",
                    dto.getEmail()));
        }
        com.hungdc.watchstore.entities.User user = new com.hungdc.watchstore.entities.User();
        user.setName(dto.getName().trim());
        user.setEmail(dto.getEmail().trim());
        user.setPassword(dto.getPassword());
        user.setTelephoneNumber(dto.getDienThoai().trim());
        user.setRoles(dto.getRoles());
        this.userRepository.save(user);
        return user;
    }

    @Override
    public com.hungdc.watchstore.entities.User update(String id, UserDto dto) {
        com.hungdc.watchstore.entities.User user = getTaiKhoan(id);
        if (ObjectUtils.isEmpty(dto.getName())) {
            throw new InvalidException("Tên tài khoản không được bỏ trống");
        }
        if (ObjectUtils.isEmpty(dto.getEmail())) {
            throw new InvalidException("Email không được bỏ trống");
        }
        if (ObjectUtils.isEmpty(dto.getPassword())) {
            throw new InvalidException("Mật khẩu không được bỏ trống");
        }
        if (ObjectUtils.isEmpty(dto.getDienThoai())) {
            throw new InvalidException("Điện thoại không được bỏ trống");
        }
        if (ObjectUtils.isEmpty(dto.getRoles())) {
            throw new InvalidException("vai trò tài khoản không được bỏ trống");
        }
        if (!user.getEmail().equalsIgnoreCase(dto.getEmail().trim())
                && this.userRepository.validateEmail(dto.getEmail().trim())){
            throw new InvalidException(String.format("Email %s đã tồn tại",
                    dto.getEmail()));
        }
        user.setName(dto.getName().trim());
        user.setEmail(dto.getEmail().trim());
        user.setPassword(dto.getPassword());
        user.setTelephoneNumber(dto.getDienThoai().trim());
        user.setRoles(dto.getRoles());
        this.userRepository.save(user);
        return user;
    }

    @Override
    public com.hungdc.watchstore.entities.User delete(String id) {
        com.hungdc.watchstore.entities.User user = getTaiKhoan(id);
        this.userRepository.delete(user);
        return user;
    }
}
