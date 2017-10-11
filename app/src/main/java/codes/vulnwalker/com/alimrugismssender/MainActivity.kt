package codes.vulnwalker.com.alimrugismssender

import android.content.DialogInterface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.View
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast
import org.json.JSONObject
import java.util.HashMap

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sendSMS.setOnClickListener {
            val queue = Volley.newRequestQueue(this@MainActivity)
            val response: String? = null
            var currentSaldo: String? = null
            val postRequest = object : StringRequest(Method.POST, "http://sms.vherolly.com/gammu/kirim/mobile_send_v2.php", Response.Listener<String>{
                response ->
                toast("SMS Terkirim")

            },
                    Response.ErrorListener {
                        toast("error")
                    }
            ) {
                override fun getParams(): Map<String, String> {
                    val params = HashMap<String, String>()
                    params.put("editPhone", nomorTujuan.text.toString())
                    params.put("editEmail", "nurmelly16@gmail.com")
                    params.put("editIsi", isiSMS.text.toString())
                    return params
                }
            }
            postRequest.retryPolicy = DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT)
            queue.add(postRequest)
        }
    }
}
