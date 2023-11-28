package org.example.cmd

import android.content.res.Configuration
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.example.ui.component.*
import org.example.ui.icon.NiaIcons
import org.example.ui.theme.NiaTheme


/**
 * Multipreview annotation that represents light and dark themes. Add this annotation to a
 * composable to render the both themes.
 */
@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO, name = "Light theme")
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, name = "Dark theme")
annotation class ThemePreviews

@OptIn(ExperimentalMaterial3Api::class)
@Preview("Top App Bar")
@Composable
private fun NiaTopAppBarPreview() {
    NiaTopAppBar(
        title = stringResource(android.R.string.untitled),
        navigationIcon = NiaIcons.Search,
        navigationIconContentDescription = "Navigation icon",
        actionIcon = NiaIcons.MoreVert,
        actionIconContentDescription = "Action icon",
    )
}


@ThemePreviews
@Composable
fun ViewToggleExpandedPreview() {
    NiaTheme {
        Surface {
            NiaViewToggleButton(
                expanded = true,
                onExpandedChange = { },
                compactText = { Text(text = "Compact view") },
                expandedText = { Text(text = "Expanded view") },
            )
        }
    }
}

@ThemePreviews
@Composable
fun ViewToggleCompactPreview() {
    NiaTheme {
        Surface {
            NiaViewToggleButton(
                expanded = false,
                onExpandedChange = { },
                compactText = { Text(text = "Compact view") },
                expandedText = { Text(text = "Expanded view") },
            )
        }
    }
}

@ThemePreviews
@Composable
fun BackgroundDefault() {
    NiaTheme(disableDynamicTheming = true) {
        NiaBackground(Modifier.size(100.dp), content = {})
    }
}

@ThemePreviews
@Composable
fun BackgroundDynamic() {
    NiaTheme(disableDynamicTheming = false) {
        NiaBackground(Modifier.size(100.dp), content = {})
    }
}

@ThemePreviews
@Composable
fun BackgroundAndroid() {
    NiaTheme(androidTheme = true) {
        NiaBackground(Modifier.size(100.dp), content = {})
    }
}

@ThemePreviews
@Composable
fun GradientBackgroundDefault() {
    NiaTheme(disableDynamicTheming = true) {
        NiaGradientBackground(Modifier.size(100.dp), content = {})
    }
}

@ThemePreviews
@Composable
fun GradientBackgroundDynamic() {
    NiaTheme(disableDynamicTheming = false) {
        NiaGradientBackground(Modifier.size(100.dp), content = {})
    }
}

@ThemePreviews
@Composable
fun GradientBackgroundAndroid() {
    NiaTheme(androidTheme = true) {
        NiaGradientBackground(Modifier.size(100.dp), content = {})
    }
}


@ThemePreviews
@Composable
fun TabsPreview() {
    NiaTheme {
        val titles = listOf("Topics", "People")
        NiaTabRow(selectedTabIndex = 0) {
            titles.forEachIndexed { index, title ->
                NiaTab(
                    selected = index == 0,
                    onClick = { },
                    text = { Text(text = title) },
                )
            }
        }
    }
}

@ThemePreviews
@Composable
fun ChipPreview() {
    NiaTheme {
        NiaBackground(modifier = Modifier.size(80.dp, 20.dp)) {
            NiaFilterChip(selected = true, onSelectedChange = {}) {
                Text("Chip")
            }
        }
    }
}

@ThemePreviews
@Composable
fun TagPreview() {
    NiaTheme {
        NiaTopicTag(followed = true, onClick = {}) {
            Text("Topic".uppercase())
        }
    }
}

@ThemePreviews
@Composable
fun IconButtonPreview() {
    NiaTheme {
        NiaIconToggleButton(
            checked = true,
            onCheckedChange = { },
            icon = {
                Icon(
                    imageVector = NiaIcons.BookmarkBorder,
                    contentDescription = null,
                )
            },
            checkedIcon = {
                Icon(
                    imageVector = NiaIcons.Bookmark,
                    contentDescription = null,
                )
            },
        )
    }
}

@ThemePreviews
@Composable
fun IconButtonPreviewUnchecked() {
    NiaTheme {
        NiaIconToggleButton(
            checked = false,
            onCheckedChange = { },
            icon = {
                Icon(
                    imageVector = NiaIcons.BookmarkBorder,
                    contentDescription = null,
                )
            },
            checkedIcon = {
                Icon(
                    imageVector = NiaIcons.Bookmark,
                    contentDescription = null,
                )
            },
        )
    }
}

@ThemePreviews
@Composable
fun NiaLoadingWheelPreview() {
    NiaTheme {
        Surface {
            NiaLoadingWheel(contentDesc = "LoadingWheel")
        }
    }
}

@ThemePreviews
@Composable
fun NiaOverlayLoadingWheelPreview() {
    NiaTheme {
        Surface {
            NiaOverlayLoadingWheel(contentDesc = "LoadingWheel")
        }
    }
}


@ThemePreviews
@Composable
fun NiaNavigationPreview() {
    val items = listOf("For you", "Saved", "Interests")
    val icons = listOf(
        NiaIcons.UpcomingBorder,
        NiaIcons.BookmarksBorder,
        NiaIcons.Grid3x3,
    )
    val selectedIcons = listOf(
        NiaIcons.Upcoming,
        NiaIcons.Bookmarks,
        NiaIcons.Grid3x3,
    )

    NiaTheme {
        NiaNavigationBar {
            items.forEachIndexed { index, item ->
                NiaNavigationBarItem(
                    icon = {
                        Icon(
                            imageVector = icons[index],
                            contentDescription = item,
                        )
                    },
                    selectedIcon = {
                        Icon(
                            imageVector = selectedIcons[index],
                            contentDescription = item,
                        )
                    },
                    label = { Text(item) },
                    selected = index == 0,
                    onClick = { },
                )
            }
        }
    }
}


@ThemePreviews
@Composable
fun NiaButtonPreview() {
    NiaTheme {
        NiaBackground(modifier = Modifier.size(150.dp, 50.dp)) {
            NiaButton(onClick = {}, text = { Text("Test button") })
        }
    }
}

@ThemePreviews
@Composable
fun NiaOutlinedButtonPreview() {
    NiaTheme() {
        NiaBackground(modifier = Modifier.size(150.dp, 50.dp)) {
            NiaOutlinedButton(onClick = {}, text = { Text("Test button") })
        }
    }
}

@ThemePreviews
@Composable
fun NiaButtonPreview2() {
    NiaTheme {
        NiaBackground(modifier = Modifier.size(150.dp, 50.dp)) {
            NiaButton(onClick = {}, text = { Text("Test button") })
        }
    }
}

@ThemePreviews
@Composable
fun NiaButtonLeadingIconPreview() {
    NiaTheme {
        NiaBackground(modifier = Modifier.size(150.dp, 50.dp)) {
            NiaButton(
                onClick = {},
                text = { Text("Test button") },
                leadingIcon = { Icon(imageVector = NiaIcons.Add, contentDescription = null) },
            )
        }
    }
}