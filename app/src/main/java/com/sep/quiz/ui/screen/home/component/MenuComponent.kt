package com.sep.quiz.ui.screen.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.sep.quiz.R
import com.sep.quiz.ui.designSystem.theme.Bold_20
import com.sep.quiz.ui.designSystem.theme.dimen.corner_8
import com.sep.quiz.ui.designSystem.theme.dimen.padding_16

@Composable
internal fun MenuComponent(
    modifier: Modifier = Modifier,
    score: State<Int>,
    onStartClick: () -> Unit,
    onAboutClick: () -> Unit
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.padding(padding_16),
            text = "Score : ${score.value}",
            style = Bold_20,
            color = MaterialTheme.colorScheme.onPrimary,
        )
        MenuComponentItem(title = stringResource(id = R.string.start), onClick = onStartClick)
        MenuComponentItem(title = stringResource(id = R.string.about), onClick = onAboutClick)
    }
}

@Composable
private fun MenuComponentItem(modifier: Modifier = Modifier, title: String, onClick: () -> Unit) {
    Text(
        modifier = modifier
            .fillMaxWidth()
            .padding(padding_16)
            .clip(RoundedCornerShape(corner_8))
            .background(color = MaterialTheme.colorScheme.primaryContainer)
            .clickable { onClick.invoke() }
            .padding(padding_16),
        text = title,
        style = Bold_20,
        color = MaterialTheme.colorScheme.onPrimary,
        textAlign = TextAlign.Center
    )
}

@Preview
@Composable
private fun MenuComponentItemPreview(modifier: Modifier = Modifier) {
    MenuComponentItem(title = "Title") {}
}

@Preview
@Composable
private fun MenuComponentPreview() {
    val score = remember { mutableIntStateOf(30) }
    MenuComponent(onAboutClick = {}, onStartClick = {}, score = score)
}