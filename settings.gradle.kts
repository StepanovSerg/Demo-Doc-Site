rootProject.name = "adm-web-calendar-facade"
include("docs")

pluginManagement {
    repositories {
        maven {
            url = uri("https://nexus.inno.tech/repository/gradle-plugins/")
            credentials {
                username = System.getProperty("inno.nexus.user")
                password = System.getProperty("inno.nexus.password")
            }
            artifactUrls("https://nexus.inno.tech/repository/common-proxy-maven-gradle/")
        }
    }
}

dependencyResolutionManagement {
    @Suppress("UnstableApiUsage")
    repositories {
        val usernameProp = System.getProperty("inno.nexus.user")
        val passwordProp = System.getProperty("inno.nexus.password")

        maven {
           name = "maven-central-mirror"
            url = uri("https://nexus.inno.tech/repository/common-proxy-maven-maven_org/")
            credentials {
                username = usernameProp
                password = passwordProp
            }
        }
        maven {
            name = "maven-central-inno"
            url = uri("https://nexus.inno.tech/repository/dbp-hosted-maven-development/")
            credentials {
                username = usernameProp
                password = passwordProp
            }
        }
    }
}
