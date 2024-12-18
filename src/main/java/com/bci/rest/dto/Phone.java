package com.bci.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

@Schema(description = "Pone")
public class Phone {

    @Schema(description = "number")
    @NotNull(message = "Phone number cannot be null")
    private Integer number;

    @Schema(description = "city code")
    @NotNull(message = "Phone city code cannot be null")
    private Integer cityCode;

    @Schema(description = "country code")
    @NotNull(message = "Phone country code cannot be null")
    private Integer countryCode;

    public Phone(Integer number, Integer cityCode, Integer countryCode) {
        this.number = number;
        this.cityCode = cityCode;
        this.countryCode = countryCode;
    }

    public Phone() {
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getCityCode() {
        return cityCode;
    }

    public void setCityCode(Integer cityCode) {
        this.cityCode = cityCode;
    }

    public Integer getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(Integer countryCode) {
        this.countryCode = countryCode;
    }
}
