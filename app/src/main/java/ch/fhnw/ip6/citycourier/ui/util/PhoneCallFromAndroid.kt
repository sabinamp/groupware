package ch.fhnw.ip6.citycourier.ui.util

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import ch.fhnw.ip6.citycourier.ui.MainActivity

val TELEPHONE_SCHEMA = "tel:"
val PRESERVED_CHARACTER = "+"

val PHONE_NUMBER = "0041435417077"

fun makePhoneCall(context: Context) : Boolean {
    try {
        //Step 1: Define the phone call uri
        //val phoneCallUri = Uri.parse(TELEPHONE_SCHEMA + PRESERVED_CHARACTER + PHONE_NUMBER)

       // Step 2: Set Intent action to `ACTION_DIAL`
       /* val phoneCallIntent = Intent(Intent.ACTION_DIAL).also{
            // Step 3. Set phone call uri to Intent data
            it.setData(phoneCallUri)
        }*/
        // Step 4: Pass the Intent to System to start any <Activity> which can accept `ACTION_DIAL`
        //startActivity(context,phoneCallIntent, null)
        val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$PHONE_NUMBER"))
        startActivity(context, intent, null)
        return true
    } catch (e: Exception) {
        e.printStackTrace()
        return false
    }
}


fun checkPermissionAndCall(context: Context, activity: Activity) {

    if (ContextCompat.checkSelfPermission(context,
            Manifest.permission.CALL_PHONE)
        != PackageManager.PERMISSION_GRANTED) {

        // Permission is not granted
        // Should we show an explanation?
        if (ActivityCompat.shouldShowRequestPermissionRationale(activity,
                Manifest.permission.CALL_PHONE)) {
            Toast.makeText(context, "Please grant My App permission to call.", Toast.LENGTH_SHORT).show()
            // Show an explanation to the user *asynchronously* -- don't block
            // this thread waiting for the user's response! After the user
            // sees the explanation, try again to request the permission.
        } else {
            // No explanation needed, we can request the permission.
            ActivityCompat.requestPermissions(activity,
                arrayOf(Manifest.permission.CALL_PHONE),
                42)
        }
    } else {
        // Permission has already been granted
        Toast.makeText(context, "Calling My App now ...", Toast.LENGTH_SHORT).show()
        makePhoneCall(context)
    }
}