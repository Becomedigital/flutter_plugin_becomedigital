import 'dart:async';

import 'package:flutter/services.dart';
import 'package:flutter_plugin_becomedigital/BecomeError.dart';
import 'package:flutter_plugin_becomedigital/ResponseIV.dart';
import 'package:flutter_plugin_becomedigital/become_digital_config.dart';

class FlutterPluginBecomedigital {
  static const MethodChannel _channel =
      const MethodChannel('flutter_plugin_becomedigital');

  static Future<String> get platformVersion async {
    final String version = await _channel.invokeMethod('getPlatformVersion');
    return version;
  }

  initBecome(BecomeDigitalConfig config) {
    _channel.invokeMethod('BECOME_METHOD_INIT_BECOME', config.toMap());
  }

  setBecomeCallback(void Function(ResponseIv) callbackSuccess, void Function(BecomeError) callBackError) {
    _channel.setMethodCallHandler((call) async{
      print("${call.method} ${call.arguments is Map}${call.arguments}");
      switch (call.method) {
        case "BECOME_METHOD_SET_BECOME_CALLBACK_SUCCESS":
          callbackSuccess(ResponseIv.fromJson(call.arguments));
          break;

        case "BECOME_METHOD_SET_BECOME_CALLBACK_ERROR":
          print('ENVIANDO BECOME_METHOD_SET_BECOME_CALLBACK_ERROR');
          try {
            
          final message = call.arguments['message'];
          print('message $message');

          callBackError(BecomeError.fromMap(call.arguments));
          } catch (e) {
            print('Error enviando error $e');
          }

          break;
        default:
      }
    });
  }
}
