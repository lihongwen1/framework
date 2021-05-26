import java.io.File

object Invariant {
    const val UTF_8 = "UTF-8"
    const val RELEASE = "release"
    const val DEBUG = "debug"
    const val MAIN = "main"
    const val compiler = "compiler"
    const val armeabiV7a = "armeabi-v7a"
    const val consumerName = "consumer-rules.pro"
    const val proguardFileName = "proguard-rules.pro"
    const val defaultProguardFileName = "proguard-android-optimize.txt"
    const val kotlinModule = "META-INF/*.kotlin_module"

    object RoomArgs {

        val incremental = listOf("room.incremental", "true")
        val expandProjection = listOf("room.expandProjection", "true")

        fun schemaLocation(file: File): List<String> =
            listOf("room.schemaLocation", "$file/schemas")

    }

}