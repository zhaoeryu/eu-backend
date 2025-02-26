package cn.eu.common.utils;


import cn.hutool.core.util.StrUtil;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 密码生成器
 */
public class PasswordGenerator {
    private static final String UPPER = "ABCDEFGHJKLMNPQRSTUVWXYZ";
    private static final String LOWER = "abcdefghijkmnopqrstuvwxyz";
    private static final String DIGITS = "23456789";
    private static final String SPECIAL = "!@#$%^&*_+-=:.";

    private final SecureRandom random = new SecureRandom();
    private final int length;
    private final boolean useUpper;
    private final boolean useLower;
    private final boolean useDigits;
    private final boolean useSpecial;
    private final boolean excludeAmbiguous;

    public static class Builder {
        private int length = 12;
        private boolean useUpper = true;
        private boolean useLower = true;
        private boolean useDigits = true;
        private boolean useSpecial = true;
        private boolean excludeAmbiguous = true;

        public Builder length(int length) {
            this.length = Math.max(length, 4);
            return this;
        }

        public Builder useUpper(boolean useUpper) {
            this.useUpper = useUpper;
            return this;
        }

        public Builder useLower(boolean useLower) {
            this.useLower = useLower;
            return this;
        }

        public Builder useDigits(boolean useDigits) {
            this.useDigits = useDigits;
            return this;
        }

        public Builder useSpecial(boolean useSpecial) {
            this.useSpecial = useSpecial;
            return this;
        }

        public Builder excludeAmbiguous(boolean excludeAmbiguous) {
            this.excludeAmbiguous = excludeAmbiguous;
            return this;
        }

        public PasswordGenerator build() {
            return new PasswordGenerator(this);
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    private PasswordGenerator(Builder builder) {
        this.length = builder.length;
        this.useUpper = builder.useUpper;
        this.useLower = builder.useLower;
        this.useDigits = builder.useDigits;
        this.useSpecial = builder.useSpecial;
        this.excludeAmbiguous = builder.excludeAmbiguous;
    }

    public String generate() {
        validateConfiguration();
        
        List<Character> password = new ArrayList<>(length);
        String charPool = buildCharacterPool();
        
        // 确保至少包含每个启用的字符类别
        if (useUpper) password.add(pickChar(getUpperChars()));
        if (useLower) password.add(pickChar(getLowerChars()));
        if (useDigits) password.add(pickChar(getDigitsChars()));
        if (useSpecial) password.add(pickChar(getSpecialChars()));

        // 填充剩余字符
        for (int i = password.size(); i < length; i++) {
            password.add(pickChar(charPool));
        }

        // 打乱顺序
        Collections.shuffle(password, random);

        return toString(password);
    }

    private void validateConfiguration() {
        int minLength = (useUpper ? 1 : 0) + (useLower ? 1 : 0) 
                      + (useDigits ? 1 : 0) + (useSpecial ? 1 : 0);
        
        if (length < minLength) {
            throw new IllegalArgumentException(
                "Password length must be at least " + minLength
            );
        }
    }

    private String buildCharacterPool() {
        StringBuilder pool = new StringBuilder();
        if (useUpper) pool.append(getUpperChars());
        if (useLower) pool.append(getLowerChars());
        if (useDigits) pool.append(getDigitsChars());
        if (useSpecial) pool.append(getSpecialChars());
        return pool.toString();
    }

    private String getUpperChars() {
        return excludeAmbiguous ? UPPER.replaceAll("[IO]", "") : UPPER;
    }

    private String getLowerChars() {
        return excludeAmbiguous ? LOWER.replaceAll("[l]", "") : LOWER;
    }

    private String getDigitsChars() {
        return excludeAmbiguous ? DIGITS.replaceAll("[01]", "") : DIGITS;
    }

    private String getSpecialChars() {
        return SPECIAL;
    }

    private char pickChar(String charSet) {
        return charSet.charAt(random.nextInt(charSet.length()));
    }

    private String toString(List<Character> chars) {
        StringBuilder sb = new StringBuilder(chars.size());
        for (Character c : chars) sb.append(c);
        return sb.toString();
    }

    /**
     * 构建系统初始密码
     * <p>
     * 规则：用户名 + # + 手机号后4位 + # + MMdd
     * @return 初始密码
     */
    public static String buildInitialPassword(String username, String mobile) {
        String date = LocalDate.now().format(DateTimeFormatter.ofPattern("MMdd"));
        String last4Digits = "";
        if (StrUtil.isNotBlank(mobile) && mobile.length() >= 4) {
            last4Digits = mobile.substring(mobile.length() - 4);
        }
        return username + "#" + last4Digits + "#" + date;
    }

    // 使用示例
    public static void main(String[] args) {
        PasswordGenerator generator = new PasswordGenerator.Builder()
            .length(10)
            .useSpecial(false)
            .build();

        for (int i = 0; i < 10; i++) {
            System.out.println("Generated Password: " + generator.generate());
        }

        System.out.println("Initial Password: " + buildInitialPassword("admin", "13800138000"));
        System.out.println("Initial Password: " + buildInitialPassword("admin", "123"));
        System.out.println("Initial Password: " + buildInitialPassword("admin", null));
    }
}