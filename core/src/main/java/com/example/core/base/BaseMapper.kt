package com.example.core.base

typealias BaseMapper<Input, Output> = (Input) -> Output

fun <Input, Output> BaseMapper<Input, Output>.map(input: List<Input>?) =
    input?.map {
        invoke(it)

    }
// some data
// test_branch
// some data
// git_branch
// bla bla
