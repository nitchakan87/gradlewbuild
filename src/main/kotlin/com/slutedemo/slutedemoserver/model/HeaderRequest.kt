package com.slutedemo.slutedemoserver.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class HeaderRequest(
    val reqID: String? = "",
    val reqChannel: String? = "",
    val reqDtm: String? = "",
    val reqBy: String? = "",
    val service: String? = "",
    val sofType: String? = "",
    val cardNo: String? = "",
    val ip: String? = "",
    val txnRefID: String? = "",
    val sessionID: String? = ""
)