import org.apache.commons.lang.StringUtils
import java.util.zip.ZipFile;
import java.util.zip.ZipEntry;

refreshModules = {forceOverride->

    def workDir = "${grailsSettings.projectWorkDir}/packagemanager"

    grailsSettings.compileDependencies.each {jarfile->

		boolean isResource = false;
		
		ZipFile zip = new ZipFile (jarfile);
		isResource = zip.getEntry("META-INF/resources/webjars") != null;
			
		if (isResource) {
			
			
	        ant.unjar (src: jarfile, dest: workDir) {
	            patternset excludes: "**/*.min.css **/*.min.js **/*-min.css **/*-min.js"
	        }
	
	        // webjars.org support
	
	        def deps = []
	        def resourcesUrls = []
	        def artifactId
	
	        if (new File(workDir, "META-INF/resources/webjars").exists()) {
	
	            // parse pom
	            new File(workDir).eachFileRecurse {file->
	                if (!file.file || file.name != 'pom.xml') return
	                def pom = new XmlParser().parse(file)
	                artifactId = pom.artifactId.text()
	                pom.dependencies.findAll {
	                    deps << it.dependency.artifactId.text()
	                }
	            }
	
	            new File(workDir, "META-INF/resources/webjars").eachFileRecurse {file->
	                if (file.file) {
	
	                    def relpath = StringUtils.removeStart(file.path, "${workDir}/META-INF/resources/webjars/")
	
	                    def resourcePath = relpath
	                    def webjarsMatcher = resourcePath =~ /([^\/]+)\/([^\/]+)\/(.+)/
	                    if (webjarsMatcher.matches()) {
	
	                        def module = webjarsMatcher.group(1)
	                        def version = webjarsMatcher.group(2)
	                        def path = webjarsMatcher.group(3)
	
	                        if (!artifactId) {
	                            artifactId = module
	                        }
	
	                        if (forceOverride || !new File(grailsSettings.baseDir, "web-app/modules/${artifactId}/${path}").exists()) {
	                            ant.copyfile(src: file, dest: "${grailsSettings.baseDir}/web-app/modules/${artifactId}/${path}")
	                            event('CreatedFile', ["${grailsSettings.baseDir}/web-app/modules/${artifactId}/${path}"])
	                            if (StringUtils.endsWithAny("modules/${artifactId}/${path}", ['.js','.css'] as String[])) {
	                                resourcesUrls << "modules/${artifactId}/${path}"
	                            }
	                        }
	                    }
	                }
	            }
	
	            def resourceDSLFilepath = "${grailsSettings.baseDir}/grails-app/conf/Modules${StringUtils.capitalise(artifactId)}Resources.groovy"
	
	            def templ = "modules = {\n"
	            templ += "    '${artifactId}' {\n"
	            if (deps) {
	                templ += "        dependsOn '${deps.join()}'\n"
	            }
	            resourcesUrls.each {
	                templ += "        resource url: '${it}'\n"
	            }
	            templ += "    }\n"
	            templ += "}\n"
	
	            if (forceOverride || !new File(resourceDSLFilepath).exists()) {
	                ant.echo(file:resourceDSLFilepath,message: templ)
	                event('CreatedFile', [resourceDSLFilepath])
	            }
	        }
	
	        ant.delete(dir: workDir)
	    }
	}
}

