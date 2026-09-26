package com.example.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.model.*
import com.example.data.repository.ChatMessage
import com.example.data.repository.RailwayAiService
import com.example.data.repository.RailwayRepository
import com.example.ui.util.AppStrings
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

enum class AppTab {
    TRAIN_SEARCH,
    PNR_STATUS,
    LIVE_TRACKING,
    AI_ASSISTANT
}

data class UiState(
    val currentTab: AppTab = AppTab.TRAIN_SEARCH,
    val currentLanguage: AppLanguage = AppLanguage.BENGALI,
    val currentIstTime: String = IstTimeUtil.getCurrentIstTimeString(),
    val currentIstDate: String = IstTimeUtil.getCurrentIstDateString(),

    // Search state
    val sourceStation: Station,
    val destinationStation: Station,
    val journeyDate: String = "28 Sep 2026",
    val searchResults: List<Train> = emptyList(),
    val splitTicketResults: List<SplitTicketOption> = emptyList(),
    val isSearching: Boolean = false,
    val showSplitDetailsDialog: SplitTicketOption? = null,

    // PNR state
    val pnrInput: String = "6428190342",
    val pnrDetails: PnrDetails? = null,
    val isPnrLoading: Boolean = false,
    val pnrErrorMessage: String? = null,

    // Live tracking state
    val trackedTrainNumber: String = "12301",
    val liveTrainStatus: LiveTrainStatus? = null,
    val isLiveStatusLoading: Boolean = false,

    // AI Assistant state
    val chatMessages: List<ChatMessage> = listOf(
        ChatMessage(
            isUser = false,
            text = AppStrings.aiWelcomeMessage(AppLanguage.BENGALI)
        )
    ),
    val aiInputText: String = "",
    val isAiThinking: Boolean = false
)

class MainViewModel(
    private val repository: RailwayRepository = RailwayRepository(),
    private val aiService: RailwayAiService = RailwayAiService()
) : ViewModel() {

    private val defaultSource = repository.getStationByCode("HWH")
    private val defaultDest = repository.getStationByCode("NDLS")

    private val _uiState = MutableStateFlow(
        UiState(
            sourceStation = defaultSource,
            destinationStation = defaultDest
        )
    )
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    init {
        // Start IST ticker
        viewModelScope.launch {
            while (isActive) {
                _uiState.update {
                    it.copy(
                        currentIstTime = IstTimeUtil.getCurrentIstTimeString(),
                        currentIstDate = IstTimeUtil.getCurrentIstDateString(it.currentLanguage)
                    )
                }
                delay(1000)
            }
        }

        // Initialize default search
        searchTrains()
        // Initialize default PNR
        checkPnr("6428190342")
        // Initialize default live status
        fetchLiveStatus("12301")
    }

    fun setLanguage(language: AppLanguage) {
        _uiState.update { current ->
            // If only welcome message was present, update it to the new language
            val updatedMessages = if (current.chatMessages.size == 1 && !current.chatMessages.first().isUser) {
                listOf(ChatMessage(isUser = false, text = AppStrings.aiWelcomeMessage(language)))
            } else {
                current.chatMessages
            }
            current.copy(
                currentLanguage = language,
                chatMessages = updatedMessages
            )
        }
    }

    fun setTab(tab: AppTab) {
        _uiState.update { it.copy(currentTab = tab) }
    }

    fun swapStations() {
        _uiState.update {
            val oldSrc = it.sourceStation
            val oldDst = it.destinationStation
            it.copy(sourceStation = oldDst, destinationStation = oldSrc)
        }
        searchTrains()
    }

    fun setSourceStation(station: Station) {
        _uiState.update { it.copy(sourceStation = station) }
        searchTrains()
    }

    fun setDestinationStation(station: Station) {
        _uiState.update { it.copy(destinationStation = station) }
        searchTrains()
    }

    fun setJourneyDate(date: String) {
        _uiState.update { it.copy(journeyDate = date) }
        searchTrains()
    }

    fun searchTrains() {
        val src = _uiState.value.sourceStation.code
        val dst = _uiState.value.destinationStation.code
        val date = _uiState.value.journeyDate

        _uiState.update { it.copy(isSearching = true) }

        viewModelScope.launch {
            delay(200)
            val trains = repository.getTrainsBetween(src, dst)
            val splits = repository.calculateSplitTickets(src, dst, date)
            _uiState.update {
                it.copy(
                    searchResults = trains,
                    splitTicketResults = splits,
                    isSearching = false
                )
            }
        }
    }

    fun showSplitDetails(option: SplitTicketOption?) {
        _uiState.update { it.copy(showSplitDetailsDialog = option) }
    }

    fun setPnrInput(input: String) {
        _uiState.update { it.copy(pnrInput = input, pnrErrorMessage = null) }
    }

    fun checkPnr(pnrToQuery: String? = null) {
        val pnr = pnrToQuery ?: _uiState.value.pnrInput
        val clean = pnr.trim().replace("-", "").replace(" ", "")

        val lang = _uiState.value.currentLanguage
        if (clean.length != 10 || !clean.all { it.isDigit() }) {
            val err = when (lang) {
                AppLanguage.BENGALI -> "দয়া করে একটি সঠিক ১০ সংখ্যার পিএনআর (PNR) নম্বর লিখুন।"
                AppLanguage.HINDI -> "कृपया एक मान्य 10 अंकों का पीएनआर (PNR) नंबर दर्ज करें।"
                AppLanguage.ENGLISH -> "Please enter a valid 10-digit PNR number."
            }
            _uiState.update {
                it.copy(pnrErrorMessage = err)
            }
            return
        }

        _uiState.update { it.copy(isPnrLoading = true, pnrErrorMessage = null, pnrInput = clean) }

        viewModelScope.launch {
            delay(250)
            val details = repository.checkPnrStatus(clean)
            _uiState.update {
                it.copy(
                    pnrDetails = details,
                    isPnrLoading = false
                )
            }
        }
    }

    fun fetchLiveStatus(trainNumber: String) {
        _uiState.update { it.copy(isLiveStatusLoading = true, trackedTrainNumber = trainNumber) }
        viewModelScope.launch {
            delay(250)
            val status = repository.getLiveRunningStatus(trainNumber)
            _uiState.update {
                it.copy(
                    liveTrainStatus = status,
                    isLiveStatusLoading = false
                )
            }
        }
    }

    fun setAiInputText(text: String) {
        _uiState.update { it.copy(aiInputText = text) }
    }

    fun sendAiMessage(customText: String? = null) {
        val prompt = customText ?: _uiState.value.aiInputText
        if (prompt.isBlank()) return

        val userMessage = ChatMessage(isUser = true, text = prompt.trim())
        val updatedHistory = _uiState.value.chatMessages + userMessage
        val currentLang = _uiState.value.currentLanguage

        _uiState.update {
            it.copy(
                chatMessages = updatedHistory,
                aiInputText = "",
                isAiThinking = true
            )
        }

        viewModelScope.launch {
            val responseText = aiService.getAiResponse(prompt, updatedHistory, currentLang)
            val aiMessage = ChatMessage(isUser = false, text = responseText)
            _uiState.update {
                it.copy(
                    chatMessages = it.chatMessages + aiMessage,
                    isAiThinking = false
                )
            }
        }
    }
}
