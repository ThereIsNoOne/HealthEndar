package com.slas.healthendar.ui.navigation

import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController

data class NavItem(
    val title: String,
    val selectedIcon: ImageVector,
    val defaultIcon: ImageVector,
    val count: Int? = null
)

@Composable
fun TabView(items: List<NavItem>, navController: NavController) {
    var selectedIndex by rememberSaveable {
        mutableIntStateOf(0)
    }

    NavigationBar {
        items.forEachIndexed { index, navItem ->
            NavigationBarItem(
                selected = index == selectedIndex,
                onClick = {
                    selectedIndex = index
                    navController.navigate(navItem.title)
                },
                icon = { TabBarIconView(item = navItem, selected = index == selectedIndex) },
                label = { Text(navItem.title) }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TabBarIconView(item: NavItem, selected: Boolean) {
    BadgedBox(badge = { BadgeCount(item.count) }) {
        Icon(
            imageVector = if (selected) item.selectedIcon else item.defaultIcon,
            contentDescription = item.title
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BadgeCount(count: Int?) {
    if (count == null) {
        return
    }
    Badge {
        Text(count.toString())
    }
}
