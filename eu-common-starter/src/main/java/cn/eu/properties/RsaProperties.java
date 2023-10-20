package cn.eu.properties;

import lombok.Data;

/**
 * RSA 配置
 * @see <a href="http://web.chacuo.net/netrsakeypair">密钥对生成工具</a>
 * @author zhaoeryu
 * @since 2023/6/5
 */
@Data
public class RsaProperties {

    private String publicKey;
    private String privateKey;
}
