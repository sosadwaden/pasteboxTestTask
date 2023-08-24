package com.sosadwaden.pasteboxtesttask.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Отдаётся в виде JSON как ошибка
 */
@Getter
@Setter
@AllArgsConstructor
public class PastebinError {
    private String message;
    private int statusCode;
}
