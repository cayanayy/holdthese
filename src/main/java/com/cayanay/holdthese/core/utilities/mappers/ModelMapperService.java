package com.cayanay.holdthese.core.utilities.mappers;

import org.modelmapper.ModelMapper;

public interface ModelMapperService {
    ModelMapper forRequest();

    ModelMapper forResponse();

    ModelMapper forItemResponse();
}
