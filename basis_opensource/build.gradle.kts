plugins {
    id(Plugins.library)
    kotlin(Plugins.kotlin_android)
    id(Plugins.kotlin_parcelize)
    kotlin(Plugins.kotlin_kapt)
}
android {
    namespace = "frame.basis.opensource"
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
    DepList.opensource.forEach { compileOnly(it) }
}