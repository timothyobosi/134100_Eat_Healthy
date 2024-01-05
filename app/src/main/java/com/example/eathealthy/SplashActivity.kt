package com.example.eathealthy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.Toast
import com.example.eathealthy.R
import com.example.eathealthy.database.RecipeDatabase
import com.example.eathealthy.entities.Category
import com.example.eathealthy.interfaces.GetDataService
import com.example.eathealthy.retofitclient.RetrofitClientInstance
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import pub.devrel.easypermissions.AppSettingsDialog
import pub.devrel.easypermissions.EasyPermissions
import retrofit2.Call
import retrofit2.Response
import java.util.Locale
import javax.security.auth.callback.Callback


class SplashActivity : BaseActivity(), EasyPermissions.RationaleCallbacks , EasyPermissions.PermissionCallbacks{

    private var READ_STORAGE_PEM = 123

    // Declare loader variable
    private lateinit var loader: ProgressBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        //create variable
        val btnGetStarted: Button = findViewById(R.id.btnGetStarted)

        readStorageTask()

        btnGetStarted.setOnClickListener{
            var intent = Intent(this@SplashActivity,HomeActivity::class.java)
            startActivity(intent)
            finish()
        }

        // Initialize loader after setContentView
        loader = findViewById(R.id.loader)
    }
    // to create category to get data from api using retrofit
    fun getCategories(){
        val service = RetrofitClientInstance.retrofitInstance.create(GetDataService::class.java)
        val call = service.getCategoryList()
        call.enqueue(object : retrofit2.Callback<Category>{


            override fun onFailure(call: Call<Category>, t: Throwable) {

                loader.visibility = View.INVISIBLE
                Toast.makeText(this@SplashActivity,"Something went wrong", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(
                call: Call<Category>,
                response: Response<Category>
            ) {

                insertDataIntoRoomDb(response.body())
            }


        })
    }

    fun insertDataIntoRoomDb(category:Category?){

        //create variable
        val btnGetStarted: Button = findViewById(R.id.btnGetStarted)

        launch {
            this.let {
                RecipeDatabase.getDatabase(this@SplashActivity).recipeDao().clearDb()
                for (arr in category!!.categorieitems!! ){
                    RecipeDatabase.getDatabase(this@SplashActivity)
                        .recipeDao().insertCategory(arr)

                }
                btnGetStarted.visibility = View.VISIBLE
            }
        }
    }

    private fun hasReadStoragePermission():Boolean{

        return EasyPermissions.hasPermissions(this,android.Manifest.permission.READ_EXTERNAL_STORAGE)

    }

    override fun onRationaleAccepted(requestCode: Int) {
        TODO("Not yet implemented")
    }

    private fun readStorageTask(){
        if(hasReadStoragePermission()) {
            getCategories()

        }else{
            EasyPermissions.requestPermissions(
                this,
                "This app needs access to your storage",
                READ_STORAGE_PEM,
                android.Manifest.permission.READ_EXTERNAL_STORAGE
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults,this)
    }

    override fun onRationaleDenied(requestCode: Int) {

    }

    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {

    }

    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)){
            AppSettingsDialog.Builder(this).build().show()
        }
    }
}