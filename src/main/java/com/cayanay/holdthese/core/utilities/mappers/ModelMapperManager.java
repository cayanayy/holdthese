package com.cayanay.holdthese.core.utilities.mappers;

import com.cayanay.holdthese.business.responses.FileResponse;
import com.cayanay.holdthese.business.responses.ItemResponse;
import com.cayanay.holdthese.entities.Item;
import lombok.AllArgsConstructor;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ModelMapperManager implements ModelMapperService {
    private ModelMapper modelMapper;

    @Override
    public ModelMapper forRequest() {
        modelMapper.getConfiguration().setAmbiguityIgnored(true).setMatchingStrategy(MatchingStrategies.LOOSE);

        return modelMapper;
    }

    @Override
    public ModelMapper forResponse() {
        modelMapper.getConfiguration().setAmbiguityIgnored(true).setMatchingStrategy(MatchingStrategies.STANDARD);

        return modelMapper;
    }

    @Override
    public ModelMapper forItemResponse() {
        ModelMapper customMapper = new ModelMapper();

        Converter<Item, ItemResponse> itemResponseConverter = mappingContext -> {
            Item item = mappingContext.getSource();
            ItemResponse itemResponse = mappingContext.getDestination();
            itemResponse.setFiles(item.getFiles().stream().map(file -> forResponse().map(file, FileResponse.class)).toList());
            return itemResponse;
        };

        customMapper.addConverter(itemResponseConverter);
        return customMapper;
    }
}
