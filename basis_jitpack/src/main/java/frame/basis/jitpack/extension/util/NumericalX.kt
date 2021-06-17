package frame.basis.jitpack.extension.util

import java.math.BigDecimal
import java.math.BigInteger

fun String.toByteExpand(defaultValue: Byte): Byte =
    toByteOrDefault { defaultValue }

fun String.toShortExpand(defaultValue: Short): Short =
    toShortOrDefault { defaultValue }

fun String.toIntExpand(defaultValue: Int): Int =
    toIntOrDefault { defaultValue }

fun String.toLongExpand(defaultValue: Long): Long =
    toLongOrDefault { defaultValue }

fun String.toFloatExpand(defaultValue: Float): Float =
    toFloatOrDefault { defaultValue }

fun String.toDoubleExpand(defaultValue: Double): Double =
    toDoubleOrDefault { defaultValue }

fun String.toBigIntegerExpand(defaultValue: BigInteger): BigInteger =
    toBigIntegerOrDefault { defaultValue }

fun String.toBigDecimalExpand(defaultValue: BigDecimal): BigDecimal =
    toBigDecimalOrDefault { defaultValue }

fun String.toByteOrDefault(action: () -> Byte): Byte =
    toByteOrNull() ?: action.invoke()

fun String.toShortOrDefault(action: () -> Short): Short =
    toShortOrNull() ?: action.invoke()

fun String.toIntOrDefault(action: () -> Int): Int =
    toIntOrNull() ?: action.invoke()

fun String.toLongOrDefault(action: () -> Long): Long =
    toLongOrNull() ?: action.invoke()

fun String.toFloatOrDefault(action: () -> Float): Float =
    toFloatOrNull() ?: action.invoke()

fun String.toDoubleOrDefault(action: () -> Double): Double =
    toDoubleOrNull() ?: action.invoke()

fun String.toBigIntegerOrDefault(action: () -> BigInteger): BigInteger =
    toBigIntegerOrNull() ?: action.invoke()

fun String.toBigDecimalOrDefault(action: () -> BigDecimal): BigDecimal =
    toBigDecimalOrNull() ?: action.invoke()