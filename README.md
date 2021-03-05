# flutter_plugin_becomedigital

[pub.dev flutter_plugin_becomedigital](https://pub.dev/packages/flutter_plugin_becomedigital)

## Getting Started
Agregar al pubspec.yaml la dependencia

<code>
dependencies:

    flutter_plugin_becomedigital: ^0.0.1
</code>



## Llamado al plugin desde FLUTTER
```
import 'package:flutter/material.dart';
import 'dart:async';

import 'package:flutter/services.dart';
import 'package:flutter_plugin_becomedigital/BecomeError.dart';
import 'package:flutter_plugin_becomedigital/ResponseIV.dart';
import 'package:flutter_plugin_becomedigital/become_digital_config.dart';
import 'package:flutter_plugin_becomedigital/flutter_plugin_becomedigital.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatefulWidget {
  @override
  _MyAppState createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  String _platformVersion = 'Unknown';
  FlutterPluginBecomedigital flutterPluginBecomedigital =
      FlutterPluginBecomedigital();

  ResponseIv responseIv;
  BecomeError becomeError;

  @override
  void initState() {
    super.initState();
    initBecome();
  }

  initBecome() {
    flutterPluginBecomedigital.setBecomeCallback((result) {
      setState(() {
        responseIv = result;
      });
      print(result);
    }, (error) {
      setState(() {
        becomeError = error;
      });
    });
  }

  handlePressAuth() {
    flutterPluginBecomedigital.initBecome(
      BecomeDigitalConfig(
        clientId: '<YOUR_CLIENT_ID>',
        clientSecret: '<YOUR_CLIENT_SECRET>',
        contractId: '<YOUR_CONTRACT_ID>',
        useGallery: true,
        /// El parámetro validationType es customizable y puede tomar una o más valores dependiendo del tipo de contrato y la cantidad de tipos de documento que el cliente espere aceptar
        validatiopnTypes: 'PASSPORT/LICENSE/DNI/VIDEO',
        userId: DateTime.now().millisecondsSinceEpoch.toString(),
      ),
    );
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: const Text('BECOME PLUGIN Flutter'),
        ),
        body: SafeArea(
          child: Padding(
            padding: const EdgeInsets.all(8.0),
            child: Center(
                child: Column(
              mainAxisSize: MainAxisSize.max,
              mainAxisAlignment: MainAxisAlignment.center,
              children: [
                Text("Error: ${becomeError?.message}"),
                Text('Respuesta: ${responseIv?.toString()}'),
                RaisedButton(
                  onPressed: () {
                    handlePressAuth();
                  },
                  child: Text('Iniciar autenticación'),
                ),
              ],
            )),
          ),
        ),
      ),
    );
  }
}

```
### Instalación en Android 
1. Establecer minSdkVersion 21 en `app/build.gradle`

2. Agregar permisos de internet en AndroidManifest

` <uses-permission android:name="android.permission.INTERNET"/>`

3. Agregar a `app/build.gradle` 

`proguardFiles getDefaultProguardFile(
                    'proguard-android-optimize.txt'),
                    'proguard-rules.pro'`

4. Agregar en el archivo `proguard-rules.pro` (crearlo sino está presente) en `app` lo siguiente 

` -keep class androidx.navigation.fragment.NavHostFragment
`


### Errores comunes
Si al implementar te imprime el siguiente error
> Error:
	Attribute application@name value=(io.flutter.app.FlutterApplication) from AndroidManifest.xml:11:9-57
	is also present at [com.github.Becomedigital:become_ANDROID_SDK:2.2.7] AndroidManifest.xml:23:9-85 value=(com.becomedigital.sdk.identity.becomedigitalsdk.MyApplication).
	Suggestion: add 'tools:replace="android:name"' to <application> element at AndroidManifest.xml:9:5-48:19 to override.

En archivo `AndroidManifest` remover la propiedad `android:name="io.flutter.app.FlutterApplication"` de `<application>`

