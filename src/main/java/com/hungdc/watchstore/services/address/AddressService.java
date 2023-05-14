package com.hungdc.watchstore.services.address;

import com.hungdc.watchstore.dtos.addressdto.AddressDto;
import com.hungdc.watchstore.entities.Address;

public interface AddressService {
    Address getAddress(String id);
    Address create (AddressDto dto);
    Address update (String id, AddressDto dto);
    Address delete (String id);
}
