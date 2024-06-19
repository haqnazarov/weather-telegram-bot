package uz.haqnazarov.telegram_bot.handlers.impl;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.bots.AbsSender;
import uz.haqnazarov.telegram_bot.config.TelegramBotProperties;
import uz.haqnazarov.telegram_bot.entity.User;
import uz.haqnazarov.telegram_bot.entity.constants.State;
import uz.haqnazarov.telegram_bot.handlers.MessageHandler;
import uz.haqnazarov.telegram_bot.repository.UserRepository;
import uz.haqnazarov.telegram_bot.response.Weather;

import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class MessageHandlerImpl implements MessageHandler {
    private final UserRepository userRepository;
    private final TelegramBotProperties botProperties;
    private final RestTemplate restTemplate = new RestTemplate();
    private User user;

    @SneakyThrows
    @Override
    public void handle(Message message, AbsSender sender) {
        Long chatId = message.getChatId();
        user = findUserByChatId(chatId);
        var tgUser = message.getFrom();
        if (message.hasText()) {
            String text = message.getText();
            if (text.equalsIgnoreCase("/start")) {
                sender.execute(SendMessage.builder().chatId(chatId).text("<b>Hi <a href='tg://user?id=" + chatId + "'>" + tgUser.getFirstName() + "</a> \uD83D\uDC4B\uD83C\uDFFB</b>\n\n" +
                        "<b><i>Which city do you want to know the weather for?</i></b>\n\n" +
                        "Sample: <code>London</code>").parseMode(ParseMode.HTML).build());
                user.setState(State.ENTER_LOCATION);
                userRepository.save(user);
            } else if (user.getState().equals(State.ENTER_LOCATION)) {
                try {
                    Weather weather = restTemplate.getForObject("http://api.weatherapi.com/v1/current.json?key=" + botProperties.getKey() + "&q=" + text + "&aqi=no", Weather.class);
                    assert weather != null;
                    String sendText = "<b>\uD83D\uDCCD Region: </b> " + weather.getLocation().getRegion() +
                            "\n<b>\uD83C\uDF0F Country</b> " + weather.getLocation().getCountry() +
                            "\n<b>\uD83D\uDD52 Local Time: </b>" + weather.getLocation().getLocalTime() +
                            "\n\n<b>\uD83C\uDF1F Condition: </b>" + weather.getCurrent().getCondition().getText() +
                            "\n<b>\uD83C\uDF21 Temperature C: </b>" + weather.getCurrent().getTempC() +
                            "\n<b>\uD83C\uDF21 Temperature F: </b>" + weather.getCurrent().getTempF() +
                            "\n<b>\uD83D\uDCA8 Wind km/h: </b>" + weather.getCurrent().getWindKph() +
                            "\n<b>\uD83D\uDCA7 Humidity: </b>" + weather.getCurrent().getHumidity() +
                            "\n<b>☁️ Cloud: </b>" + weather.getCurrent().getCloud();
                    sender.execute(SendMessage.builder().chatId(chatId).text(sendText).parseMode(ParseMode.HTML).replyToMessageId(message.getMessageId()).build());
                } catch (Exception e) {
                    sender.execute(SendMessage.builder().chatId(chatId).text("<b>The city below was not found \uD83D\uDE14</b>").parseMode(ParseMode.HTML).replyToMessageId(message.getMessageId()).build());
                }
            }

        }
    }

    private User findUserByChatId(Long chatId) {
        User user = userRepository.findByChatIdAndDeletedFalse(chatId).orElse(null);
        return Objects.requireNonNullElseGet(user, () -> userRepository.save(new User(chatId, State.START)));
    }
}
