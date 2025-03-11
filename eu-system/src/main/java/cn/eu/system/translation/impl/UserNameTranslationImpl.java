package cn.eu.system.translation.impl;

import cn.eu.common.annotation.TranslationType;
import cn.eu.common.constants.TransConstant;
import cn.eu.common.translation.TranslationInterface;
import cn.eu.system.cache.SysUserCache;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 用户名翻译实现
 */
@Slf4j
@Component
@AllArgsConstructor
@TranslationType(type = TransConstant.USER_ID_TO_NAME)
public class UserNameTranslationImpl implements TranslationInterface<String> {

    private final SysUserCache sysUserCache;

    @Override
    public String translation(Object key) {
        return sysUserCache.get((String) key);
    }
}
