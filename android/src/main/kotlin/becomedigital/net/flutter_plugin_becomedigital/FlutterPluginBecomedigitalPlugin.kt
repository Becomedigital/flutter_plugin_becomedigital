package becomedigital.net.flutter_plugin_becomedigital

import android.app.Activity
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.annotation.NonNull
import com.becomedigital.sdk.identity.becomedigitalsdk.callback.BecomeCallBackManager
import com.becomedigital.sdk.identity.becomedigitalsdk.callback.BecomeInterfaseCallback
import com.becomedigital.sdk.identity.becomedigitalsdk.callback.BecomeResponseManager
import com.becomedigital.sdk.identity.becomedigitalsdk.callback.LoginError
import com.becomedigital.sdk.identity.becomedigitalsdk.models.BDIVConfig
import com.becomedigital.sdk.identity.becomedigitalsdk.models.ResponseIV
import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.embedding.engine.plugins.activity.ActivityAware
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result
import org.json.JSONException
import java.io.ByteArrayOutputStream
import java.util.*


/** FlutterPluginBecomedigitalPlugin */
class FlutterPluginBecomedigitalPlugin() : FlutterPlugin, MethodCallHandler, ActivityAware {
    /// This local reference serves to register the plugin with the Flutter Engine and unregister it
    /// when the Flutter Engine is detached from the Activity
    private lateinit var channel: MethodChannel
    private val mCallbackManager = BecomeCallBackManager.createNew()


    lateinit var activity: Activity


    private val BECOME_METHOD_INIT_BECOME = "BECOME_METHOD_INIT_BECOME"
    private val BECOME_METHOD_SET_BECOME_CALLBACK_SUCCESS = "BECOME_METHOD_SET_BECOME_CALLBACK_SUCCESS"
    private val BECOME_METHOD_SET_BECOME_CALLBACK_ERROR = "BECOME_METHOD_SET_BECOME_CALLBACK_ERROR"

    companion object {

    }

    override fun onAttachedToEngine(@NonNull flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
        channel = MethodChannel(flutterPluginBinding.binaryMessenger, "flutter_plugin_becomedigital")
        channel.setMethodCallHandler(this)
        val becomeDigitalInterfaceImpl: BecomeDigitalInterfaceImpl = BecomeDigitalInterfaceImpl()
        BecomeResponseManager.getInstance().registerCallback(mCallbackManager, becomeDigitalInterfaceImpl)


    }


    override fun onMethodCall(@NonNull call: MethodCall, @NonNull result: Result) {
        println("On Method all")
        when (call.method) {
            BECOME_METHOD_INIT_BECOME -> {
                startAuthentication(call.arguments as HashMap<String,Any>)
                result.success(null)
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
                val id = responseIV.id
                val created_at = responseIV.created_at
                val company = responseIV.company
                val fullname = responseIV.fullname
                val birth = responseIV.birth
                val document_type = responseIV.document_type
                val document_number = responseIV.document_number
                val face_match = responseIV.face_match
                val template = responseIV.template
                val alteration = responseIV.alteration
                val watch_list = responseIV.watch_list
                val comply_advantage_result = responseIV.comply_advantage_result
                val comply_advantage_url = responseIV.comply_advantage_url
                val verification_status = responseIV.verification_status
                val message = responseIV.message
                val responseStatus = responseIV.responseStatus

                val data = HashMap<String,Any>()

                try {
                    data.put("id", id)
                    data["created_at"] = created_at
                    data["company"] = company
                    data.put("fullname", fullname)
                    data.put("birth", birth)
                    data.put("document_type", document_type)
                    data.put("document_number", document_number)
                    data.put("face_match", face_match)
                    data.put("template", template)
                    data.put("alteration", alteration)
                    data.put("watch_list", watch_list)
                    data.put("comply_advantage_result", comply_advantage_result)
                    data.put("comply_advantage_url", comply_advantage_url)
                    data.put("verification_status", verification_status)
                    data.put("message", message)
                    data.put("responseStatus:", responseStatus)
                    channel.invokeMethod(BECOME_METHOD_SET_BECOME_CALLBACK_SUCCESS, data)

                } catch (e: JSONException) {
                    val data = HashMap<String,Any>()
                    e?.message?.let { data["message"] =  it }
                    emitError(data)
                }

            }
        }

        override fun onCancel() {
            Log.d("onCancel", "Cancelado por el usuario")
            val data = HashMap<String,Any>()
            data["message"] =  "Cancelado por el usuario"
            emitError(data)
        }

        override fun onError(pLoginError: LoginError?) {
            pLoginError?.message?.let {
                Log.d("onError", it)
                val data = HashMap<String,Any>()
                data["message"] =  it
                emitError(data)

            }
        }
    }

    private fun emitError(data: HashMap<String,Any>) {
        channel.invokeMethod(BECOME_METHOD_SET_BECOME_CALLBACK_ERROR, data)
    }


    private fun startAuthentication(argument: HashMap<String,Any>) {

        val validationTypes:String = argument["validatiopnTypes"] as String
        val contractId = argument["contractId"] as String
        val clientSecret: String = argument["clientSecret"] as String
        val clientId: String = argument["clientId"] as String
        val userId = argument["userId"] as String
        val useGallery:Boolean = argument["useGallery"] as Boolean
        val byteArrayOutputStream = ByteArrayOutputStream();
        val byteArray = byteArrayOutputStream.toByteArray();
        Handler(Looper.getMainLooper()).post {
            BecomeResponseManager.getInstance().startAutentication(
                    activity, BDIVConfig(
                    clientId,
                    clientSecret,
                    contractId,
                    validationTypes,
                    useGallery,
                    byteArray,
                    userId
            )

            )
        }

    }

    override fun onDetachedFromActivity() {

    }

    override fun onReattachedToActivityForConfigChanges(binding: ActivityPluginBinding) {
        onAttachedToActivity(binding)
    }

    override fun onAttachedToActivity(binding: ActivityPluginBinding) {
        activity = binding.activity
    }

    override fun onDetachedFromActivityForConfigChanges() {
        TODO("Not yet implemented")
    }


}
