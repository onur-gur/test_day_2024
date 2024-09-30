package com.orchestration.store.model.converter;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.orchestration.store.model.PointDao;
import jakarta.persistence.AttributeConverter;

import java.lang.reflect.Type;
import java.util.List;

public class PointDaoListConverter implements AttributeConverter<List<PointDao>, String> {
    private final Gson gson = new Gson();

    @Override
    public String convertToDatabaseColumn(List<PointDao> pointDaoList) {
        if (pointDaoList == null) {
            return null;
        }
        return gson.toJson(pointDaoList);
    }

    @Override
    public List<PointDao> convertToEntityAttribute(String json) {
        if (json == null || json.isEmpty()) {
            return null;
        }
        Type listType = new TypeToken<List<PointDao>>() {
        }.getType();
        return gson.fromJson(json, listType);
    }
}
