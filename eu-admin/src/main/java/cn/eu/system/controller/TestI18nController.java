package cn.eu.system.controller;

import cn.eu.common.model.ResultBody;
import cn.eu.common.utils.MessageUtils;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Eu.z
 * @since 2024/11/18
 */
@RestController
@RequestMapping("/api/test/i18n")
public class TestI18nController {

    @RequestMapping
    public ResultBody test(@RequestParam("code") String code) {
        return ResultBody.ok()
                .putValue("locale", LocaleContextHolder.getLocale().getLanguage())
                .data(MessageUtils.message(code));
    }

}
