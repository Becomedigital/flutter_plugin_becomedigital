import 'package:flutter/material.dart';
import 'dart:async';

import 'package:flutter/services.dart';
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

  @override
  void initState() {
    super.initState();
    initPlatformState();
  }

  initBecome() {
    flutterPluginBecomedigital.initBecome(
      BecomeDigitalConfig(
        clientId: '',
        clientSecret: '',
        contractId: '',
        useGallery: true,
        validatiopnTypes: 'A/B/',
      ),
    );
    flutterPluginBecomedigital.setBecomeCallback((result) {
      print(result);
    }, (error) {
      print('Error');
    });
  }

  // Platform messages are asynchronous, so we initialize in an async method.
  Future<void> initPlatformState() async {
    String platformVersion;
    // Platform messages may fail, so we use a try/catch PlatformException.
    try {
      platformVersion = await FlutterPluginBecomedigital.platformVersion;
    } on PlatformException {
      platformVersion = 'Failed to get platform version.';
    }

    // If the widget was removed from the tree while the asynchronous platform
    // message was in flight, we want to discard the reply rather than calling
    // setState to update our non-existent appearance.
    if (!mounted) return;

    setState(() {
      _platformVersion = platformVersion;
    });
  }

  handlePressAuth() {}

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: const Text('Plugin example app'),
        ),
        body: Center(
            child: RaisedButton(
          onPressed: () {
            handlePressAuth();
          },
          child: Text('Iniciar autenticación'),
        )),
      ),
    );
  }
}
