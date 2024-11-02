package app.revanced.bilibili.patches

import android.os.Bundle
import androidx.annotation.Keep
import app.revanced.bilibili.settings.Settings
import app.revanced.bilibili.utils.Logger
import app.revanced.bilibili.utils.toJson

@Keep
object H265CodecPatch {
    @JvmStatic
    fun enableH265Codec(): Boolean {
        return if (Settings.ForceDisadleH265Codec()) true else false
    }
}
