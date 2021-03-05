# flutter_plugin_becomedigital

A new flutter plugin project.

## Getting Started
### Android 
Establecer minSdkVersion 21
Agregar permisos de internet en AndroidManifest
<uses-permission android:name="android.permission.INTERNET"/>


### Errores comunes
Si al implementar te imprime el siguiente error
> Error:
	Attribute application@name value=(io.flutter.app.FlutterApplication) from AndroidManifest.xml:11:9-57
	is also present at [com.github.Becomedigital:become_ANDROID_SDK:2.2.7] AndroidManifest.xml:23:9-85 value=(com.becomedigital.sdk.identity.becomedigitalsdk.MyApplication).
	Suggestion: add 'tools:replace="android:name"' to <application> element at AndroidManifest.xml:9:5-48:19 to override.

En archivo `AndroidManifest` remover la propiedad `android:name="io.flutter.app.FlutterApplication"` de `<application>`

