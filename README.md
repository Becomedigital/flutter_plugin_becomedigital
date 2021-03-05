# flutter_plugin_becomedigital

A new flutter plugin project.

## Getting Started


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
        validatiopnTypes: 'PASSPORT/LICENSE/DNI/VIDEO',
        userId: '123456',
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

