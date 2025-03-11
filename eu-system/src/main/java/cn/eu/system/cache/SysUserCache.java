package cn.eu.system.cache;

import cn.eu.system.service.ISysUserService;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Eu.z
 * @since 2025/3/11
 */
@Slf4j
@Service
public class SysUserCache {

    /**
     * 用户缓存
     * key: 用户id
     * value: 用户昵称
     */
    public Map<String, String> cache = new ConcurrentHashMap<>();

    @Autowired
    private ISysUserService sysUserService;

    @PostConstruct
    public void init() {
        log.info("加载用户缓存到内存中");
        sysUserService.list()
                .forEach(sysUser -> cache.put(sysUser.getId(), sysUser.getNickname()));
    }

    public String get(String key) {
        if (StrUtil.isBlank(key)) {
            return key;
        }
        if (!cache.containsKey(key)) {
            Optional.ofNullable(sysUserService.getById(key))
                    .ifPresent(sysUser -> cache.put(sysUser.getId(), sysUser.getNickname()));
        }
        return cache.getOrDefault(key, key);
    }

    @PreDestroy
    public void clear() {
        log.info("清空内存中的用户缓存");
        cache.clear();
    }
}
