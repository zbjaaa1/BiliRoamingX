package app.revanced.patches.bilibili.video.player.patch

import app.revanced.patcher.data.BytecodeContext
import app.revanced.patcher.extensions.InstructionExtensions.addInstruction
import app.revanced.patcher.extensions.InstructionExtensions.addInstructions
import app.revanced.patcher.patch.BytecodePatch
import app.revanced.patcher.patch.annotation.CompatiblePackage
import app.revanced.patcher.patch.annotation.Patch
import app.revanced.patches.bilibili.video.player.fingerprints.IjkH265PlayerOptionsFingerprint
import app.revanced.util.exception
import com.android.tools.smali.dexlib2.Opcode
import com.android.tools.smali.dexlib2.builder.instruction.BuilderInstruction22c
import com.android.tools.smali.dexlib2.builder.instruction.BuilderInstruction35c
import com.android.tools.smali.dexlib2.iface.reference.MethodReference

@Patch(
    name = "Force disable h265 codec",
    description = "强制禁用h265",
    compatiblePackages = [
        CompatiblePackage(name = "tv.danmaku.bili"),
        CompatiblePackage(name = "tv.danmaku.bilibilihd"),
        CompatiblePackage(name = "com.bilibili.app.in")
    ]
)
object H265CodecPatch : BytecodePatch(setOf(IjkH265PlayerOptionsFingerprint)) {
    override fun execute(context: BytecodeContext) {
        IjkH265PlayerOptionsFingerprint.result?.mutableMethod?.addInstructions(
            0,
            """
                invoke-static {}, Lapp/revanced/bilibili/patches/H265CodecPatch;->enableH265Codec()Z
                move-result v0
                if-eqz v0, :jump
                const/4 v0, 0x1
                return v0
                :jump
            """,
        ) ?: throw IjkH265PlayerOptionsFingerprint.exception
    }
}
