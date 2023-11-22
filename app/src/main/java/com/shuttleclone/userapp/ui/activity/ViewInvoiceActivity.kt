package com.shuttleclone.userapp.ui.activity

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.shuttleclone.userapp.R
import com.shuttleclone.userapp.utils.Constants.API_BASE_URL
import com.shuttleclone.userapp.utils.LocaleManager
import com.shuttleclone.userapp.utils.mylog
//import com.github.barteksc.pdfviewer.PDFView


class ViewInvoiceActivity : AppCompatActivity() {

    var ivBack: ImageView? = null
//    var pdfView: PDFView? = null
    private val TAG="ViewInvoiceActivity"

    var url="$API_BASE_URL/users/invoice/"

    override fun onResume() {
        super.onResume()
        LocaleManager().setLocale(this) //This method update the language when the activity orientation changes
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LocaleManager().setLocale(this) //This method update the language when the activity orientation changes
        setContentView(R.layout.activity_viwe_invoice)
        ivBack=findViewById(R.id.ivBack)
        ivBack!!.setOnClickListener { finish() }

//        pdfView = findViewById(R.id.idPDFView)


        try {
            if (intent != null) {
               val pnrNo = intent.getStringExtra("pnrNo").toString()
                mylog(TAG, "onCreate: pnrNo=$pnrNo")
                url=url+pnrNo
            }
        } catch (e: Exception) {
            mylog(TAG, "onCreate: error=${e.localizedMessage}")
        }

//        loadInvoice(url)

    }

    /*private fun loadInvoice(url: String) {
        try {
            val executor: ExecutorService = Executors.newSingleThreadExecutor()
            val handler = Handler(Looper.getMainLooper())

            LoadingDialog.showLoadingDialog(this,"Loading....")
            executor.execute {
                var inputStream: InputStream? = null
                try {
                    val url = URL(url)
                    val urlConnection = url.openConnection() as HttpURLConnection
                    if (urlConnection.getResponseCode() === 200) {
                        inputStream = BufferedInputStream(urlConnection.getInputStream())
                    }
                } catch (e: IOException) {
                    e.printStackTrace()
                }
                handler.post {
                    pdfView!!.fromStream(inputStream).load()
                    LoadingDialog.cancelLoading()
                }
            }
        }catch (e:Exception){
            mylog(TAG, "loadInvoice: Error=${e.localizedMessage}")}
    }*/

}