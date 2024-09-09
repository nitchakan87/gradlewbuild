package com.slutedemo.slutedemoserver

import kotlinx.coroutines.ThreadContextElement
import kotlinx.coroutines.withContext
import org.slf4j.MDC
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import kotlin.coroutines.AbstractCoroutineContextElement
import kotlin.coroutines.CoroutineContext

/**
 * [MDC] context element for [CoroutineContext].
 *
 * Example:
 *
 * ```
 * MDC.put("kotlin", "rocks") // Put a value into the MDC context
 *
 * launch(MDCContext()) {
 *     logger.info { "..." }   // The MDC context contains the mapping here
 * }
 * ```
 *
 * Note that you cannot update MDC context from inside of the coroutine simply
 * using [MDC.put]. These updates are going to be lost on the next suspension and
 * reinstalled to the MDC context that was captured or explicitly specified in
 * [contextMap] when this object was created on the next resumption.
 * Use `withContext(MDCContext()) { ... }` to capture updated map of MDC keys and values
 * for the specified block of code.
 *
 * @param contextMap the value of [MDC] context map.
 * Default value is the copy of the current thread's context map that is acquired via
 * [MDC.getCopyOfContextMap].
 */
typealias MDCContextMap = Map<String, String>?
class MDCContext(
    /**
     * The value of [MDC] context map.
     */
    val contextMap: MDCContextMap = MDC.getCopyOfContextMap()
) : ThreadContextElement<MDCContextMap>, AbstractCoroutineContextElement(Key) {
    /**
     * Key of [MDCContext] in [CoroutineContext].
     */
    companion object Key : CoroutineContext.Key<MDCContext>

    /** @suppress */
    override fun updateThreadContext(context: CoroutineContext): MDCContextMap {
        val oldState = MDC.getCopyOfContextMap()
        setCurrent(contextMap)
        return oldState
    }

    /** @suppress */
    override fun restoreThreadContext(context: CoroutineContext, oldState: MDCContextMap) {
        setCurrent(oldState)
    }

    private fun setCurrent(contextMap: MDCContextMap) {
        if (contextMap == null) {
            MDC.clear()
        } else {
            MDC.setContextMap(contextMap)
        }
    }
}
suspend inline fun ServerRequest.coHandler(crossinline block: suspend (ServerRequest) -> ServerResponse) = withContext(
    MDCContext()
) { block(this@coHandler) }