package org.aurifolia.ginkgo.user.api.util;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;

import java.util.Locale;

/**
 * 验证相关的工具
 *
 * @author danpeng
 * @since 1.0
 */
public class ValidationUtil {
    private static volatile PhoneNumberUtil PHONE_NUMBER_UTIL;

    /**
     * 校验手机号码
     *
     * @param phoneNumber 国际化的手机号码格式 例如: +86 135 6789 0987
     * @return 是否有效
     */
    public static boolean validateMobileNumber(String phoneNumber) {
        if (PHONE_NUMBER_UTIL == null) {
            synchronized (ValidationUtil.class) {
                if (PHONE_NUMBER_UTIL == null) {
                    PHONE_NUMBER_UTIL = PhoneNumberUtil.getInstance();
                }
            }
        }
        int countryCallingCodeEnd = phoneNumber.indexOf(" ");
        String countryCallingCode = countryCallingCodeEnd == -1 ? Locale.getDefault().getCountry()
                : PHONE_NUMBER_UTIL.getRegionCodeForCountryCode(Integer
                .parseInt(phoneNumber.substring(0, countryCallingCodeEnd)));
        try {
            return PHONE_NUMBER_UTIL.isValidNumber(PHONE_NUMBER_UTIL.parse(phoneNumber, countryCallingCode));
        } catch (NumberParseException e) {
            throw new RuntimeException(e);
        }
    }
}
