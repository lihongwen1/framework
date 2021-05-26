object DepList {
    val base: List<String> = arrayListOf<String>().apply {
        add(Dep.kotlin)
        add(Dep.kotlinCoroutines)
        add(Dep.appCompat)
    }
    val extension: List<String> = arrayListOf<String>().apply {
        add(Dep.kotlin)
        add(Dep.kotlinCoroutines)
        add(Dep.material)
        add(Dep.appCompat)
        add(Dep.lifecycleLDKtx)
        add(Dep.lifecycleVMKtx)
        add(Dep.lifecycleRunTimeKtx)
        add(Dep.lifecycleProcess)
        add(Dep.lifecycleJava8)
    }
    val net: List<String> = arrayListOf<String>().apply {
        add(Dep.kotlin)
        add(Dep.retrofit)
        add(Dep.retrofitMoshi)
        add(Dep.retrofitLog)
        add(Dep.moshi)
        add(Dep.moshiKotlin)
        add(Dep.moshiCodegen)
    }
    val opensource: List<String> = arrayListOf<String>().apply {
        add(Dep.kotlin)
        add(Dep.glide)
        add(Dep.uCrop)
        add(Dep.utilCode)
        add(Dep.multiType)
        add(Dep.xxPermissions)
    }
    val widget: List<String> = arrayListOf<String>().apply {
        add(Dep.kotlin)
    }
}