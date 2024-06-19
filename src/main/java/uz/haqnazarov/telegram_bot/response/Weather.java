package uz.haqnazarov.telegram_bot.response;

import lombok.Data;

@Data
public class Weather {
    private Location location;
    private Current current;
}
