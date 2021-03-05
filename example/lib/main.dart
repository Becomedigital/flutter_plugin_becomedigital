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
        clientId: 'acc_demo',
        clientSecret: 'FKLDM63GPH89TISBXNZ4YJUE57WRQA25',
        contractId: '2',
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
