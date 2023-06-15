import org.asciidoctor.gradle.jvm.AsciidoctorTask
import org.asciidoctor.gradle.jvm.pdf.AsciidoctorPdfTask

plugins {
    java
    id("org.asciidoctor.jvm.convert") version "3.3.2"
    id("org.asciidoctor.jvm.gems") version "3.3.2"
    id("org.asciidoctor.jvm.pdf") version "3.3.2"
    id("com.github.jruby-gradle.base") version "2.1.0-alpha.2"
}

version = File("${rootProject.projectDir.absolutePath}/.version").readText().trimEnd()

dependencies {
    asciidoctorGems("rubygems:rouge:3.29.0")
    implementation("org.asciidoctor:asciidoctorj:2.5.7")
}

// Fix SCA vulnerabilities scan
buildscript {
    dependencies {
        classpath("com.fasterxml.jackson.core:jackson-databind:2.13.4.2")
        classpath("io.netty:netty-handler:4.1.46.Final")
    }
}

tasks.withType<AsciidoctorTask> {
    outputOptions {
        backends("pdf")
    }
    baseDirFollowsSourceDir()
    setSourceDir(file("${projectDir}/"))
    setLanguages(listOf("ru"))
    setOutputDir(file("${projectDir}/build/docs/asciidocPdf/modules/"))
    sources {
        include("*Desc*.adoc", "*Guide*.adoc")
    }
}

tasks.withType<AsciidoctorPdfTask> {
    baseDirFollowsSourceDir()
    setSourceDir(file("${projectDir}/"))
    setLanguages(listOf("ru"))
    sources {
        include("*Desc*.adoc", "*Guide*.adoc")
    }
}

asciidoctorj {
    setVersion("2.5.7")
    modules {
        pdf.setVersion("2.3.4")
        diagram.setVersion("2.2.3")
    }
    attributes(
        mutableMapOf(
            "iconsdir" to file("${projectDir}/common/images/icons"),
            "pdf-fontsdir" to file("${projectDir}/common/fonts"),
            "pdf-theme" to file("${projectDir}/common/themes/base-theme.yml")
        )
    )
    requires("${projectDir}/common/scripts/remove-period.rb")
}
