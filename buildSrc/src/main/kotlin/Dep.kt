object Dep {

    internal object Version {
        const val gradle = "4.2.1"
        const val kotlin = "1.4.32"
        const val kotlinCoroutines = "1.4.1"
        const val material = "1.3.0"
        const val appCompat = "1.2.0"
        const val coreKtx = "1.3.2"
        const val lifecycle = "2.3.1"
        const val room = "2.3.0"
        const val constraintLayout = "2.0.4"
        const val recyclerView = "1.2.0"
        const val viewPager2 = "1.0.0"
        const val retrofit = "2.9.0"
        const val retrofitLog = "4.9.1"
        const val glide = "4.12.0"
        const val multiType = "4.2.0"
        const val moshi = "1.12.0"
        const val uCrop = "2.2.6"
        const val multidex = "2.0.1"
        const val utilCode = "1.30.6"
        const val coli = "1.2.1"
        const val rxJava = "2.2.21"
        const val rxAndroid = "2.1.1"
        const val xxPermissions = "11.0"
    }

    object ClassPath {
        const val kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Version.kotlin}"
        const val gradle = "com.android.tools.build:gradle:${Version.gradle}"
    }

    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib:${Version.kotlin}"
    const val kotlinCoroutines =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Version.kotlinCoroutines}"

    const val lifecycleProcess = "androidx.lifecycle:lifecycle-process:${Version.lifecycle}"
    const val lifecycleRunTimeKtx = "androidx.lifecycle:lifecycle-runtime-ktx:${Version.lifecycle}"
    const val lifecycleVMKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Version.lifecycle}"
    const val lifecycleLDKtx = "androidx.lifecycle:lifecycle-livedata-ktx:${Version.lifecycle}"
    const val lifecycleJava8 = "androidx.lifecycle:lifecycle-common-java8:${Version.lifecycle}"

    const val roomRunTime = "androidx.room:room-runtime:${Version.room}"
    const val roomKtx = "androidx.room:room-ktx:${Version.room}"
    const val roomCompiler = "androidx.room:room-compiler:${Version.room}"

    const val multidex = "androidx.multidex:multidex:${Version.multidex}"
    const val material = "com.google.android.material:material:${Version.material}"
    const val appCompat = "androidx.appcompat:appcompat:${Version.appCompat}"
    const val coreKtx = "androidx.core:core-ktx:${Version.coreKtx}"
    const val recyclerView = "androidx.recyclerview:recyclerview:${Version.recyclerView}"
    const val viewPager2 = "androidx.viewpager2:viewpager2:${Version.viewPager2}"
    const val constraintLayout =
        "androidx.constraintlayout:constraintlayout:${Version.constraintLayout}"

    const val retrofit = "com.squareup.retrofit2:retrofit:${Version.retrofit}"
    const val retrofitMoshi = "com.squareup.retrofit2:converter-moshi:${Version.retrofit}"
    const val retrofitLog = "com.squareup.okhttp3:logging-interceptor:${Version.retrofitLog}"
    const val moshi = "com.squareup.moshi:moshi:${Version.moshi}"
    const val moshiKotlin = "com.squareup.moshi:moshi-kotlin:${Version.moshi}"
    const val moshiCodegen = "com.squareup.moshi:moshi-kotlin-codegen:${Version.moshi}"

    const val uCrop = "com.github.yalantis:ucrop:${Version.uCrop}"
    const val glide = "com.github.bumptech.glide:glide:${Version.glide}"
    const val multiType = "com.drakeet.multitype:multitype:${Version.multiType}"
    const val utilCode = "com.blankj:utilcodex:${Version.utilCode}"
    const val xxPermissions = "com.github.getActivity:XXPermissions:${Version.xxPermissions}"

//    const val fragmentKtx = "androidx.fragment:fragment-ktx:1.3.3"
//    const val activityKxt = "androidx.activity:activity-ktx:1.2.3"
//    const val rxJava = "io.reactivex.rxjava2:rxjava:${Version.rxJava}"
//    const val rxAndroid = "io.reactivex.rxjava2:rxandroid:${Version.rxAndroid}"
//    const val coli = "io.coil-kt:coil:${Version.coli}"
//    const val coliGif = "io.coil-kt:coil-gif:${Version.coli}"
//    const val coliSvg = "io.coil-kt:coil-svg:${Version.coli}"
//    const val coliVideo = "io.coil-kt:coil-video:${Version.coli}"
}