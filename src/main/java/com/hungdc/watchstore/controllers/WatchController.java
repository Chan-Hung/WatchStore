package com.hungdc.watchstore.controllers;

import com.hungdc.watchstore.dtos.order.OrderDto;
import com.hungdc.watchstore.dtos.watch.WatchDto;
import com.hungdc.watchstore.entities.Watch;
import com.hungdc.watchstore.services.watch.WatchService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/watch")
public class WatchController {
    private final WatchService watchService;

    public WatchController(WatchService watchService) {
        this.watchService = watchService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Watch> getWatch(@PathVariable String id) {
        return new ResponseEntity<>(watchService.getWatch(id), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<Watch> create(@Valid @RequestBody WatchDto dto) {
        return new ResponseEntity<>(watchService.create(dto), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Watch> update(@PathVariable String id, @Valid @RequestBody WatchDto dto) {
        return new ResponseEntity<>(watchService.update(id, dto), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Watch> delete(@PathVariable String id) {
        return new ResponseEntity<>(watchService.delete(id), HttpStatus.OK);
    }
    @PreAuthorize(value = "hasRole('USER')")
    @GetMapping
    public ResponseEntity<Page<Watch>> getListPagination(@RequestParam int page,
                                                         @RequestParam int size,
                                                         @RequestParam String sort,
                                                         @RequestParam String column){
        return new ResponseEntity<>(watchService.getListWatch(page, size, sort, column),HttpStatus.OK);
    }
    @PreAuthorize(value = "hasRole('USER')")
    @GetMapping("/search")
    public ResponseEntity<Page<Watch>> search(@RequestParam String search,
                                             @RequestParam int page,
                                             @RequestParam int size){
        return new ResponseEntity<>(watchService.searchWatch(search,page,size),HttpStatus.OK);
    }
}
