In order to start App run .target/start.cmd.
It will create ./target/modules folder(if it does not exists) and start App.
To deploy module you need to put jar with class that implement IModule interface.
While executing module all classes that implement IModule will be executed.
You can use "module" project to make such jar.