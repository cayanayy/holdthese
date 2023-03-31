package com.cayanay.holdthese.business.concretes;

import com.cayanay.holdthese.business.abstracts.ItemService;
import com.cayanay.holdthese.business.requests.CreateItemRequest;
import com.cayanay.holdthese.business.rules.AccessCodeBusinessRules;
import com.cayanay.holdthese.business.rules.ItemBusinessRules;
import com.cayanay.holdthese.core.utilities.mappers.ModelMapperManager;
import com.cayanay.holdthese.dataaccess.ItemRepository;
import com.cayanay.holdthese.entities.AccessCode;
import com.cayanay.holdthese.entities.File;
import com.cayanay.holdthese.entities.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemManager implements ItemService {
    private final ItemRepository itemRepository;
    private final AccessCodeManager accessCodeManager;
    private final ModelMapperManager modelMapperManager;
    private final AccessCodeBusinessRules accessCodeBusinessRules;
    private final ItemBusinessRules itemBusinessRules;

    @Override
    public void createItem(CreateItemRequest createItemRequest, List<File> files, AccessCode accessCode) {
        Item item = modelMapperManager.forRequest().map(createItemRequest, Item.class);
        item.setFiles(files);
        item.setAccessCode(accessCode);
        item.setUnableAt(LocalDateTime.now().plusMinutes(item.getDuration()));
        itemRepository.save(item);
    }

    @Override
    public List<Item> getItemsByCode(String code) {
        AccessCode accessCode = accessCodeManager.getAccessCodeByCode(code);
        List<Item> items = itemRepository.findItemsByAccessCode(accessCode);
        items.removeIf(item -> item.getUnableAt().isBefore(LocalDateTime.now()));

        return items;
    }

    @Override
    public void deleteItem(Long itemId, String code) {
        accessCodeBusinessRules.checkIfAccessCodeDoesNotExists(code);
        itemBusinessRules.checkIfItemDoesNotExits(itemId);
        itemBusinessRules.checkOwnerOfItem(itemId, code);
        itemRepository.deleteById(itemId);
    }
}
