package frame.basis.jitpack.extension.util

import android.util.Base64
import java.nio.charset.StandardCharsets
import java.security.MessageDigest
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

const val DEFAULT_KEY = "abcdefgabcdefgab"

private const val AES = "AES"
private val KEY_VI = "default_AES256VI".toByteArray()
private const val CIPHER_ALGORITHM = "AES/CBC/PKCS7Padding"

fun String.encodeAESExpand(key: String): String {
    return runCatching {
        val secretKey = SecretKeySpec(key.shaExpand().toByteArray(), 0, 32, AES)
        val cipher = Cipher.getInstance(CIPHER_ALGORITHM)
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, IvParameterSpec(KEY_VI))
        val byteEncode = toByteArray(StandardCharsets.UTF_8)
        val byteAES = cipher.doFinal(byteEncode)
        Base64.encodeToString(byteAES, Base64.NO_WRAP)
    }.onFailure { }.getOrNull().orEmpty()
}

fun String.decodeAESExpand(key: String): String {
    return runCatching {
        val secretKey = SecretKeySpec(key.shaExpand().toByteArray(), 0, 32, AES)
        val cipher = Cipher.getInstance(CIPHER_ALGORITHM)
        cipher.init(Cipher.DECRYPT_MODE, secretKey, IvParameterSpec(KEY_VI))
        val byteContent = Base64.decode(this, Base64.NO_WRAP)
        val byteDecode = cipher.doFinal(byteContent)
        String(byteDecode, StandardCharsets.UTF_8)
    }.onFailure { }.getOrNull().orEmpty()
}

fun String.shaExpand(): String {
    return runCatching {
        toHexExpand(MessageDigest.getInstance("SHA-256").digest(toByteArray()))
    }.getOrNull().orEmpty()
}

private fun toHexExpand(byteArray: ByteArray): String {
    val result = with(StringBuilder()) {
        byteArray.forEach {
            val hexStr = Integer.toHexString(it.toInt() and (0xFF))
            if (hexStr.length == 1) append("0")
            append(hexStr)
        }
        toString()
    }
    return result
}