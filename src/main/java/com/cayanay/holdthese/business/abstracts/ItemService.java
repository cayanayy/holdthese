package com.cayanay.holdthese.business.abstracts;

import com.cayanay.holdthese.business.requests.CreateItemRequest;
import com.cayanay.holdthese.entities.AccessCode;
import com.cayanay.holdthese.entities.File;
import com.cayanay.holdthese.entities.Item;

import java.util.List;

public interface ItemService {
    void createItem(CreateItemRequest createItemRequest, List<File> files, AccessCode accessCode);

    List<Item> getItemsByCode(String code);
}
