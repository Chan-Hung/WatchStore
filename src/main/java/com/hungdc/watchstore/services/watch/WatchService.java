package com.hungdc.watchstore.services.watch;

import com.hungdc.watchstore.dtos.watch.WatchDto;
import com.hungdc.watchstore.entities.Watch;
import org.springframework.data.domain.Page;

public interface WatchService {
    Watch getWatch(String id);
    Page<Watch> getListWatch(int page, int size);

    Watch create (WatchDto dto);

    Watch update (String id, WatchDto dto);

    Watch delete (String id);
    Page<Watch> searchWatch(String search, int pageNumber, int pageSize);
}
