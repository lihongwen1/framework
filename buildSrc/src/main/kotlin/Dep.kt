object Dep {

    internal object Version {
        const val gradle = "4.2.2"
        const val kotlin = "1.5.20"
        const val kotlinCoroutines = "1.5.1"

        const val appCompat = "1.3.0"
        const val fragmentKtx = "1.3.5"
        const val activityKtx = "1.2.3"
        const val coreKtx = "1.6.0"
        const val recyclerView = "1.2.1"
        const val viewPager2 = "1.0.0"
        const val constraintLayout = "2.0.4"
        const val cardView = "1.0.0"
        const val drawerLayout = "1.1.1"
        const val startup = "1.1.0-beta01"
        const val swipeRefreshLayout = "1.1.0"
        const val dataStore = "1.0.0-beta01"
        const val multidex = "2.0.1"
        const val lifecycle = "2.3.1"
        const val room = "2.3.0"

        const val material = "1.4.0"

        const val retrofit = "2.9.0"
        const val retrofitLog = "4.9.1"
        const val moshi = "1.12.0"

        const val uCrop = "2.2.6"
        const val glide = "4.12.0"
        const val coli = "1.3.0"
        const val multiType = "4.3.0"
        const val utilCode = "1.30.6"
        const val xxPermissions = "11.6"
        const val rxJava = "3.0.13"
        const val rxAndroid = "3.0.0"
        const val cropper = "2.8.0"
        const val cameraView = "2.7.1"
    }

    object ClassPath {
        // https://mvnrepository.com/artifact/org.jetbrains.kotlin/kotlin-gradle-plugin
        const val kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Version.kotlin}"

        // https://maven.google.com/web/index.html?q=gradle#com.android.tools.build:gradle
        const val gradle = "com.android.tools.build:gradle:${Version.gradle}"
    }

    object Kotlin {
        // https://mvnrepository.com/artifact/org.jetbrains.kotlin/kotlin-stdlib
        const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib:${Version.kotlin}"

        // https://mvnrepository.com/artifact/org.jetbrains.kotlinx/kotlinx-coroutines-android
        const val kotlinCoroutines =
            "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Version.kotlinCoroutines}"
    }

    object Jitpack {
        // https://maven.google.com/web/index.html?#androidx.appcompat:appcompat
        const val appCompat = "androidx.appcompat:appcompat:${Version.appCompat}"

        // https://maven.google.com/web/index.html?#androidx.fragment:fragment-ktx
        const val fragmentKtx = "androidx.fragment:fragment-ktx:${Version.fragmentKtx}"

        // https://maven.google.com/web/index.html?#androidx.activity:activity-ktx
        const val activityKxt = "androidx.activity:activity-ktx:${Version.activityKtx}"

        // https://maven.google.com/web/index.html?#androidx.core:core-ktx
        const val coreKtx = "androidx.core:core-ktx:${Version.coreKtx}"

        // https://maven.google.com/web/index.html?#androidx.recyclerview:recyclerview
        const val recyclerView = "androidx.recyclerview:recyclerview:${Version.recyclerView}"

        // https://maven.google.com/web/index.html?#androidx.viewpager2:viewpager2
        const val viewPager2 = "androidx.viewpager2:viewpager2:${Version.viewPager2}"

        // https://maven.google.com/web/index.html?#androidx.constraintlayout:constraintlayout
        const val constraintLayout =
            "androidx.constraintlayout:constraintlayout:${Version.constraintLayout}"

        // https://maven.google.com/web/index.html?#androidx.cardview:cardview
        const val cardView = "androidx.cardview:cardview:${Version.cardView}"

        // https://maven.google.com/web/index.html?#androidx.drawerlayout:drawerlayout
        const val drawerLayout = "androidx.drawerlayout:drawerlayout:${Version.drawerLayout}"

        // https://maven.google.com/web/index.html?#androidx.startup:startup-runtime
        const val startup = "androidx.startup:startup-runtime:${Version.startup}"

        // https://maven.google.com/web/index.html?#androidx.swiperefreshlayout:swiperefreshlayout
        const val swipeRefreshLayout =
            "androidx.swiperefreshlayout:swiperefreshlayout:${Version.swipeRefreshLayout}"

        // https://maven.google.com/web/index.html?#androidx.multidex:multidex
        const val multidex = "androidx.multidex:multidex:${Version.multidex}"

        // https://maven.google.com/web/index.html?#androidx.datastore
        const val dataStore = "androidx.datastore:datastore:${Version.dataStore}"
        const val dataStoreRxJava3 = "androidx.datastore:datastore-rxjava3:${Version.dataStore}"
        const val dataStoreCore = "androidx.datastore:datastore-core:${Version.dataStore}"
        const val dataStorePreferences =
            "androidx.datastore:datastore-preferences:${Version.dataStore}"
        const val dataStorePreferencesCore =
            "androidx.datastore:datastore-preferences-core:${Version.dataStore}"
        const val dataStorePreferencesRxJava3 =
            "androidx.datastore:datastore-preferences-rxjava3:${Version.dataStore}"

        // https://maven.google.com/web/index.html?#androidx.lifecycle
        const val lifecycleProcess = "androidx.lifecycle:lifecycle-process:${Version.lifecycle}"
        const val lifecycleRunTimeKtx =
            "androidx.lifecycle:lifecycle-runtime-ktx:${Version.lifecycle}"
        const val lifecycleVMKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Version.lifecycle}"
        const val lifecycleLDKtx = "androidx.lifecycle:lifecycle-livedata-ktx:${Version.lifecycle}"
        const val lifecycleJava8 = "androidx.lifecycle:lifecycle-common-java8:${Version.lifecycle}"

        // https://maven.google.com/web/index.html?#androidx.room
        const val roomRunTime = "androidx.room:room-runtime:${Version.room}"
        const val roomKtx = "androidx.room:room-ktx:${Version.room}"
        const val roomCompiler = "androidx.room:room-compiler:${Version.room}"

        // https://maven.google.com/web/index.html?#com.google.android.material
        const val material = "com.google.android.material:material:${Version.material}"
    }

    object Net {
        // https://github.com/square/retrofit
        const val retrofit = "com.squareup.retrofit2:retrofit:${Version.retrofit}"
        const val retrofitMoshi = "com.squareup.retrofit2:converter-moshi:${Version.retrofit}"

        // https://github.com/square/okhttp/blob/master/okhttp-logging-interceptor/README.md
        const val retrofitLog = "com.squareup.okhttp3:logging-interceptor:${Version.retrofitLog}"

        // https://github.com/square/moshi#codegen
        const val moshi = "com.squareup.moshi:moshi:${Version.moshi}"
        const val moshiKotlin = "com.squareup.moshi:moshi-kotlin:${Version.moshi}"
        const val moshiCodegen = "com.squareup.moshi:moshi-kotlin-codegen:${Version.moshi}"
    }

    object Image {
        // https://github.com/bumptech/glide#download
        const val glide = "com.github.bumptech.glide:glide:${Version.glide}"

        // https://github.com/coil-kt/coil#download
        const val coli = "io.coil-kt:coil:${Version.coli}"
        const val coliGif = "io.coil-kt:coil-gif:${Version.coli}"
        const val coliSvg = "io.coil-kt:coil-svg:${Version.coli}"
        const val coliVideo = "io.coil-kt:coil-video:${Version.coli}"

        // https://github.com/Yalantis/uCrop
        const val uCrop = "com.github.yalantis:ucrop:${Version.uCrop}"

        // https://github.com/ArthurHub/Android-Image-Cropper#usage
        const val cropper = "com.theartofdev.edmodo:android-image-cropper:${Version.cropper}"

        // https://github.com/natario1/CameraView#cameraview
        const val cameraView = "com.otaliastudios:cameraview:${Version.cameraView}"
    }

    object OpenSource {
        // https://github.com/getActivity/XXPermissions
        const val xxPermissions = "com.github.getActivity:XXPermissions:${Version.xxPermissions}"

        // https://github.com/drakeet/MultiType#getting-started
        const val multiType = "com.drakeet.multitype:multitype:${Version.multiType}"

        // https://github.com/Blankj/AndroidUtilCode/blob/master/lib/utilcode/README-CN.md
        const val utilCode = "com.blankj:utilcodex:${Version.utilCode}"
    }

    object Rx {
        // https://github.com/ReactiveX/RxJava
        const val rxJava = "io.reactivex.rxjava3:rxjava:${Version.rxJava}"

        // https://github.com/ReactiveX/RxAndroid#binaries
        const val rxAndroid = "io.reactivex.rxjava3:rxandroid:${Version.rxAndroid}"
    }

}