package com.cayanay.holdthese.core.utilities.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Map;

@EqualsAndHashCode(callSuper = true)
@Data
public class ValidationProblemDetails extends ProblemDetails {
    private Map<String, String> validationErrors;
}
