Modules Manager Grails Plugin
==============================

Creates resources modules using packaged resources from maven central repository. Script consume jar packaged web js/css resources and produce resources DSL with web-app assets.


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

To re-create and force override resources modules

    grails refresh-modules --force


Note
====

Module manager only support web resources from webjars.org


History
=======

0.1.0   Initial version
