class JsWasmPlatform: Platform {
    override val name: String = "Kotlin/Wasm ${System.getProperty("user.dir")}"
    override val supportDynamicTheming: Boolean = false
}

actual fun getPlatform(): Platform = JsWasmPlatform()