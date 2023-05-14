package com.hungdc.watchstore.services.address;

import com.hungdc.watchstore.dtos.addressdto.AddressDto;
import com.hungdc.watchstore.entities.Address;
import com.hungdc.watchstore.entities.Category;
import com.hungdc.watchstore.exceptions.InvalidException;
import com.hungdc.watchstore.exceptions.NotFoundException;
import com.hungdc.watchstore.repositories.AddressRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Slf4j
@Service
public class AddressServiceImpl implements AddressService{
    private final AddressRepository addressRepository;

    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }
    @Override
    public Address getAddress(String id) {
        return addressRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Địa chỉ không tồn tại"));
    }

    @Override
    public Address create(AddressDto dto) {
        if (ObjectUtils.isEmpty(dto.getEmail())) {
            throw new InvalidException("Email khách hàng không được bỏ trống");
        }
        if (ObjectUtils.isEmpty(dto.getHomeNumber())) {
            throw new InvalidException("Số nhà không được bỏ trống");
        }
        if (ObjectUtils.isEmpty(dto.getWard())) {
            throw new InvalidException("Tên phường/Xã không được bỏ trống");
        }
        if (ObjectUtils.isEmpty(dto.getCity())) {
            throw new InvalidException("Tên thành phố không được bỏ trống");
        }

        Address address = new Address();
        address.setEmail(dto.getEmail().trim());
        address.setHomeNumber(dto.getHomeNumber());
        address.setWard(dto.getWard());
        address.setCity(dto.getCity());

        addressRepository.save(address);
        return address;
    }

    @Override
    public Address update(String id, AddressDto dto) {
        Address address =getAddress(id);
        if (ObjectUtils.isEmpty(dto.getEmail())) {
            throw new InvalidException("Email khách hàng không được bỏ trống");
        }
        if (ObjectUtils.isEmpty(dto.getHomeNumber())) {
            throw new InvalidException("Số nhà không được bỏ trống");
        }
        if (ObjectUtils.isEmpty(dto.getWard())) {
            throw new InvalidException("Tên phường/Xã không được bỏ trống");
        }
        if (ObjectUtils.isEmpty(dto.getCity())) {
            throw new InvalidException("Tên thành phố không được bỏ trống");
        }

        address.setEmail(dto.getEmail().trim());
        address.setHomeNumber(dto.getHomeNumber());
        address.setWard(dto.getWard());
        address.setCity(dto.getCity());

        addressRepository.save(address);
        return address;
    }

    @Override
    public Address delete(String id) {
        Address address = getAddress(id);
        addressRepository.delete(address);
        return address;
    }
}
