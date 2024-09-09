package com.slutedemo.slutedemoserver.model

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty

data class TransferResponse<T>(
    @JsonProperty("headerResp") val headerResponse: HeaderResponse,

    @JsonInclude(JsonInclude.Include.NON_NULL) val content: T
)

data class HeaderResponse(
    var reqID: String,
    var reqDtm: String,
    var txnRefID: String? = null,
    var service: String,
    var statusCd: String,
    var statusDesc: String
)

data class HeaderResponseWithContentBody<T>(
    var reqID: String,
    var reqDtm: String,
    var txnRefID: String? = null,
    var service: String,
    var statusCd: String,
    var statusDesc: String
)