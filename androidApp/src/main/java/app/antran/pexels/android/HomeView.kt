package app.antran.pexels.android

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FoodBank
import androidx.compose.material.icons.filled.Terrain
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.compose.material.Icon
import app.antran.pexels.android.github.GitHubRepositoryView

@Composable
fun HomeView() {
    val navController = rememberNavController()

    val bottomNavigationItems = listOf(
        BottomNavigationScreens.Counter,
        BottomNavigationScreens.GitHub,
    )
    Scaffold(
        bottomBar = {
            HomeAppBottomNavigation(navController, bottomNavigationItems)
        },
    ) {
        HomeNavigationConfigurations(navController)
    }
}


@Composable
private fun HomeNavigationConfigurations(
    navController: NavHostController
) {
    NavHost(
        navController,
        startDestination = BottomNavigationScreens.GitHub.route
    ) {
        composable(BottomNavigationScreens.Counter.route) {
            CounterView()
        }
        composable(BottomNavigationScreens.GitHub.route) {
            GitHubRepositoryView()
        }
    }
}

sealed class BottomNavigationScreens(val route: String, @StringRes val resourceId: Int, val icon: ImageVector) {
    object Counter : BottomNavigationScreens("Counter", R.string.counter_route, Icons.Filled.Terrain)
    object GitHub : BottomNavigationScreens("GitHub", R.string.github_route, Icons.Filled.FoodBank)
}

@Composable
private fun HomeAppBottomNavigation(
    navController: NavHostController,
    items: List<BottomNavigationScreens>
) {
    BottomNavigation {
        val currentRoute = navController.currentBackStackEntry?.destination?.route
        items.forEach { screen ->
            BottomNavigationItem(
                icon = { Icon(screen.icon, stringResource(id = screen.resourceId)) },
                label = { Text(stringResource(id = screen.resourceId)) },
                selected = currentRoute == screen.route,
                alwaysShowLabel = true, // This hides the title for the unselected items
                onClick = {
                    // This if check gives us a "singleTop" behavior where we do not create a
                    // second instance of the composable if we are already on that destination
                    if (currentRoute != screen.route) {
                        navController.navigate(screen.route)
                    }
                }
            )
        }
    }
}

@Composable
@Preview
fun PreviewHomeView() {
    MaterialTheme {
        HomeView()
    }
}