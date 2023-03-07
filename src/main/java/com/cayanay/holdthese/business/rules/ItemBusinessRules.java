package com.cayanay.holdthese.business.rules;

import com.cayanay.holdthese.core.utilities.exceptions.BusinessException;
import com.cayanay.holdthese.dataaccess.ItemRepository;
import com.cayanay.holdthese.entities.AccessCode;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ItemBusinessRules {
    private ItemRepository itemRepository;

    public void checkIfItemsExits(AccessCode accessCode) {
        if (itemRepository.findItemsByAccessCode(accessCode).isEmpty()) {
            throw new BusinessException("There is no item connected to this access code!");
        }
    }
}
