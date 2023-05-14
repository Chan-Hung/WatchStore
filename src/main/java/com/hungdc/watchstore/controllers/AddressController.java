package com.hungdc.watchstore.controllers;

import com.hungdc.watchstore.dtos.addressdto.AddressDto;
import com.hungdc.watchstore.entities.Address;
import com.hungdc.watchstore.services.address.AddressService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/address")
public class AddressController {
    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Address> getAddress(@PathVariable String id) {
        return new ResponseEntity<>(addressService.getAddress(id), HttpStatus.OK);
    }
    @PreAuthorize("hasRole('USER')")
    @PostMapping
    public ResponseEntity<Address> create(@Valid @RequestBody AddressDto dto) {
        return new ResponseEntity<>(addressService.create(dto), HttpStatus.OK);
    }
    @PreAuthorize("hasRole('USER')")
    @PutMapping("/{id}")
    public ResponseEntity<Address> update(@PathVariable String id, @Valid @RequestBody AddressDto dto) {
        return new ResponseEntity<>(addressService.update(id, dto), HttpStatus.OK);
    }
    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Address> delete(@PathVariable String id) {
        return new ResponseEntity<>(addressService.delete(id), HttpStatus.OK);
    }
}
