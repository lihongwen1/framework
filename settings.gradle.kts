pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        maven("https://jitpack.io")
        google()
        mavenCentral()
        jcenter()
    }
}

include(":app")
include(":basis_net")
include(":basis_jitpack")
include(":basis_opensource")
include(":library_db")