package com.hungdc.watchstore.controllers;

import com.hungdc.watchstore.dtos.watch.WatchDto;
import com.hungdc.watchstore.entities.Watch;
import com.hungdc.watchstore.exceptions.UnauthorizedAccessException;
import com.hungdc.watchstore.securities.JwtTokenUtils;
import com.hungdc.watchstore.services.watch.WatchService;
import com.hungdc.watchstore.utils.EnumRole;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/watch")
public class WatchController {
    private final WatchService watchService;
    private final JwtTokenUtils jwtTokenUtils;
    public WatchController(@Qualifier("watchServiceProxyImpl") WatchService watchService, JwtTokenUtils jwtTokenUtils) {
        this.watchService = watchService;
        this.jwtTokenUtils = jwtTokenUtils;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Watch> getWatch(@PathVariable String id) {
        return new ResponseEntity<>(watchService.getWatch(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Watch> create(@Valid @RequestBody WatchDto dto) {
            return new ResponseEntity<>(watchService.create(dto), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Watch> update(@PathVariable String id, @Valid @RequestBody WatchDto dto) {
        return new ResponseEntity<>(watchService.update(id, dto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Watch> delete(@PathVariable String id) {
        return new ResponseEntity<>(watchService.delete(id), HttpStatus.OK);
    }
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping
    public ResponseEntity<Page<Watch>> getListPagination(@RequestParam int page,
                                                         @RequestParam int size){
        return new ResponseEntity<>(watchService.getListWatch(page, size),HttpStatus.OK);
    }
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/search")
    public ResponseEntity<Page<Watch>> search(@RequestParam String text,
                                             @RequestParam int page,
                                             @RequestParam int size){
        return new ResponseEntity<>(watchService.searchWatch(text,page,size),HttpStatus.OK);
    }
}
