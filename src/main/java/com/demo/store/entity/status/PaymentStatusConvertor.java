package com.demo.store.entity.status;

import javax.persistence.AttributeConverter;
import java.util.stream.Stream;

public class PaymentStatusConvertor implements AttributeConverter<PaymentStatus, String> {
    
@Override
public String convertToDatabaseColumn(PaymentStatus status) {
    if (status == null ) {
        return null;
    }
    return status.label;

}

@Override
public PaymentStatus convertToEntityAttribute(String dbData) {
    if (dbData == null) {
        return null;
    }
        return Stream.of(PaymentStatus.values())
        .filter(s -> s.label.equals(dbData))
        .findFirst()
        .orElseThrow(IllegalArgumentException::new);
    }

}
