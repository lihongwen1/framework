val schemaLocation = Invariant.RoomArgs.schemaLocation(projectDir)
plugins {
    id(Plugins.library)
    kotlin(Plugins.kotlin_android)
    id(Plugins.kotlin_parcelize)
    kotlin(Plugins.kotlin_kapt)
}
android {
    namespace = "frame.library.db"
    compileSdk = SdkVersions.compileSdk
    defaultConfig {
        minSdk = SdkVersions.minSdk
        targetSdk = SdkVersions.targetSdk
        kapt {
            arguments {
                arg(schemaLocation[0], schemaLocation[1])
                arg(Invariant.RoomArgs.incremental[0], Invariant.RoomArgs.incremental[1])
                arg(Invariant.RoomArgs.expandProjection[0], Invariant.RoomArgs.expandProjection[1])
            }
        }
    }
    sourceSets {
        getByName(Invariant.MAIN) {
            java.srcDirs(schemaLocation[1])
        }
    }
}
dependencies {
    api(Dep.Kotlin.kotlin)
    api(Dep.Jitpack.roomKtx)
    api(Dep.Jitpack.roomRunTime)
    kapt(Dep.Jitpack.roomCompiler)
}