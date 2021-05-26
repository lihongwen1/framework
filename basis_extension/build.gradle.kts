plugins {
    id(Plugins.library)
    kotlin(Plugins.kotlin_android)
    id(Plugins.kotlin_parcelize)
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
    DepList.extension.forEach { compileOnly(it) }
}