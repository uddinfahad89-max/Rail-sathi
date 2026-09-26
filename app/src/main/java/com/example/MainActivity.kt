package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ConfirmationNumber
import androidx.compose.material.icons.filled.GpsFixed
import androidx.compose.material.icons.filled.SmartToy
import androidx.compose.material.icons.filled.Train
import androidx.compose.material.icons.outlined.ConfirmationNumber
import androidx.compose.material.icons.outlined.GpsFixed
import androidx.compose.material.icons.outlined.SmartToy
import androidx.compose.material.icons.outlined.Train
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.data.repository.RailwayRepository
import com.example.ui.AppTab
import com.example.ui.MainViewModel
import com.example.ui.components.IstTopAppBar
import com.example.ui.screens.AiAssistantScreen
import com.example.ui.screens.LiveTrackingScreen
import com.example.ui.screens.PnrScreen
import com.example.ui.screens.TrainSearchScreen
import com.example.ui.theme.MyApplicationTheme
import com.example.ui.util.AppStrings

class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()
    private val repository = RailwayRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                val uiState by viewModel.uiState.collectAsStateWithLifecycle()
                val currentLang = uiState.currentLanguage

                BackHandler(enabled = uiState.currentTab != AppTab.TRAIN_SEARCH || uiState.showSplitDetailsDialog != null) {
                    if (uiState.showSplitDetailsDialog != null) {
                        viewModel.showSplitDetails(null)
                    } else {
                        viewModel.setTab(AppTab.TRAIN_SEARCH)
                    }
                }

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        IstTopAppBar(
                            currentIstTime = uiState.currentIstTime,
                            currentIstDate = uiState.currentIstDate,
                            currentLanguage = currentLang,
                            onLanguageChange = { viewModel.setLanguage(it) }
                        )
                    },
                    bottomBar = {
                        NavigationBar(
                            modifier = Modifier
                                .navigationBarsPadding()
                                .testTag("main_navigation_bar"),
                            tonalElevation = 8.dp
                        ) {
                            NavigationBarItem(
                                selected = uiState.currentTab == AppTab.TRAIN_SEARCH,
                                onClick = { viewModel.setTab(AppTab.TRAIN_SEARCH) },
                                icon = {
                                    Icon(
                                        imageVector = if (uiState.currentTab == AppTab.TRAIN_SEARCH)
                                            Icons.Filled.Train else Icons.Outlined.Train,
                                        contentDescription = AppStrings.tabSearch(currentLang)
                                    )
                                },
                                label = { Text(AppStrings.tabSearch(currentLang), fontSize = 11.sp) },
                                modifier = Modifier.testTag("nav_tab_search")
                            )

                            NavigationBarItem(
                                selected = uiState.currentTab == AppTab.PNR_STATUS,
                                onClick = { viewModel.setTab(AppTab.PNR_STATUS) },
                                icon = {
                                    Icon(
                                        imageVector = if (uiState.currentTab == AppTab.PNR_STATUS)
                                            Icons.Filled.ConfirmationNumber else Icons.Outlined.ConfirmationNumber,
                                        contentDescription = AppStrings.tabPnr(currentLang)
                                    )
                                },
                                label = { Text(AppStrings.tabPnr(currentLang), fontSize = 11.sp) },
                                modifier = Modifier.testTag("nav_tab_pnr")
                            )

                            NavigationBarItem(
                                selected = uiState.currentTab == AppTab.LIVE_TRACKING,
                                onClick = { viewModel.setTab(AppTab.LIVE_TRACKING) },
                                icon = {
                                    Icon(
                                        imageVector = if (uiState.currentTab == AppTab.LIVE_TRACKING)
                                            Icons.Filled.GpsFixed else Icons.Outlined.GpsFixed,
                                        contentDescription = AppStrings.tabLive(currentLang)
                                    )
                                },
                                label = { Text(AppStrings.tabLive(currentLang), fontSize = 11.sp) },
                                modifier = Modifier.testTag("nav_tab_live")
                            )

                            NavigationBarItem(
                                selected = uiState.currentTab == AppTab.AI_ASSISTANT,
                                onClick = { viewModel.setTab(AppTab.AI_ASSISTANT) },
                                icon = {
                                    Icon(
                                        imageVector = if (uiState.currentTab == AppTab.AI_ASSISTANT)
                                            Icons.Filled.SmartToy else Icons.Outlined.SmartToy,
                                        contentDescription = AppStrings.tabAi(currentLang)
                                    )
                                },
                                label = { Text(AppStrings.tabAi(currentLang), fontSize = 11.sp) },
                                modifier = Modifier.testTag("nav_tab_ai")
                            )
                        }
                    }
                ) { innerPadding ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    ) {
                        when (uiState.currentTab) {
                            AppTab.TRAIN_SEARCH -> {
                                TrainSearchScreen(
                                    currentLanguage = currentLang,
                                    sourceStation = uiState.sourceStation,
                                    destinationStation = uiState.destinationStation,
                                    journeyDate = uiState.journeyDate,
                                    allStations = repository.allStations,
                                    searchResults = uiState.searchResults,
                                    splitTicketResults = uiState.splitTicketResults,
                                    isSearching = uiState.isSearching,
                                    showSplitDetailsDialog = uiState.showSplitDetailsDialog,
                                    onSwapStations = { viewModel.swapStations() },
                                    onSelectSourceStation = { viewModel.setSourceStation(it) },
                                    onSelectDestinationStation = { viewModel.setDestinationStation(it) },
                                    onSelectJourneyDate = { viewModel.setJourneyDate(it) },
                                    onSearchClick = { viewModel.searchTrains() },
                                    onOpenSplitDetails = { viewModel.showSplitDetails(it) },
                                    onCloseSplitDetails = { viewModel.showSplitDetails(null) }
                                )
                            }
                            AppTab.PNR_STATUS -> {
                                PnrScreen(
                                    currentLanguage = currentLang,
                                    pnrInput = uiState.pnrInput,
                                    pnrDetails = uiState.pnrDetails,
                                    isLoading = uiState.isPnrLoading,
                                    errorMessage = uiState.pnrErrorMessage,
                                    onPnrInputChange = { viewModel.setPnrInput(it) },
                                    onCheckPnr = { viewModel.checkPnr(it) }
                                )
                            }
                            AppTab.LIVE_TRACKING -> {
                                LiveTrackingScreen(
                                    currentLanguage = currentLang,
                                    currentTrainNumber = uiState.trackedTrainNumber,
                                    liveStatus = uiState.liveTrainStatus,
                                    isLoading = uiState.isLiveStatusLoading,
                                    onSelectTrain = { viewModel.fetchLiveStatus(it) },
                                    onRefresh = { viewModel.fetchLiveStatus(uiState.trackedTrainNumber) }
                                )
                            }
                            AppTab.AI_ASSISTANT -> {
                                AiAssistantScreen(
                                    currentLanguage = currentLang,
                                    chatMessages = uiState.chatMessages,
                                    inputText = uiState.aiInputText,
                                    isThinking = uiState.isAiThinking,
                                    onInputChange = { viewModel.setAiInputText(it) },
                                    onSendMessage = { viewModel.sendAiMessage(it) }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
