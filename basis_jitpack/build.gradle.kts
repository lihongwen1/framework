plugins {
    id(Plugins.library)
    kotlin(Plugins.kotlin_android)
    id(Plugins.kotlin_parcelize)
}
android {
    namespace = "frame.basis.jitpack"
    compileSdk = SdkVersions.compileSdk
    defaultConfig {
        minSdk = SdkVersions.minSdk
        targetSdk = SdkVersions.targetSdk
    }
    buildFeatures {
        viewBinding = true
    }
}
dependencies {
    DepList.jitpack.forEach { implementation(it) }
}