package becomedigital.net.flutter_plugin_becomedigital


import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.becomedigital.sdk.identity.becomedigitalsdk.callback.BecomeResponseManager
import com.becomedigital.sdk.identity.becomedigitalsdk.models.BDIVConfig
import java.io.ByteArrayOutputStream
import java.text.SimpleDateFormat
import java.util.*


class ActivityPrueba : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_prueba)
        startAuthentication()
    }
    private fun startAuthentication() {


        val validatiopnTypes = "PASSPORT/LICENSE/DNI/VIDEO"
        val contractId = "2"
        val clientSecret: String = "FKLDM63GPH89TISBXNZ4YJUE57WRQA25";
        val clientId: String = "acc_demo";
        val currentTime = Calendar.getInstance().time
        val format1 =
                SimpleDateFormat("yyyyMMddHHmmssSSS", Locale.getDefault())
        val inActiveDate: String = format1.format(currentTime)
        val userId = inActiveDate
        val byteArrayOutputStream = ByteArrayOutputStream();

        //decodeResource.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        val byteArray = byteArrayOutputStream.toByteArray();
        try {

        } catch (err:Throwable){

        }
        Handler(Looper.getMainLooper()).post {
            BecomeResponseManager.getInstance().startAutentication(
                    this, BDIVConfig(
                    clientId,
                    clientSecret,
                    contractId,
                    validatiopnTypes,
                    true,
                    byteArray,
                    userId
            )

            )
        }

    }
}