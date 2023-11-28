import android.os.Build

import androidx.annotation.ChecksSdkIntAtLeast

@ChecksSdkIntAtLeast(api = Build.VERSION_CODES.S)
fun supportsDynamicTheming() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S

class AndroidPlatform : Platform {
    override val name: String = "Android ${Build.VERSION.SDK_INT}"

    override val supportDynamicTheming: Boolean
        get() = supportsDynamicTheming()
}

actual fun getPlatform(): Platform = AndroidPlatform()