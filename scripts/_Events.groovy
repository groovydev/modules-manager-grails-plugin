includeTargets << new File("${modulesManagerPluginDir}/scripts/RefreshModules.groovy")

eventCompileStart = {

    println "modulesManagerPluginDir: ${modulesManagerPluginDir}"

    println "grailsSettings.config.modules?.refreshOnCompile ${!!grailsSettings.config.modules?.refreshOnCompile}"
    println "grailsSettings.config.modules?.forceOverride ${!!grailsSettings.config.modules?.forceOverride}"

    if (grailsSettings.config.modules?.refreshOnCompile) {
        refreshModules(grailsSettings.config.modules?.forceOverride)
    }

}
