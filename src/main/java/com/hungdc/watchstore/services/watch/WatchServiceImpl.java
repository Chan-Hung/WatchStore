package com.hungdc.watchstore.services.watch;

import com.hungdc.watchstore.dtos.watch.WatchDto;
import com.hungdc.watchstore.entities.Watch;
import com.hungdc.watchstore.exceptions.InvalidException;
import com.hungdc.watchstore.exceptions.NotFoundException;
import com.hungdc.watchstore.repositories.WatchRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Slf4j
@Service
public class WatchServiceImpl implements WatchService {
    private final WatchRepository watchRepository;

    public WatchServiceImpl(WatchRepository watchRepository) {
        this.watchRepository = watchRepository;
    }

    @Override
    public Watch getWatch(String id) {
        return watchRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String
                        .format("Đồng hồ có id %s không tồn tại", id)));
    }

    @Override
    public Watch create(WatchDto dto) {
        if (ObjectUtils.isEmpty(dto.getCode())) {
            throw new InvalidException("Mã đồng hồ không được bỏ trống");
        }
        if (ObjectUtils.isEmpty(dto.getName())) {
            throw new InvalidException("Tên đồng hồ không được bỏ trống");
        }
        if (ObjectUtils.isEmpty(dto.getMachine())) {
            throw new InvalidException("Thông tin về máy cơ không được bỏ trống");
        }
        if (ObjectUtils.isEmpty(dto.getOrigin())) {
            throw new InvalidException("Thông tin về xuất xứ không được bỏ trống");
        }
        if (ObjectUtils.isEmpty(dto.getWaterResistant())) {
            throw new InvalidException("Thông tin về kháng nước không được bỏ trống");
        }
        if (ObjectUtils.isEmpty(dto.getProductImages())) {
            throw new InvalidException("Hình ảnh đồng hồ không được bỏ trống");
        }
        if (ObjectUtils.isEmpty(dto.getPrice())) {
            throw new InvalidException("Giá đồng hồ không được bỏ trống");
        }
        if (ObjectUtils.isEmpty(dto.getCategory())) {
            throw new InvalidException("Loại đồng hồ không được bỏ trống");
        }
        if (watchRepository.validateWatchCode(dto.getCode().trim().toUpperCase())) {
            throw new InvalidException(String.format("Đồng hồ có mã %s đã tồn tại",
                    dto.getCode()));
        }
        Watch watch = new Watch();

        watch.setCode(dto.getCode().trim().toUpperCase());
        watch.setName(dto.getName().trim());
        watch.setMachine(dto.getMachine().trim());
        watch.setOrigin(dto.getOrigin());
        watch.setWaterResistant(dto.getWaterResistant());
        watch.setProductImages(dto.getProductImages());
        watch.setPrice(dto.getPrice());
        watch.setCategory(dto.getCategory());
        watch.setQuantity(dto.getQuantity());
        this.watchRepository.save(watch);
        return watch;
    }

    @Override
    public Watch update(String id, WatchDto dto) {
        Watch watch = getWatch(id);
        if (ObjectUtils.isEmpty(dto.getCode())) {
            throw new InvalidException("Mã đồng hồ không được bỏ trống");
        }
        if (ObjectUtils.isEmpty(dto.getName())) {
            throw new InvalidException("Tên đồng hồ không được bỏ trống");
        }
        if (ObjectUtils.isEmpty(dto.getMachine())) {
            throw new InvalidException("Thông tin về loại máy không được bỏ trống");
        }
        if (ObjectUtils.isEmpty(dto.getOrigin())) {
            throw new InvalidException("Thông tin về xuất xứ không được bỏ trống");
        }
        if (ObjectUtils.isEmpty(dto.getWaterResistant())) {
            throw new InvalidException("Thông tin về kháng nước không được bỏ trống");
        }
        if (ObjectUtils.isEmpty(dto.getProductImages())) {
            throw new InvalidException("Hình ảnh đồng hồ không được bỏ trống");
        }
        if (ObjectUtils.isEmpty(dto.getPrice())) {
            throw new InvalidException("Giá đồng hồ không được bỏ trống");
        }
        if (ObjectUtils.isEmpty(dto.getCategory())) {
            throw new InvalidException("Loại đồng hồ không được bỏ trống");
        }
        if (!watch.getCode().equalsIgnoreCase(dto.getCode().trim())
                && this.watchRepository.validateWatchCode(dto.getCode().trim().toUpperCase())) {
            throw new InvalidException(String.format("Đồng hồ có mã %s đã tồn tại",
                    dto.getCode()));
        }
        watch.setCode(dto.getCode().trim().toUpperCase());
        watch.setName(dto.getName().trim());
        watch.setMachine(dto.getMachine().trim());
        watch.setOrigin(dto.getOrigin());
        watch.setWaterResistant(dto.getWaterResistant());
        watch.setProductImages(dto.getProductImages());
        watch.setPrice(dto.getPrice());
        watch.setCategory(dto.getCategory());
        watch.setQuantity(dto.getQuantity());
        watchRepository.save(watch);
        return watch;
    }

    @Override
    public Watch delete(String id) {
        Watch watch = getWatch(id);
        this.watchRepository.delete(watch);
        return watch;
    }
    @Override
    public Page<Watch> getListWatch(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return watchRepository.findAll(pageable);
    }

    @Override
    public Page<Watch> searchWatch(String search, int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return watchRepository.findByNameContaining(search,pageable);
    }
}
