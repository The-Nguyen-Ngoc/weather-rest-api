package com.example.weatherrestapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Table(name = "locations")
@Data
public class Location {
    @Id
    @Column(length = 12, nullable = false, unique = true)
    @NotBlank
    private String code;
    @Column(length = 128, nullable = false)
    @JsonProperty("city_name")
    @NotBlank
    private String cityName;
    @Column(length = 128)
    @JsonProperty("region_name")
    @NotNull
    private String regionName;
    @Column(length = 64, nullable = false)
    @JsonProperty("country_name")
    @NotBlank
    private String countryName;
    @Column(length = 2, nullable = false)
    @JsonProperty("country_code")
    @NotBlank
    private String countryCode;

    private boolean enabled;
    @JsonIgnore
    private boolean trashed;
}

