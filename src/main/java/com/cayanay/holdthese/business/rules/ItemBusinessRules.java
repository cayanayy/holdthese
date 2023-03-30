package com.cayanay.holdthese.business.rules;

import com.cayanay.holdthese.core.utilities.exceptions.BusinessException;
import com.cayanay.holdthese.core.utilities.exceptions.NotFoundException;
import com.cayanay.holdthese.dataaccess.ItemRepository;
import com.cayanay.holdthese.entities.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ItemBusinessRules {
    private final ItemRepository itemRepository;

    public void checkIfItemDoesNotExits(Long itemId) {
        if (!itemRepository.existsById(itemId)) {
            throw new NotFoundException("Item does not exits!");
        }
    }

    public void checkOwnerOfItem(Long itemId, String code) {
        Item item = itemRepository.findById(itemId).orElseThrow(() -> new NotFoundException("Item does not exits!"));
        if (!item.getAccessCode().getCode().equals(code)) {
            throw new BusinessException("This item does not connected to this access code");
        }
    }
}
