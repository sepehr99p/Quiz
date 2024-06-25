package com.sep.quiz.ui.screen.questions.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.sep.quiz.ui.systemDesign.theme.Regular_14

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun QuestionsHeaderComponent(
    modifier: Modifier = Modifier,
    pagerState: PagerState,
    onCloseClicked: () -> Unit
) {
    Box(modifier = modifier.fillMaxWidth()) {
        Icon(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .clickable {
                    onCloseClicked.invoke()
                }, imageVector = Icons.Default.Close, contentDescription = "close"
        )

        Text(
            modifier = Modifier.align(Alignment.CenterStart),
            text = "${pagerState.currentPage} / ${pagerState.pageCount}",
            color = MaterialTheme.colorScheme.onPrimary,
            style = Regular_14
        )
    }
}