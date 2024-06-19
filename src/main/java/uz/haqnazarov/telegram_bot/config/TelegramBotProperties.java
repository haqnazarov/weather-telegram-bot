package uz.haqnazarov.telegram_bot.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@ConfigurationProperties(prefix = "telegram-bot", ignoreUnknownFields = false)
@Configuration
public class TelegramBotProperties {
    String token;
    String username;
    String key;
}
