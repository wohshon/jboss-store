package com.demo.store.entity.status;

import javax.persistence.AttributeConverter;
import java.util.stream.Stream;

public class InvoiceStatusConvertor implements AttributeConverter<InvoiceStatus, String> {
    
@Override
public String convertToDatabaseColumn(InvoiceStatus status) {
    if (status == null ) {
        return null;
    }
    return status.label;

}

@Override
public InvoiceStatus convertToEntityAttribute(String dbData) {
    if (dbData == null) {
        return null;
    }
        return Stream.of(InvoiceStatus.values())
        .filter(s -> s.label.equals(dbData))
        .findFirst()
        .orElseThrow(IllegalArgumentException::new);
    }

}
