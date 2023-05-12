package com.hungdc.watchstore.services.watch;

import com.hungdc.watchstore.dtos.order.OrderDto;
import com.hungdc.watchstore.dtos.watch.WatchDto;
import com.hungdc.watchstore.entities.Watch;

public interface WatchService {
    Watch getWatch(String id);

    Watch create (WatchDto dto);

    Watch update (String id, WatchDto dto);

    Watch delete (String id);
}
