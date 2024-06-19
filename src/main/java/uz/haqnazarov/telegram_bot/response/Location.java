package uz.haqnazarov.telegram_bot.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
public class Location {
    private String name;
    private String region;
    private String country;
    private Double lat;
    private Double lon;
    @JsonProperty(value = "tz_id")
    private String tzId;
    @JsonProperty(value = "localtime_epoch")
    private Date localTimeEpoch;
    @JsonProperty(value = "localtime")
    private String localTime;
}
