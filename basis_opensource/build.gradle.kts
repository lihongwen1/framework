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
    }
    buildFeatures {
        viewBinding = true
    }
}
dependencies {
    DepList.opensource.forEach { compileOnly(it) }
}