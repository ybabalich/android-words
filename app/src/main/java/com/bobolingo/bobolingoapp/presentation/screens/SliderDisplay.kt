@file:OptIn(ExperimentalFoundationApi::class)

package com.bobolingo.bobolingoapp.presentation.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bobolingo.bobolingoapp.R
import com.bobolingo.bobolingoapp.data.SliderItem
import com.bobolingo.bobolingoapp.presentation.views.DotsIndicator
import com.bobolingo.bobolingoapp.ui.theme.IndicatorSelectedColor
import com.bobolingo.bobolingoapp.ui.theme.TextColor
import com.bobolingo.bobolingoapp.ui.theme.TitleTextColor
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.xml.datatype.Duration

val images = mutableListOf(
    SliderItem(
        image = R.drawable.ic_slider_1,
        title = "Welcome to\nBobolingo",
        text = "Unlock English fluency: your journey starts here!"
    ),
    SliderItem(
        R.drawable.ic_slider_2,
        "Memorize words",
        "Practice speaking, listening, pronunciation and other types of games."
    ),
    SliderItem(
        R.drawable.ic_slider_3,
        "Learn 20 \nnew words a day",
        "Pimp up your vocabulary, A proven way to quickly learn new foreign words."
    ),
    SliderItem(
        R.drawable.ic_slider_4,
        "Create your own \ndictionary",
        "Add words while reading books, watching movies or studying"
    ),
)


@Preview
@Composable
fun SliderDisplay(
    modifier: Modifier = Modifier
) {


    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        var isClicked by remember { mutableStateOf(false) }

        Image(
            painterResource(R.drawable.ic_logo_slider),
            modifier = Modifier.weight(0.5f),
            contentDescription = ""
        )

        val pagerState = rememberPagerState(
            initialPageOffsetFraction = 0f
        ) {
            images.size
        }


        HorizontalPager(
            modifier = Modifier
                .fillMaxWidth()
                .weight(3f),
            state = pagerState,
            key = { images[it] }) { index ->
            Column() {
                Image(
                    painterResource(images[index].image),
                    contentDescription = "",
                    modifier = Modifier.run {
                        fillMaxWidth()
                            .height(134.dp)
                    }
                )

                Text(
                    text = images[index].title,
                    color = TitleTextColor,
                    textAlign = TextAlign.Center,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(0.dp, 40.dp)
                )

                Text(
                    text = images[index].text,
                    color = TextColor,
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(20.dp, 0.dp, 20.dp, 0.dp)
                )
            }
        }

        DotsIndicator(
            modifier = Modifier
                .height(36.dp)
                .weight(1f),
            totalDots = images.size,
            selectedIndex = pagerState.targetPage,
            dotSize = 8.dp
        )

        Box(
            modifier = Modifier
                .height(56.dp)
                .fillMaxWidth()
                .padding(16.dp, 0.dp)
        ) {
            FilledTonalButton(
                onClick = {
                    isClicked = true
                },
                shape = RoundedCornerShape(16),
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Center),
                colors = ButtonDefaults.buttonColors(containerColor = IndicatorSelectedColor)
            ) {
                Text(
                    text = if (pagerState.targetPage == images.size - 1) "Finish" else "Continue",
                    textAlign = TextAlign.Center,
                    fontSize = 16.sp
                )
            }
        }

        Box(
            modifier = Modifier
                .height(20.dp)
        )

        var key: Boolean by remember { mutableStateOf(false) }


        if (isClicked) {
            isClicked = false
            LaunchedEffect(key1 = key) {
                launch {
                    with(pagerState) {
                        val target = if (currentPage < pageCount - 1) currentPage + 1 else 0
                        scrollToPage(page = target)
                        key = !key
                    }
                }
            }
        }
    }
}