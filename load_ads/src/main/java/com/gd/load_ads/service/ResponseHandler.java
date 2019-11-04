package com.gd.load_ads.service;

import com.gd.load_ads.dto.DetailedDescription;

import java.util.Map;

public interface ResponseHandler {
    DetailedDescription getDetailDescription(Map<String, String> map);
}
