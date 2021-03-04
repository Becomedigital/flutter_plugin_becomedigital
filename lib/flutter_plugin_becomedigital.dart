
import 'dart:async';

import 'package:flutter/services.dart';
import 'package:flutter_plugin_becomedigital/become_digital_config.dart';

class FlutterPluginBecomedigital {
  static const MethodChannel _channel =
      const MethodChannel('flutter_plugin_becomedigital');

  static Future<String> get platformVersion async {
    final String version = await _channel.invokeMethod('getPlatformVersion');
    return version;
  }

  initBecome(BecomeDigitalConfig config){
    final map = config.toMap();
    //Llamar al metodo nativo


  }


  setBecomeCallback(Function callbackSuccess,Function callBackError){
    _channel.setMethodCallHandler((call){
      print('Metodo invocado');
      print(call.method);
      print(call.arguments);
    });
  }
}
