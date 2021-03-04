import 'package:flutter/services.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:flutter_plugin_becomedigital/flutter_plugin_becomedigital.dart';

void main() {
  const MethodChannel channel = MethodChannel('flutter_plugin_becomedigital');

  TestWidgetsFlutterBinding.ensureInitialized();

  setUp(() {
    channel.setMockMethodCallHandler((MethodCall methodCall) async {
      return '42';
    });
  });

  tearDown(() {
    channel.setMockMethodCallHandler(null);
  });

  test('getPlatformVersion', () async {
    expect(await FlutterPluginBecomedigital.platformVersion, '42');
  });
}
