includeTargets << new File("${modulesManagerPluginDir}/scripts/RefreshModules.groovy")

eventCompileStart = {

    if (grailsSettings.config.modules?.refreshOnCompile) {
        refreshModules(grailsSettings.config.modules?.forceOverride)
    }

}
