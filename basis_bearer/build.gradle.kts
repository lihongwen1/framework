plugins {
    id(Plugins.library)
    kotlin(Plugins.kotlin_android)
}
android {
    compileSdkVersion(SdkVersions.compileSdk)
    defaultConfig {
        minSdkVersion(SdkVersions.minSdk)
        targetSdkVersion(SdkVersions.targetSdk)
    }
}
dependencies {

    val list =
        DepList.jitpack + DepList.net + DepList.opensource + DepList.widget

    list
        .distinct()
        .filter { !it.contains(Invariant.compiler) }
        .forEach { api(it) }

    api(project(":basis_jitpack"))
    api(project(":basis_net"))
    api(project(":basis_widget"))
    api(project(":basis_opensource"))
}