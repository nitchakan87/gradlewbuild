import com.slutedemo.slutedemoserver.model.HeaderRequest
import com.slutedemo.slutedemoserver.model.HeaderResponse
import com.slutedemo.slutedemoserver.model.ResponseStatus
import com.slutedemo.slutedemoserver.model.TransferResponse
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.bodyValueAndAwait
import org.springframework.web.reactive.function.server.json

suspend inline fun <T> transferResponseSuccess(headerRequest: HeaderRequest, response: T) =
    ServerResponse
        .ok()
        .json()
        .bodyValueAndAwait(
            TransferResponse(
                buildHeaderResponse(
                    headerRequest
                ),
                response
            )
        )

fun buildHeaderResponse(
    headerRequest: HeaderRequest,
    responseStatus: ResponseStatus = ResponseStatus.SUCCESS
) = HeaderResponse(
    reqDtm = headerRequest.reqDtm.orEmpty(),
    reqID = headerRequest.reqID.orEmpty(),
    service = headerRequest.service.orEmpty(),
    statusCd = responseStatus.code,
    statusDesc = responseStatus.desc,
    txnRefID = headerRequest.txnRefID.orEmpty()
)