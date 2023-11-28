class JVMPlatform: Platform {
    override val name: String = "Java ${System.getProperty("java.version")}"
    override val supportDynamicTheming: Boolean = false
}

actual fun getPlatform(): Platform = JVMPlatform()