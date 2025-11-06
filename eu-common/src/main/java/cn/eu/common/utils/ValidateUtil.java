package cn.eu.common.utils;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author zhaoeryu
 * @since 2023/8/24
 */
public class ValidateUtil {
    public static <T> void valid(T t){
        Validator validatorFactory = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<T>> errors = validatorFactory.validate(t);
        if (!errors.isEmpty()){
            List<String> errorMessage = errors.stream().map(ConstraintViolation::getMessage)
                    .map(MessageUtils::parseMessage)
                    .collect(Collectors.toList());
            throw new RuntimeException(String.join(",", errorMessage));
        }
    }
}
