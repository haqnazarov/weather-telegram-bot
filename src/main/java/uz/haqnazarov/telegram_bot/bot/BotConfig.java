package uz.haqnazarov.telegram_bot.bot;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import uz.haqnazarov.telegram_bot.config.TelegramBotProperties;
import uz.haqnazarov.telegram_bot.handlers.MessageHandler;

@Component
@RequiredArgsConstructor
public class BotConfig {
    private final TelegramBotProperties botProperties;
    public final MessageHandler messageHandler;

    @Bean
    public DefaultBotOptions defaultBotOptions() {
        DefaultBotOptions options = new DefaultBotOptions();
        options.setMaxThreads(10);
        return options;
    }

    @SneakyThrows
    @Bean
    public TelegramBotsApi telegramBotsApi() {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        telegramBotsApi.registerBot(new TelegramBot(messageHandler,
                botProperties,
                new DefaultBotOptions()
        ));
        return telegramBotsApi;
    }
}
