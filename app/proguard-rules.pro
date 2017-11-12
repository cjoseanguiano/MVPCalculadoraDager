-keep class * extends dagger.internal.Binding
-keep class * extends dagger.internal.ModuleAdapter
-keep class * extends dagger.internal.StaticInjection
-keep class * extends dagger.internal.BindingsGroup
-dontwarn dagger.internal.**

# Conserva las anotaciones de los campos anotados.
-keepattributes *Annotation*

# Conserva las propias clases de las anotaciones de Dagger
-keep interface dagger.*,javax.inject.*

# Conserva las clases anotadas con @Module.
-keep @dagger.Module class *

## Conserva los miembros anotados con @Inject en las clases que no sean eliminadas.
#-keepclassmembers class * {
#  @javax.inject.* <fields>;
#}

## Conserva los nombres de las clases con campos anotados con @Inject y los propios campos.
#-keepclasseswithmembernames class * {
#  @javax.inject.* <fields>;
#}

# Conserva las clases autogeneradas por dagger-compile
-keep class **$$ModuleAdapter
-keep class **$$InjectAdapter
-keep class **$$StaticInjection
