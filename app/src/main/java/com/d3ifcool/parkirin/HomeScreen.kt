package com.d3ifcool.parkirin


import ParkingViewModel
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.d3ifcool.parkirin.ui.theme.BiruB
import com.d3ifcool.parkirin.ui.theme.BiruJ
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun HomeScreen(navController: NavController, viewModel: ParkingViewModel = viewModel()) {

    val isDark = isSystemInDarkTheme()
    val backgroundColor = if (isDark) Color(0xFF121212) else Color.White

    val jumlah = 5
    val tersedia by viewModel.jumlahSlot.collectAsState()
    val statusList by viewModel.daftarSlot.collectAsState()


    fun getStatus(id: String): String {
        return statusList.find { it.id == id }?.status ?: "Unknown"
    }

    Scaffold(
        containerColor = backgroundColor,
        topBar = {
            TopBar(title = stringResource(R.string.app_name))
        }
    ) { innerPadding ->

        BoxWithConstraints(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            val screenWidth = maxWidth

            val cardWidth = 320.dp
            val cardHeight = 280.dp
            val smallBoxSize = 120.dp
            val slotSize = 100.dp
            val legendWidth = 150.dp
            val legendHeight = 50.dp
            val topSpacing = 24.dp
            val midSpacing = 34.dp
            val smallSpacing = 13.dp

            val titleFontSize = 45.sp
            val labelFontSize = 20.sp
            val numberFontSize = 30.sp
            val slotFontSize = 32.sp
            val legendFontSize = 28.sp

            val scrollState = rememberScrollState()
            val cardShape = RoundedCornerShape(16.dp)
            val slotShape = RoundedCornerShape(5.dp)
            val circleShape = RoundedCornerShape(20.dp)

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState)
                    .drawBehind {
                        val canvasWidth = size.width
                        val yGaris1 = (308.dp + 15.dp).toPx()
                        val yGaris2 = (435.dp + 15.dp).toPx()
                        val yGaris3 = (520.dp + 15.dp).toPx()
                        val yGarisVertikal_bawah = (yGaris3.toDp() + 130.dp).toPx()

                        val startX = canvasWidth * 0.05f
                        val endX = canvasWidth * 0.95f
                        val midX = canvasWidth * 0.7f

                        drawLine(color = BiruJ, start = Offset(startX, yGaris1), end = Offset(endX, yGaris1), strokeWidth = 4f)
                        drawLine(color = BiruJ, start = Offset(startX, yGaris2), end = Offset(endX, yGaris2), strokeWidth = 4f)
                        drawLine(color = BiruJ, start = Offset(startX, yGaris3), end = Offset(midX, yGaris3), strokeWidth = 4f)
                        drawLine(color = BiruJ, start = Offset(midX, yGarisVertikal_bawah), end = Offset(midX, yGaris3), strokeWidth = 4f)
                        drawLine(color = BiruJ, start = Offset(endX, yGarisVertikal_bawah), end = Offset(endX, yGaris2), strokeWidth = 4f)
                    },
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Spacer(modifier = Modifier.height(topSpacing))

                Box(
                    modifier = Modifier
                        .size(width = cardWidth, height = cardHeight)
                        .shadow(elevation = 10.dp, shape = cardShape)
                        .clip(cardShape)
                        .background(color = BiruJ, shape = cardShape),
                    contentAlignment = Alignment.TopCenter
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Spacer(modifier = Modifier.height(15.dp))
                        Text(
                            text = stringResource(R.string.home_parking_slot_title),
                            fontSize = titleFontSize,
                            color = Color.White
                        )
                        Spacer(modifier = Modifier.height(40.dp))

                        Row {
                            InfoBox(
                                label = stringResource(R.string.home_label_total),
                                value = jumlah.toString(),
                                size = smallBoxSize,
                                shape = cardShape,
                                labelSize = labelFontSize,
                                numberSize = numberFontSize
                            )

                            Spacer(modifier = Modifier.width(20.dp))

                            InfoBox(
                                label = stringResource(R.string.home_parking_slot_title), // Sesuaikan string resource
                                value = tersedia.toString(),
                                size = smallBoxSize,
                                shape = cardShape,
                                labelSize = labelFontSize,
                                numberSize = numberFontSize
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(midSpacing))

                Row {
                    // A1 -> Slot 1
                    ParkingSlotCard(
                        slotName = "A1",
                        status = getStatus("slot_1"),
                        size = slotSize,
                        shape = slotShape,
                        fontSize = slotFontSize
                    )
                    Spacer(modifier = Modifier.width(smallSpacing))

                    // A2 -> Slot 2
                    ParkingSlotCard(
                        slotName = "A2",
                        status = getStatus("slot_2"),
                        size = slotSize,
                        shape = slotShape,
                        fontSize = slotFontSize
                    )
                    Spacer(modifier = Modifier.width(smallSpacing))

                    // A3 -> Slot 3
                    ParkingSlotCard(
                        slotName = "A3",
                        status = getStatus("slot_3"),
                        size = slotSize,
                        shape = slotShape,
                        fontSize = slotFontSize
                    )
                }

                Spacer(modifier = Modifier.height(midSpacing))

                Row {
                    repeat(5) {
                        Text(text = "<", fontSize = slotFontSize, color = Color.Gray)
                        if (it < 4) Spacer(modifier = Modifier.width(45.dp))
                    }
                }

                Spacer(modifier = Modifier.height(45.dp))

                Row {
                    Row {
                        ParkingSlotCard(
                            slotName = "A4",
                            status = getStatus("slot_4"),
                            size = slotSize,
                            shape = slotShape,
                            fontSize = slotFontSize
                        )
                        Spacer(modifier = Modifier.width(smallSpacing))

                        ParkingSlotCard(
                            slotName = "A5",
                            status = getStatus("slot_5"),
                            size = slotSize,
                            shape = slotShape,
                            fontSize = slotFontSize
                        )
                    }
                    Spacer(modifier = Modifier.width(75.dp))

                    Column(horizontalAlignment = Alignment.Start) {
                        repeat(2) {
                            Text(text = "^", fontSize = slotFontSize, color = Color.Gray)
                            if (it < 1) Spacer(modifier = Modifier.height(25.dp))
                        }
                    }
                    Spacer(modifier = Modifier.width(screenWidth + 45.dp))
                }

                Spacer(modifier = Modifier.height(25.dp))

                Row {
                    LegendCard(text = "Full", color = Color.Gray.copy(alpha = 0.59f), width = legendWidth, height = legendHeight, shape = cardShape, circleShape = circleShape, fontSize = legendFontSize)
                    Spacer(modifier = Modifier.width(20.dp))
                    LegendCard(text = "Free", color = BiruJ.copy(alpha = 0.59f), width = legendWidth, height = legendHeight, shape = cardShape, circleShape = circleShape, fontSize = legendFontSize)
                }

                Spacer(modifier = Modifier.height(30.dp))
            }
        }
    }
}

@Composable
fun InfoBox(label: String, value: String, size: Dp, shape: Shape, labelSize: TextUnit, numberSize: TextUnit) {
    Box(
        modifier = Modifier
            .size(size)
            .shadow(elevation = 10.dp, shape = shape)
            .clip(shape)
            .background(BiruJ, shape = shape)
            .background(color = BiruB.copy(alpha = 0.59f), shape = shape),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Spacer(modifier = Modifier.height(18.dp))
            Text(text = label, fontSize = labelSize, color = Color.White)
            Spacer(modifier = Modifier.height(15.dp))
            Text(text = value, fontSize = numberSize, color = Color.White)
        }
    }
}

@Composable
fun LegendCard(text: String, color: Color, width: Dp, height: Dp, shape: Shape, circleShape: Shape, fontSize: TextUnit) {
    Box(
        modifier = Modifier
            .size(width = width, height = height)
            .shadow(elevation = 10.dp, shape = shape)
            .background(Color.White, shape = circleShape)
            .background(color = BiruJ, shape = shape),
        contentAlignment = Alignment.Center
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(20.dp)
                    .shadow(elevation = 10.dp, shape = circleShape)
                    .background(Color.White, shape = circleShape)
                    .background(color = color, shape = circleShape)
            ) {}
            Spacer(modifier = Modifier.width(10.dp))
            Text(text = text, fontSize = fontSize, color = Color.White)
        }
    }
}

@Preview
@Composable
fun HomePreview() {
    val navController = rememberNavController()

    HomeScreen(
        navController = navController
    )
}