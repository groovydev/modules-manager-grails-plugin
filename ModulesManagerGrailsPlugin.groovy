class ModulesManagerGrailsPlugin {
    def version = "0.1.0"
    def grailsVersion = "2.0 > *"
    def dependsOn = [:]
    def pluginExcludes = [
        "grails-app/views/error.gsp"
    ]
    def title = "Resources Modules Manager Plugin"
    def author = "Karol Balejko"
    def authorEmail = "kb@groovydev.com"
    def description = 'Creates resources modules using packaged resources from maven central repository.'
    def documentation = "http://grails.org/plugin/modules-manager"

    def license = "APACHE"
    def organization = [ name: "GroovyDev", url: "http://groovydev.com/" ]
    def scm = [ url: "https://github.com/groovydev/modules-manager-grails-plugin" ]
}