interface Platform {
    val name: String
    val supportDynamicTheming: Boolean
}

expect fun getPlatform(): Platform

expect fun randomUUID(): String