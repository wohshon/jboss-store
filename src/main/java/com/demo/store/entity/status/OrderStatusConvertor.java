package com.demo.store.entity.status;

import javax.persistence.AttributeConverter;
import java.util.stream.Stream;

public class OrderStatusConvertor implements AttributeConverter<OrderStatus, String> {
    
@Override
public String convertToDatabaseColumn(OrderStatus status) {
    if (status == null ) {
        return null;
    }
    return status.label;

}

@Override
public OrderStatus convertToEntityAttribute(String dbData) {
    if (dbData == null) {
        return null;
    }
        return Stream.of(OrderStatus.values())
        .filter(s -> s.label.equals(dbData))
        .findFirst()
        .orElseThrow(IllegalArgumentException::new);
    }

}
