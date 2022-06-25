package com.example.core.utils

typealias Mapper<T, R> = (T) -> R

fun <T, R> Mapper<T, R>.fromToList(input: List<T>) =
    input.map {
        invoke(it)

    }

// data
