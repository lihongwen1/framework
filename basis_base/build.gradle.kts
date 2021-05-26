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
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    DepList.base.forEach { compileOnly(it) }
}