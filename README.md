Modules Manager Grails Plugin
==============================

Usage
=====

Define js & css dependency.

grails-app/config/BuildConfig.groovy :

    ...
    dependencies {
        compile 'org.webjars:bootstrap:2.1.1'
        compile 'org.webjars:backbonejs:0.9.2'
        compile 'org.webjars:jquery-mobile:1.2.0'
    }
    ...

Create resources modules

    grails refresh-modules

Manager will create config/Modules**Resources.groovy and js/css files in web-app/modules/

To force override re-create resources modules

    grails refresh-modules --force


Note
====

Module manager support resource


History
=======

0.1.0   Initial version
