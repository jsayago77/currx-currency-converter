package com.jsayago77.currx.data.remote.mapper

import com.jsayago77.currx.data.remote.dto.DollarRateResponse
import com.jsayago77.currx.data.remote.dto.QuoteBRResponse
import com.jsayago77.currx.data.remote.dto.QuoteResponse
import com.jsayago77.currx.data.remote.dto.RateOption
import com.jsayago77.currx.data.remote.dto.RateResponse

fun QuoteResponse.toRateOption(): RateOption {
    val rate = promedio ?: compra ?: venta ?: 0.0
    return RateOption(rate, nombre, "DolarApi")
}

fun QuoteBRResponse.toRateOption(): RateOption {
    val rate = compra ?: venta ?: 0.0
    return RateOption(rate, nombre, "DolarApi")
}

fun DollarRateResponse.toRateOption(): RateOption {
    val rate = promedio ?: compra ?: venta ?: 0.0
    return RateOption(rate, nombre, "DolarApi")
}

fun RateResponse.toRateOption(): RateOption {
    return RateOption(rate, "interbank", "Frankfurther")
}