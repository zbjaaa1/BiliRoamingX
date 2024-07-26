package app.revanced.patches.bilibili.video.player.fingerprints

import app.revanced.patcher.fingerprint.MethodFingerprint

object IjkH265PlayerOptionsFingerprint : MethodFingerprint(
    strings = listOf("ijkplayer.h265-cpu-blacklist"),
    parameters = listOf(),
    returnType = "Z"
)
