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

    val list = DepList.base + DepList.extension + DepList.net + DepList.opensource + DepList.widget

    list
        .distinct()
        .filter { !it.contains(Invariant.compiler) }
        .forEach { api(it) }

    api(project(":basis_base"))
    api(project(":basis_net"))
    api(project(":basis_widget"))
    api(project(":basis_extension"))
    api(project(":basis_opensource"))

    api(Dep.multidex)
    api(Dep.coreKtx)
    api(Dep.recyclerView)
    api(Dep.viewPager2)
    api(Dep.constraintLayout)

}