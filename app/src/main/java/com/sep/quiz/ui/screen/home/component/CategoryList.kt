package com.sep.quiz.ui.screen.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import com.sep.quiz.domain.entiry.CategoryEntity
import com.sep.quiz.ui.systemDesign.theme.Bold_14
import com.sep.quiz.ui.systemDesign.theme.Bold_18
import com.sep.quiz.ui.systemDesign.theme.dimen.corner_16
import com.sep.quiz.ui.systemDesign.theme.dimen.padding_8

@Composable
fun CategoryList(modifier: Modifier = Modifier, categories: List<CategoryEntity>) {
    LazyColumn(modifier = modifier) {
        items(categories) {
            CategoryListItem(categoryEntity = it)
        }
    }
}

@Composable
private fun CategoryListItem(modifier: Modifier = Modifier, categoryEntity: CategoryEntity) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(padding_8)
            .clip(RoundedCornerShape(corner_16))
            .background(color = MaterialTheme.colorScheme.primaryContainer)
    ) {
        Text(
            modifier = Modifier.align(Alignment.Center).padding(vertical = padding_8),
            text = categoryEntity.name,
            style = Bold_14,
            color = MaterialTheme.colorScheme.onPrimaryContainer
        )
    }
}

@Preview
@Composable
private fun CategoryListItemPreview(modifier: Modifier = Modifier) {
    CategoryListItem(categoryEntity = CategoryEntity(id = "id", name = "name"))
}

@Preview
@Composable
private fun CategoryListPreview(modifier: Modifier = Modifier) {
    CategoryList(categories = listOf())
}