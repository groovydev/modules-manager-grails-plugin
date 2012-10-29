includeTargets << new File("${modulesManagerPluginDir}/scripts/_RefreshModules.groovy")

eventCompileStart = {

    if (grailsSettings.config.modules?.refreshOnCompile) {
        refreshModules(grailsSettings.config.modules?.forceOverride)
    }

}
