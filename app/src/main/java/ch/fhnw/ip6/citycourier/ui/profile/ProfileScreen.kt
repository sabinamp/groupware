package ch.fhnw.ip6.citycourier.ui.profile

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.compose.unaryPlus
import androidx.ui.core.Clip
import androidx.ui.layout.Size
import androidx.ui.core.dp
import androidx.ui.core.setContent
import androidx.ui.foundation.DrawImage
import androidx.ui.foundation.shape.corner.CircleShape
import androidx.ui.layout.Container
import androidx.ui.res.imageResource
import androidx.ui.tooling.preview.Preview
import ch.fhnw.ip6.citycourier.R

class ProfileScreen : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
                setContent {

        }
    }
}


@Composable
fun Picture(){
    val image = +imageResource(R.drawable.ic_profile_60)
    Container(modifier = Size(130.dp, 130.dp)) {
        Clip(shape= CircleShape){
            DrawImage(image = image)
        }
    }
}

@Composable
fun ProfileInfo(){


}


@Preview
@Composable
fun DefaultPreview() {
    Picture()
    ProfileInfo()

}