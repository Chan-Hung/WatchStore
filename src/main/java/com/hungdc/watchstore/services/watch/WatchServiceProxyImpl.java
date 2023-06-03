package com.hungdc.watchstore.services.watch;

import com.hungdc.watchstore.dtos.watch.WatchDto;
import com.hungdc.watchstore.entities.Watch;
import com.hungdc.watchstore.exceptions.UnauthorizedAccessException;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class WatchServiceProxyImpl implements WatchService{

    private final WatchService watchServiceImpl;

    public WatchServiceProxyImpl(WatchService watchServiceImpl) {
        this.watchServiceImpl = watchServiceImpl;
    }

    public boolean hasUserRole() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            return userDetails.getAuthorities().stream()
                    .anyMatch(authority -> authority.getAuthority().equals("ROLE_USER"));
        }
        return false;
    }
    @Override
    @Cacheable("watchCache")
    public Watch getWatch(String id) {
        System.out.println("Proxy class use getWatch method");
        return watchServiceImpl.getWatch(id);
    }

    @Override
    @Cacheable("watchListCache")
    public Page<Watch> getListWatch(int page, int size) {
        System.out.println("Proxy class use getListWatch method");
        return watchServiceImpl.getListWatch(page, size);
    }

    @Override
    public Watch create(WatchDto dto) {
        if (hasUserRole()){
            throw new UnauthorizedAccessException("Không có quyền truy cập");
        }
        //logging
        System.out.println("Proxy class use create method");
        System.out.println("Creating watch with DTO: " + dto);
        return watchServiceImpl.create(dto);
    }

    @Override
    public Watch update(String id, WatchDto dto) {
        if (hasUserRole()){
            throw new UnauthorizedAccessException("Không có quyền truy cập");
        }
        //logging
        System.out.println("Proxy class use update method");
        System.out.println("Updating watch with DTO: " + dto);
        return watchServiceImpl.update(id, dto);
    }

    @Override
    public Watch delete(String id) {
        if (hasUserRole()){
            throw new UnauthorizedAccessException("Không có quyền truy cập");
        }
        System.out.println("Proxy class use delete method");
        System.out.println("Deleting watch with id: " + id);
        return watchServiceImpl.delete(id);
    }

    @Override
    public Page<Watch> searchWatch(String search, int pageNumber, int pageSize) {
        System.out.println("Proxy class use search method");
        return watchServiceImpl.searchWatch(search, pageNumber, pageSize);
    }
}
