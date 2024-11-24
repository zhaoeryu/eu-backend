package cn.eu.common.utils;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
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
                    .map(msg -> {
                        // 如果msg以{}包裹，则从国际化文件中获取
                        if (msg.startsWith("{") && msg.endsWith("}")) {
                            return MessageUtils.message(msg.substring(1, msg.length() - 1));
                        }
                        return msg;
                    })
                    .collect(Collectors.toList());
            throw new RuntimeException(String.join(",", errorMessage));
        }
    }
}
