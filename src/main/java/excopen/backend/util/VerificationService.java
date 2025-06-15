package excopen.backend.util;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class VerificationService {
    // Фиксированный код для прототипа
    private static final String FIXED_CODE = "000000";
    // Лимит: 1 запрос в 3 минуты на номер
    private static final int RATE_LIMIT_MINUTES = 3;

    private final PhoneNumberValidator phoneNumberValidator;

    private final Cache<Long, String> userVerificationPhones =
            Caffeine.newBuilder()
                    .expireAfterWrite(15, TimeUnit.MINUTES)
                    .build();

    private final Cache<String, String> verificationCodes =
            Caffeine.newBuilder()
                    .expireAfterWrite(5, TimeUnit.MINUTES)
                    .build();

    private final Cache<String, Boolean> rateLimitCache =
            Caffeine.newBuilder()
                    .expireAfterWrite(RATE_LIMIT_MINUTES, TimeUnit.MINUTES)
                    .build();

    private final Cache<String, Boolean> verifiedPhones =
            Caffeine.newBuilder()
                    .expireAfterWrite(1, TimeUnit.HOURS)
                    .build();

    public void sendVerificationCode(Long userId, String phoneNumber) {
        String normalizedPhone = phoneNumberValidator.normalizePhoneNumber(phoneNumber);

        if (!phoneNumberValidator.isValidRussianPhoneNumber(normalizedPhone)) {
            throw new IllegalArgumentException("Некорректный номер телефона");
        }

        if (rateLimitCache.getIfPresent(normalizedPhone) != null) {
            throw new IllegalStateException("Повторный запрос возможен через " + RATE_LIMIT_MINUTES + " минут");
        }

        userVerificationPhones.put(userId, normalizedPhone);

        verificationCodes.put(normalizedPhone, FIXED_CODE);
        rateLimitCache.put(normalizedPhone, true);
    }

    public boolean verifyCode(Long userId, String phoneNumber, String code) {
        String normalizedPhone = phoneNumberValidator.normalizePhoneNumber(phoneNumber);

        // Проверяем привязку пользователя к номеру
        String userPhone = userVerificationPhones.getIfPresent(userId);
        if (userPhone == null || !userPhone.equals(normalizedPhone)) {
            return false;
        }

        // Проверяем код
        String storedCode = verificationCodes.getIfPresent(normalizedPhone);
        if (storedCode == null || !storedCode.equals(code)) {
            return false;
        }

        // Помечаем как подтвержденный
        verificationCodes.invalidate(normalizedPhone);
        verifiedPhones.put(normalizedPhone, true);
        return true;
    }

    public boolean isPhoneVerified(String phoneNumber) {
        String normalizedPhone = phoneNumberValidator.normalizePhoneNumber(phoneNumber);
        return verifiedPhones.getIfPresent(normalizedPhone) != null;
    }
}