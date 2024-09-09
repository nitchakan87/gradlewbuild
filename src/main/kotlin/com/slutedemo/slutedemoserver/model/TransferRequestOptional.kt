package com.slutedemo.slutedemoserver.model

import com.fasterxml.jackson.annotation.JsonProperty

/*
* This should be use in some of our endpoints that we think can have optional 'content'
* */
data class TransferRequestOptional<T>(
    @JsonProperty("headerReq") val headerRequest: HeaderRequest,

    val content: T? = null
)


