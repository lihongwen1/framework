buildscript {
    repositories {
        google()
        mavenCentral()
        jcenter() // Warning: this repository is going to shut down soon
    }
    dependencies {
        classpath(Dep.ClassPath.gradle)
        classpath(Dep.ClassPath.kotlin)
    }
}
allprojects {
    tasks.withType<JavaCompile> { options.encoding = Invariant.UTF_8 }
    repositories {
        google()
        mavenCentral()
        maven(Maven.jitpack)
        jcenter() // Warning: this repository is going to shut down soon
    }
}