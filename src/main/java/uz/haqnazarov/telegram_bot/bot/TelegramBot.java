package uz.haqnazarov.telegram_bot.bot;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import uz.haqnazarov.telegram_bot.config.TelegramBotProperties;
import uz.haqnazarov.telegram_bot.handlers.MessageHandler;

@Slf4j
@NoArgsConstructor(force = true)
@Component
public class TelegramBot extends TelegramLongPollingBot {
    private final MessageHandler messageHandler;
    public final TelegramBotProperties botProperties;

    public TelegramBot(MessageHandler messageHandler, TelegramBotProperties botProperties, DefaultBotOptions defaultBotOptions) {
        super(defaultBotOptions, botProperties.getToken());
        this.messageHandler = messageHandler;
        this.botProperties = botProperties;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            messageHandler.handle(update.getMessage(), this);
        }
    }

    @Override
    public String getBotUsername() {
        return botProperties.getUsername();
    }
}
