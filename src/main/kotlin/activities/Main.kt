package activities

import activities.components.*
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.*
import activities.theme.blackBackground

@OptIn(ExperimentalMaterialApi::class)
@Composable
@Preview
fun app() {

  val buttonMenuItems = listOf("Explore", "Productivity", "Development", "Games", "Art & Design")
  val programsMock = listOf("postman", "Spotify", "Gimp", "Inkscape", "VSCode", "Slack")
  val iconProgramMock = listOf("postman.png", "spotify.svg", "gimp.png", "Inkscape.png", "vscode.png", "slack.png")
  var textSearch by remember { mutableStateOf("") }

  var showCategoriesOptions by remember { mutableStateOf(true) }
  var showSearchTextField by remember { mutableStateOf(false) }

  MaterialTheme {
    Surface(color = blackBackground) {
      Box(Modifier.fillMaxSize()) {
        Row {
          navigationRail(
            showCategoriesOptions = showCategoriesOptions,
            showSearchTextField = showSearchTextField,
            onShowCategoriesOptionsChanged = { newValue -> showCategoriesOptions = newValue },
            onShowSearchTextFieldChanged = { newValue -> showSearchTextField = newValue }
          )

          Column {
            Row(
              modifier = Modifier.fillMaxWidth().padding(5.dp),
              horizontalArrangement = Arrangement.SpaceBetween,
              verticalAlignment = Alignment.CenterVertically
            ) {
              if (showCategoriesOptions) {
                LazyRow(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(start = 10.dp)) {
                  buttonMenuItems.forEach { button ->
                    item {
                      topMenuCategoryItem(text = button)
                      Spacer(Modifier.width(5.dp))
                    }
                  }
                }
              }
              if (showSearchTextField) searchBar(textSearch) { newText -> textSearch = newText }
            }

            Banner().home()

            Spacer(Modifier.height(10.dp))

            Text(
              "Explore os mais baixados!",
              color = Color.White,
              modifier = Modifier.fillMaxWidth().padding(top = 5.dp, bottom = 10.dp),
              textAlign = TextAlign.Center,
              fontWeight = FontWeight.Bold
            )
            Box(modifier = Modifier.padding(start = 10.dp, end = 10.dp)) {
              LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                content = {
                  for (i in programsMock.indices) {
                    item {
                      verticalListProgramsItems(programsMock = programsMock[i], iconProgramMock = iconProgramMock[i])
                    }
                  }
                }
              )
            }
          }
        }
      }
    }
  }
}


fun main() = application {
  Window(
    onCloseRequest = ::exitApplication,
    title = "UbuntuStore",
    alwaysOnTop = false,
    state =
    WindowState(
      width = 700.dp,
      height = 600.dp,
      placement = WindowPlacement.Maximized,
      position = WindowPosition(Alignment.Center)
    ),
  ) {
    app()
  }
}
