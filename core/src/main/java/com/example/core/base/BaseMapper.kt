package com.example.core.base

typealias BaseMapper<Input, Output> = (Input) -> Output

fun <Input, Output> BaseMapper<Input, Output>.map(input: List<Input>?) =
    input?.map {
        invoke(it)
    }