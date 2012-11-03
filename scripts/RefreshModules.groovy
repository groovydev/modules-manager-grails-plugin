import org.apache.commons.lang.StringUtils

includeTargets << grailsScript("_GrailsInit")
includeTargets << grailsScript("_GrailsArgParsing")
includeTargets << new File("${modulesManagerPluginDir}/scripts/_RefreshModules.groovy")

target(main: "Refresh resources modules") {
    depends(parseArguments)
    refreshModules(argsMap.force)
}

setDefaultTarget(main)
