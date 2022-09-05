package com.example.conventer;

import com.example.entity.AbstractEntity;

public interface Converter<T extends AbstractEntity, K> {

    T convert(K value);

    K convert(T value);
}
