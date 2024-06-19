package uz.haqnazarov.telegram_bot.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
public class Current {
    @JsonProperty(value = "last_updated_epoch")
    private Date lastUpdatedEpoch;
    @JsonProperty(value = "last_updated")
    private String lastUpdated;
    @JsonProperty(value = "temp_c")
    private Double tempC;
    @JsonProperty(value = "temp_f")
    private Double tempF;
    @JsonProperty(value = "is_day")
    private Integer isDay;
    private Condition condition;
    @JsonProperty(value = "wind_mph")
    private Double windMph;
    @JsonProperty(value = "wind_kph")
    private Double windKph;
    @JsonProperty(value = "wind_degree")
    private Double windDegree;
    @JsonProperty(value = "wind_dir")
    private String windDir;
    @JsonProperty(value = "pressure_mb")
    private Double pressureMb;
    @JsonProperty(value = "pressure_in")
    private Double pressureIn;
    @JsonProperty(value = "precip_mm")
    private Double precipMm;
    @JsonProperty(value = "precip_in")
    private Double precipIn;
    private Integer humidity;
    private Integer cloud;
    @JsonProperty(value = "feelslike_c")
    private Double feelslikeC;
    @JsonProperty(value = "feelslike_f")
    private Double feelslikeF;
    @JsonProperty(value = "windchill_c")
    private Double windchillC;
    @JsonProperty(value = "windchill_f")
    private Double windchillF;
    @JsonProperty(value = "heatindex_c")
    private Double heatindexC;
    @JsonProperty(value = "heatindex_f")
    private Double heatindexF;
    @JsonProperty(value = "dewpoint_c")
    private Double dewpointC;
    @JsonProperty(value = "dewpoint_f")
    private Double dewpointF;
    @JsonProperty(value = "vis_km")
    private Double visKm;
    @JsonProperty(value = "vis_miles")
    private Double visMiles;
    private Double uv;
    @JsonProperty(value = "gust_mph")
    private Double gustMph;
    @JsonProperty(value = "gust_kph")
    private Double gustKph;
}
