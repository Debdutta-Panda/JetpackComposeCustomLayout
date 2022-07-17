package com.debduttapanda.customlayoutcolumn

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.debduttapanda.customlayoutcolumn.ui.theme.CustomLayoutColumnTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CustomLayoutColumnTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize()
                    ){
                        DoNothingLayout(
                            spacing = 20,
                            modifier = Modifier
                                .height(400.dp)
                            .background(Color.Red)
                            .padding(8.dp)
                        ) {
                            Text(
                                "MyBasicColumn",
                                modifier = Modifier.height(100.dp)
                            )
                            Text("places items")
                            Text("vertically.")
                            Text("We've done it by hand!")
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun DoNothingLayout(
    modifier: Modifier = Modifier,
    spacing: Int = 0,
    content: @Composable () -> Unit
) {
    Layout(
        modifier = modifier,
        content = content
    ) { measurables, constraints ->
        val looseConstraints = constraints.copy(
            minWidth = 0,
            minHeight = 0
        )
        layout(constraints.maxWidth, constraints.maxHeight) {
            var indent = 0
            var yCoord = 0
            val placeables = measurables.map { measurable ->
                measurable.measure(looseConstraints)
            }

            placeables.forEach { placeable ->
                placeable.placeRelative(x = indent, y = yCoord)
                indent += placeable.width + spacing
                yCoord += placeable.height + spacing
            }
        }
    }
}