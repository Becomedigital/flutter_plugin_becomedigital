package becomedigital.net.flutter_plugin_becomedigital

import android.util.Log
import android.widget.Toast
import android.os.Handler
import androidx.annotation.NonNull
import com.becomedigital.sdk.identity.becomedigitalsdk.callback.BecomeCallBackManager
import com.becomedigital.sdk.identity.becomedigitalsdk.callback.BecomeInterfaseCallback
import com.becomedigital.sdk.identity.becomedigitalsdk.callback.LoginError
import com.becomedigital.sdk.identity.becomedigitalsdk.models.ResponseIV

import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result
import java.util.*
import kotlin.collections.HashMap

/** FlutterPluginBecomedigitalPlugin */
class FlutterPluginBecomedigitalPlugin : FlutterPlugin, MethodCallHandler {
    /// This local reference serves to register the plugin with the Flutter Engine and unregister it
    /// when the Flutter Engine is detached from the Activity
    private lateinit var channel: MethodChannel
    private val mCallbackManager = BecomeCallBackManager.createNew()


    val BECOME_METHOD_INIT_BECOME = "BECOME_METHOD_INIT_BECOME"
    val BECOME_METHOD_SET_BECOME_CALLBACK = "BECOME_METHOD_SET_BECOME_CALLBACK"

    override fun onAttachedToEngine(@NonNull flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
        channel = MethodChannel(flutterPluginBinding.binaryMessenger, "flutter_plugin_becomedigital")
        channel.setMethodCallHandler(this)

        Handler().postDelayed({
            val args: HashMap<String, String> = hashMapOf<String, String>()
            args.put("mensaje", "Hola")
            channel.invokeMethod(BECOME_METHOD_SET_BECOME_CALLBACK, args)
        },2000)


    }


    override fun onMethodCall(@NonNull call: MethodCall, @NonNull result: Result) {
        when (call.method) {
            "getPlatformVersion" -> {
                result.success("Android ${android.os.Build.VERSION.RELEASE}")
            }
            BECOME_METHOD_INIT_BECOME -> {

            }
            BECOME_METHOD_SET_BECOME_CALLBACK -> {

            }
            else -> {

                result.notImplemented()
            }
        }
    }

    override fun onDetachedFromEngine(@NonNull binding: FlutterPlugin.FlutterPluginBinding) {
        channel.setMethodCallHandler(null)
    }

    inner class BecomeDigitalInterfaceImpl : BecomeInterfaseCallback {
        override fun onSuccess(responseIV: ResponseIV?) {
            if (responseIV != null) {
                val args: HashMap<String, ResponseIV> = hashMapOf<String, ResponseIV>()
                args.put("responseIV", responseIV)
                channel.invokeMethod(BECOME_METHOD_SET_BECOME_CALLBACK, args)
            }
        }

        override fun onCancel() {
            Log.d("onCancel", "Cancelado por el usuario")

        }

        override fun onError(pLoginError: LoginError?) {
            pLoginError?.message?.let {
                Log.d("onError", it)
            }
        }
    }
}
