package com.dionep.jcompose

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Icon
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.loadImageResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.ui.tooling.preview.Preview
import com.dionep.jcompose.ui.JComposeTheme
import com.dionep.jcompose.ui.typography

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JComposeTheme {
                Surface(color = MaterialTheme.colors.background) {
                    initView()
                }
            }
        }
    }


}

@Composable
fun initView() {
    MaterialTheme {
        Scaffold(
                topBar = { toolbar() },
                bodyContent = { content() }
        )
    }
}

@Composable
fun toolbar() {
    TopAppBar(
            title = { Text(text = "JCompose") },
            navigationIcon = {
                IconButton(onClick = {}) {
                    Icon(Icons.Filled.Menu)
                }
            },
            backgroundColor = Color.Blue,
            contentColor = Color.White,
            elevation = 12.dp
    )
}

@Composable
fun content() {
    ScrollableColumn {
        Column(
                modifier = Modifier.padding(16.dp)
        ) {
            loadImageResource(id = R.drawable.header).resource.resource?.let {
                Image(
                        it,
                        modifier =
                            Modifier.preferredHeight(180.dp)
                                    .fillMaxWidth()
                                    .clip(RoundedCornerShape(16.dp)),
                        contentScale = ContentScale.Crop
                )
            }
            Text(
                    text = "First compose layout",
                    modifier = Modifier.padding(top = 16.dp),
                    style = typography.h6
            )
            Text(text = "Looks like Flatter?", style = typography.body2)
            Text(text = "It's ok >:", style = typography.body2)

            LazyColumnFor(
                    items = (1..15).map { Item("Title $it", "Description") }
            ) { item ->
                Row(modifier = Modifier.padding(16.dp).fillParentMaxWidth()) {
                    Card(
                            shape = RoundedCornerShape(16.dp),
                            backgroundColor = Color.Magenta,
                            modifier = Modifier.fillParentMaxWidth()
                    ) {
                        Column {
                            Text(
                                    text = item.title,
                                    style = typography.h5,
                                    modifier = Modifier.padding(12.dp)
                            )
                            Text(
                                    text = item.description,
                                    style = typography.caption,
                                    modifier = Modifier.padding(12.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JComposeTheme {
        initView()
    }
}

data class Item(
        val title: String,
        val description: String
)