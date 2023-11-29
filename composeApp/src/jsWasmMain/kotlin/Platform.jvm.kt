//import io.ktor.client.HttpClient
//import kotlin.wasm.*

class JsWasmPlatform: Platform {
    override val name: String = "Kotlin/Wasm {TODO}"
    override val supportDynamicTheming: Boolean = false
}

actual fun getPlatform(): Platform = JsWasmPlatform()