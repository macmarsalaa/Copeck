package com.macmarsala.copeck.presentation.screen.menu_test

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.macmarsala.copeck.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuTestScreen(
    menuTestViewModel: MenuTestViewModel = hiltViewModel(),
    onNavigationClick: () -> Unit = {}
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val uiState by menuTestViewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            LargeTopAppBar(
                title = { Text(stringResource(R.string.menu_test_headline)) },
                navigationIcon = {
                    IconButton(onClick = { onNavigationClick() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = null
                        )
                    }
                }
            )
        },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { paddingValues ->
        Column(
            Modifier
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
        ) {
            Button(onClick = { menuTestViewModel.showSnackbar() }) {
                Text(text = stringResource(R.string.menu_test_snackbar))
            }

            Button(onClick = { menuTestViewModel.showToast() }) {
                Text(text = stringResource(R.string.menu_test_toast))
            }
        }
    }

    if (uiState.showSnackbar) {
        LaunchedEffect(snackbarHostState) {
            snackbarHostState.showSnackbar(message = "Пример сообщения в Snackbar.")
            menuTestViewModel.hideSnackbar()
        }
    }

    if (uiState.showToast) {
        Toast.makeText(LocalContext.current, "Пример сообщения в Toast.", Toast.LENGTH_SHORT).show()
        menuTestViewModel.hideToast()
    }
}
