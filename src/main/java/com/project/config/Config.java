package com.project.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class Config {
    @Value("${google.api.key}")
    private  String XRapidAPIKey;
    @Value("${google.api.host}")
    private  String XRapidAPIHost;
}
