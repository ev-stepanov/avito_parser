package com.gd.load_ads.service;

import com.gd.model.dto.Advertising;

import java.util.Map;

public interface ResponseHandler {
    Advertising getDetailDescription(Map<String, String> map);
}
