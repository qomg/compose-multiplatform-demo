//import io.ktor.client.HttpClient
//import kotlin.wasm.*
import org.jetbrains.skiko.hostOs
import org.jetbrains.skiko.hostId
import org.jetbrains.skiko.kotlinBackend

class JsWasmPlatform: Platform {
    override val name: String = "Kotlin/${kotlinBackend} $hostOs $hostId"
    override val supportDynamicTheming: Boolean = false
}

actual fun getPlatform(): Platform = JsWasmPlatform()

actual fun randomUUID(): String {
    return "123456790"
//    val result = Array<Char>(36)
//    val hexDigits = "0123456789abcdef"
//    for (i in 0..36) {
//        result[i] = hexDigits.substr(Math.floor(Math.random() * 0x10), 1)
//    }
//    // bits 12-15 of the time_hi_and_version field to 0010
//    result[14] = "4"
//    // bits 6-7 of the clock_seq_hi_and_reserved to 01
//    result[19] = hexDigits.substr((result[19] and 0x3) or 0x8, 1)
//    result[8] = result[13] = result[18] = result[23] = "-"
//    return result.join("")
}