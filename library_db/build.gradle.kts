val schemaLocation = Invariant.RoomArgs.schemaLocation(projectDir)
plugins {
    id(Plugins.library)
    kotlin(Plugins.kotlin_android)
    id(Plugins.kotlin_parcelize)
    kotlin(Plugins.kotlin_kapt)
}
android {
    compileSdkVersion(SdkVersions.compileSdk)
    defaultConfig {
        minSdkVersion(SdkVersions.minSdk)
        targetSdkVersion(SdkVersions.targetSdk)
        kapt {
            arguments {
                arg(schemaLocation[0], schemaLocation[1])
                arg(Invariant.RoomArgs.incremental[0], Invariant.RoomArgs.incremental[1])
                arg(Invariant.RoomArgs.expandProjection[0], Invariant.RoomArgs.expandProjection[1])
            }
        }
    }
    buildFeatures {
        viewBinding = true
    }
    sourceSets {
        getByName(Invariant.MAIN) {
            java.srcDirs(schemaLocation[1])
        }
    }
}
dependencies {
    api(Dep.kotlin)
    api(Dep.roomKtx)
    api(Dep.roomRunTime)
    kapt(Dep.roomCompiler)
}