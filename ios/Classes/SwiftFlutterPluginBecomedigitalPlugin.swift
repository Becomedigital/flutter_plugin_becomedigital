import Flutter
import UIKit

public class SwiftFlutterPluginBecomedigitalPlugin: NSObject, FlutterPlugin {
  public static func register(with registrar: FlutterPluginRegistrar) {
    let channel = FlutterMethodChannel(name: "flutter_plugin_becomedigital", binaryMessenger: registrar.messenger())
    let instance = SwiftFlutterPluginBecomedigitalPlugin()
    registrar.addMethodCallDelegate(instance, channel: channel)
  }

  public func handle(_ call: FlutterMethodCall, result: @escaping FlutterResult) {
    result("iOS " + UIDevice.current.systemVersion)
  }
}
