package com.example.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.*
import com.example.data.repository.StationDatabase
import com.example.ui.components.SplitTicketDetailDialog
import com.example.ui.components.StationPickerDialog
import com.example.ui.theme.*
import com.example.ui.util.AppStrings
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import java.util.TimeZone

@Composable
fun TrainSearchScreen(
    currentLanguage: AppLanguage,
    sourceStation: Station,
    destinationStation: Station,
    journeyDate: String,
    allStations: List<Station>,
    searchResults: List<Train>,
    splitTicketResults: List<SplitTicketOption>,
    isSearching: Boolean,
    showSplitDetailsDialog: SplitTicketOption?,
    onSwapStations: () -> Unit,
    onSelectSourceStation: (Station) -> Unit,
    onSelectDestinationStation: (Station) -> Unit,
    onSelectJourneyDate: (String) -> Unit,
    onSearchClick: () -> Unit,
    onOpenSplitDetails: (SplitTicketOption) -> Unit,
    onCloseSplitDetails: () -> Unit
) {
    var showSourcePicker by remember { mutableStateOf(false) }
    var showDestPicker by remember { mutableStateOf(false) }
    var showDatePicker by remember { mutableStateOf(false) }

    val pickerTitleFrom = when (currentLanguage) {
        AppLanguage.BENGALI -> "প্রারম্ভিক স্টেশন নির্বাচন করুন"
        AppLanguage.HINDI -> "प्रारंभिक स्टेशन चुनें"
        AppLanguage.ENGLISH -> "Select Origin Station"
    }

    val pickerTitleTo = when (currentLanguage) {
        AppLanguage.BENGALI -> "গন্তব্য স্টেশন নির্বাচন করুন"
        AppLanguage.HINDI -> "गंतव्य स्टेशन चुनें"
        AppLanguage.ENGLISH -> "Select Destination Station"
    }

    if (showSourcePicker) {
        StationPickerDialog(
            title = pickerTitleFrom,
            currentLanguage = currentLanguage,
            stations = allStations,
            onSelectStation = {
                onSelectSourceStation(it)
                showSourcePicker = false
            },
            onDismiss = { showSourcePicker = false }
        )
    }

    if (showDestPicker) {
        StationPickerDialog(
            title = pickerTitleTo,
            currentLanguage = currentLanguage,
            stations = allStations,
            onSelectStation = {
                onSelectDestinationStation(it)
                showDestPicker = false
            },
            onDismiss = { showDestPicker = false }
        )
    }

    if (showSplitDetailsDialog != null) {
        SplitTicketDetailDialog(
            splitOption = showSplitDetailsDialog,
            currentLanguage = currentLanguage,
            onDismiss = onCloseSplitDetails
        )
    }

    if (showDatePicker) {
        JourneyDatePickerDialog(
            currentLanguage = currentLanguage,
            onDateSelected = {
                onSelectJourneyDate(it)
                showDatePicker = false
            },
            onDismiss = { showDatePicker = false }
        )
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .testTag("train_search_screen"),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Search Input Card
        item {
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Text(
                        text = AppStrings.searchHeader(currentLanguage),
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Bold,
                            color = RailBluePrimary
                        )
                    )

                    // Stations Row
                    Box(modifier = Modifier.fillMaxWidth()) {
                        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                            // Source Picker
                            StationField(
                                label = AppStrings.fromLabel(currentLanguage),
                                station = sourceStation,
                                currentLanguage = currentLanguage,
                                icon = Icons.Default.TripOrigin,
                                iconTint = RailBluePrimary,
                                tag = "source_station_field",
                                onClick = { showSourcePicker = true }
                            )

                            // Destination Picker
                            StationField(
                                label = AppStrings.toLabel(currentLanguage),
                                station = destinationStation,
                                currentLanguage = currentLanguage,
                                icon = Icons.Default.LocationOn,
                                iconTint = RailSecondaryOrange,
                                tag = "dest_station_field",
                                onClick = { showDestPicker = true }
                            )
                        }

                        // Swap Button in Center
                        IconButton(
                            onClick = onSwapStations,
                            modifier = Modifier
                                .align(Alignment.CenterEnd)
                                .offset(x = (-8).dp)
                                .clip(RoundedCornerShape(20.dp))
                                .background(MaterialTheme.colorScheme.surfaceVariant)
                                .border(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.5f), RoundedCornerShape(20.dp))
                                .testTag("swap_stations_button")
                        ) {
                            Icon(
                                imageVector = Icons.Default.SwapVert,
                                contentDescription = "স্টেশন অদলবদল",
                                tint = RailBluePrimary
                            )
                        }
                    }

                    // Quick Popular Stations Row
                    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                        Text(
                            text = AppStrings.popularStationsLabel(currentLanguage),
                            style = MaterialTheme.typography.labelSmall.copy(
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                fontWeight = FontWeight.SemiBold
                            )
                        )
                        val popularCodes = listOf("HWH", "NDLS", "SDAH", "CSMT", "KOAA", "PNBE", "MAS", "SBC", "GHY")
                        LazyRow(
                            horizontalArrangement = Arrangement.spacedBy(6.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            items(popularCodes) { code ->
                                val stn = StationDatabase.getStationByCode(code) ?: allStations.firstOrNull { it.code == code }
                                if (stn != null) {
                                    SuggestionChip(
                                        onClick = {
                                            if (sourceStation.code == code) {
                                                onSwapStations()
                                            } else {
                                                onSelectDestinationStation(stn)
                                            }
                                        },
                                        label = {
                                            Text(
                                                text = "${stn.code} - ${stn.getName(currentLanguage)}",
                                                fontSize = 11.sp
                                            )
                                        },
                                        shape = RoundedCornerShape(14.dp),
                                        colors = SuggestionChipDefaults.suggestionChipColors(
                                            containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.6f)
                                        )
                                    )
                                }
                            }
                        }
                    }

                    // Journey Dates Section with Calendar and Quick Chips
                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = AppStrings.journeyDate(currentLanguage),
                                style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.SemiBold)
                            )
                            TextButton(
                                onClick = { showDatePicker = true },
                                contentPadding = PaddingValues(horizontal = 8.dp, vertical = 2.dp),
                                modifier = Modifier.testTag("open_calendar_button")
                            ) {
                                Icon(
                                    imageVector = Icons.Default.DateRange,
                                    contentDescription = null,
                                    modifier = Modifier.size(16.dp),
                                    tint = RailBluePrimary
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(
                                    text = AppStrings.pickFromCalendar(currentLanguage),
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = RailBluePrimary
                                )
                            }
                        }

                        // Selected Date Card (Interactive)
                        Surface(
                            shape = RoundedCornerShape(10.dp),
                            color = RailBluePrimary.copy(alpha = 0.08f),
                            border = BorderStroke(1.dp, RailBluePrimary.copy(alpha = 0.3f)),
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { showDatePicker = true }
                                .testTag("selected_date_banner")
                        ) {
                            Row(
                                modifier = Modifier.padding(horizontal = 12.dp, vertical = 10.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.CalendarMonth,
                                        contentDescription = null,
                                        tint = RailBluePrimary
                                    )
                                    Column {
                                        Text(
                                            text = if (currentLanguage == AppLanguage.BENGALI) "নির্বাচিত তারিখ:"
                                            else if (currentLanguage == AppLanguage.HINDI) "चयनित तारीख:"
                                            else "Selected Date:",
                                            style = MaterialTheme.typography.labelSmall.copy(color = MaterialTheme.colorScheme.onSurfaceVariant)
                                        )
                                        Text(
                                            text = formatJourneyDateDisplay(journeyDate, currentLanguage),
                                            style = MaterialTheme.typography.bodyMedium.copy(
                                                fontWeight = FontWeight.Bold,
                                                color = RailBluePrimary
                                            )
                                        )
                                    }
                                }
                                Surface(
                                    shape = RoundedCornerShape(8.dp),
                                    color = RailBluePrimary
                                ) {
                                    Text(
                                        text = if (currentLanguage == AppLanguage.BENGALI) "তারিখ বাছুন 📅"
                                        else if (currentLanguage == AppLanguage.HINDI) "तारीख बदलें 📅"
                                        else "Change Date 📅",
                                        color = Color.White,
                                        fontSize = 11.sp,
                                        fontWeight = FontWeight.Bold,
                                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                                    )
                                }
                            }
                        }

                        // Quick Relative Date Selection Chips
                        val quickDateOptions = remember {
                            val cal = Calendar.getInstance(TimeZone.getTimeZone("Asia/Kolkata"))
                            val sdf = SimpleDateFormat("dd MMM yyyy", Locale.US).apply {
                                timeZone = TimeZone.getTimeZone("Asia/Kolkata")
                            }
                            val dayMonthSdf = SimpleDateFormat("dd MMM", Locale.US).apply {
                                timeZone = TimeZone.getTimeZone("Asia/Kolkata")
                            }

                            val d0 = sdf.format(cal.time)
                            val dm0 = dayMonthSdf.format(cal.time)

                            cal.add(Calendar.DAY_OF_YEAR, 1)
                            val d1 = sdf.format(cal.time)
                            val dm1 = dayMonthSdf.format(cal.time)

                            cal.add(Calendar.DAY_OF_YEAR, 1)
                            val d2 = sdf.format(cal.time)
                            val dm2 = dayMonthSdf.format(cal.time)

                            listOf(
                                Triple(d0, "আজ ($dm0)", "आज ($dm0)"),
                                Triple(d1, "আগামীকাল ($dm1)", "कल ($dm1)"),
                                Triple(d2, "পরশু ($dm2)", "परसों ($dm2)")
                            )
                        }

                        LazyRow(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            items(quickDateOptions) { (dateStr, labelBn, labelHi) ->
                                val isSelected = journeyDate == dateStr
                                val label = when (currentLanguage) {
                                    AppLanguage.BENGALI -> labelBn.replace("Sep", "সেপ্টে").replace("Oct", "অক্টো")
                                    AppLanguage.HINDI -> labelHi.replace("Sep", "सितं").replace("Oct", "अक्तू")
                                    AppLanguage.ENGLISH -> if (dateStr == quickDateOptions[0].first) "Today (${dateStr.substring(0, 6)})"
                                    else if (dateStr == quickDateOptions[1].first) "Tomorrow (${dateStr.substring(0, 6)})"
                                    else "Day After (${dateStr.substring(0, 6)})"
                                }
                                FilterChip(
                                    selected = isSelected,
                                    onClick = { onSelectJourneyDate(dateStr) },
                                    label = {
                                        Text(
                                            text = label,
                                            fontSize = 12.sp,
                                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                                        )
                                    },
                                    colors = FilterChipDefaults.filterChipColors(
                                        selectedContainerColor = RailBluePrimary,
                                        selectedLabelColor = Color.White
                                    ),
                                    modifier = Modifier.testTag("date_chip_$dateStr")
                                )
                            }

                            item {
                                FilterChip(
                                    selected = false,
                                    onClick = { showDatePicker = true },
                                    label = {
                                        Row(
                                            verticalAlignment = Alignment.CenterVertically,
                                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                                        ) {
                                            Icon(Icons.Default.EditCalendar, contentDescription = null, modifier = Modifier.size(14.dp))
                                            Text(
                                                text = if (currentLanguage == AppLanguage.BENGALI) "অন্য তারিখ..."
                                                else if (currentLanguage == AppLanguage.HINDI) "अन्य तारीख..."
                                                else "Other Date...",
                                                fontSize = 12.sp
                                            )
                                        }
                                    },
                                    modifier = Modifier.testTag("date_chip_other")
                                )
                            }
                        }
                    }

                    // Search Button
                    Button(
                        onClick = onSearchClick,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp)
                            .testTag("search_trains_button"),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = RailBluePrimary)
                    ) {
                        if (isSearching) {
                            CircularProgressIndicator(
                                modifier = Modifier.size(20.dp),
                                color = Color.White,
                                strokeWidth = 2.dp
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(AppStrings.searching(currentLanguage))
                        } else {
                            Icon(imageVector = Icons.Default.Search, contentDescription = null)
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(AppStrings.searchButton(currentLanguage), fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
        }

        // Split-Ticket / Seat Switching Highlight Section
        if (splitTicketResults.isNotEmpty()) {
            item {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Surface(
                        shape = RoundedCornerShape(12.dp),
                        color = RailSecondaryOrange.copy(alpha = 0.12f),
                        border = BorderStroke(1.dp, RailSecondaryOrange.copy(alpha = 0.4f)),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(
                            modifier = Modifier.padding(12.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.ElectricBolt,
                                contentDescription = null,
                                tint = RailSecondaryOrange
                            )
                            Column {
                                Text(
                                    text = AppStrings.splitBannerTitle(currentLanguage),
                                    style = MaterialTheme.typography.titleSmall.copy(
                                        fontWeight = FontWeight.Bold,
                                        color = RailSecondaryOrange
                                    )
                                )
                                Text(
                                    text = AppStrings.splitBannerSubtitle(currentLanguage),
                                    style = MaterialTheme.typography.bodySmall.copy(
                                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                                        fontSize = 12.sp
                                    )
                                )
                            }
                        }
                    }

                    splitTicketResults.forEach { splitOption ->
                        SplitTicketCard(
                            option = splitOption,
                            currentLanguage = currentLanguage,
                            onViewDetails = { onOpenSplitDetails(splitOption) }
                        )
                    }
                }
            }
        }

        // Direct Trains Header with Route and Date Context
        item {
            Column(
                modifier = Modifier.padding(top = 8.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = AppStrings.directTrainsHeader(currentLanguage, searchResults.size),
                    style = MaterialTheme.typography.titleSmall.copy(
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                )
                Surface(
                    shape = RoundedCornerShape(6.dp),
                    color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.6f)
                ) {
                    Text(
                        text = "📅 ${formatJourneyDateDisplay(journeyDate, currentLanguage)}  •  ${sourceStation.getName(currentLanguage)} (${sourceStation.code}) ➔ ${destinationStation.getName(currentLanguage)} (${destinationStation.code})",
                        style = MaterialTheme.typography.labelSmall.copy(
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            fontWeight = FontWeight.Medium
                        ),
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }
            }
        }

        // Direct Train Cards
        items(searchResults) { train ->
            TrainResultCard(train = train, currentLanguage = currentLanguage)
        }
    }
}

@Composable
fun StationField(
    label: String,
    station: Station,
    currentLanguage: AppLanguage,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    iconTint: Color,
    tag: String,
    onClick: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)),
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .testTag(tag)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Icon(imageVector = icon, contentDescription = null, tint = iconTint)
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = label,
                    style = MaterialTheme.typography.labelSmall.copy(color = MaterialTheme.colorScheme.onSurfaceVariant)
                )
                Text(
                    text = "${station.getName(currentLanguage)} (${station.code})",
                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold)
                )
            }
            Surface(
                shape = RoundedCornerShape(8.dp),
                color = RailBluePrimary.copy(alpha = 0.12f)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "লিখুন বা খুঁজুন",
                        tint = RailBluePrimary,
                        modifier = Modifier.size(13.dp)
                    )
                    Text(
                        text = if (currentLanguage == AppLanguage.BENGALI) "লিখুন/খুঁজুন"
                        else if (currentLanguage == AppLanguage.HINDI) "लिखें/खोजें"
                        else "Type/Search",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = RailBluePrimary
                    )
                }
            }
        }
    }
}

@Composable
fun SplitTicketCard(
    option: SplitTicketOption,
    currentLanguage: AppLanguage,
    onViewDetails: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = Modifier
            .fillMaxWidth()
            .border(1.5.dp, RailConfirmedGreen.copy(alpha = 0.5f), RoundedCornerShape(14.dp))
            .clickable(onClick = onViewDetails)
            .testTag("split_card_${option.id}")
    ) {
        Column(
            modifier = Modifier.padding(14.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    shape = RoundedCornerShape(6.dp),
                    color = RailConfirmedGreenContainer
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.CheckCircle,
                            contentDescription = null,
                            tint = RailConfirmedGreen,
                            modifier = Modifier.size(14.dp)
                        )
                        Text(
                            text = AppStrings.confirmedSeatBadge(currentLanguage),
                            style = MaterialTheme.typography.labelSmall.copy(
                                fontWeight = FontWeight.Bold,
                                color = RailConfirmedGreen
                            )
                        )
                    }
                }

                Text(
                    text = "${AppStrings.totalFare(currentLanguage)} ₹${option.totalFare}",
                    style = MaterialTheme.typography.titleSmall.copy(
                        fontWeight = FontWeight.Bold,
                        color = RailBluePrimary
                    )
                )
            }

            Text(
                text = option.getTrainName(currentLanguage),
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold)
            )

            // Segment visualization
            Surface(
                shape = RoundedCornerShape(8.dp),
                color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.6f),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(10.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    val part1 = when (currentLanguage) {
                        AppLanguage.BENGALI -> "১ম অংশ"
                        AppLanguage.HINDI -> "1ला भाग"
                        AppLanguage.ENGLISH -> "Part 1"
                    }
                    val part2 = when (currentLanguage) {
                        AppLanguage.BENGALI -> "২য় অংশ"
                        AppLanguage.HINDI -> "2रा भाग"
                        AppLanguage.ENGLISH -> "Part 2"
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "$part1: ${option.segment1.fromStation.code} ➜ ${option.segment1.toStation.code}",
                            style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.SemiBold)
                        )
                        Text(
                            text = "${option.segment1.trainClass.code} (CNF)",
                            style = MaterialTheme.typography.labelSmall.copy(
                                fontWeight = FontWeight.Bold,
                                color = RailConfirmedGreen
                            )
                        )
                    }
                    Text(
                        text = "${option.segment1.getSeatInfo(currentLanguage)} • ${option.segment1.departureTimeIst}",
                        style = MaterialTheme.typography.labelSmall.copy(color = MaterialTheme.colorScheme.onSurfaceVariant)
                    )

                    HorizontalDivider(modifier = Modifier.padding(vertical = 2.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "$part2: ${option.segment2.fromStation.code} ➜ ${option.segment2.toStation.code}",
                            style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.SemiBold)
                        )
                        Text(
                            text = "${option.segment2.trainClass.code} (CNF)",
                            style = MaterialTheme.typography.labelSmall.copy(
                                fontWeight = FontWeight.Bold,
                                color = RailConfirmedGreen
                            )
                        )
                    }
                    Text(
                        text = "${option.segment2.getSeatInfo(currentLanguage)} • ${option.segment2.arrivalTimeIst}",
                        style = MaterialTheme.typography.labelSmall.copy(color = MaterialTheme.colorScheme.onSurfaceVariant)
                    )
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "${option.intermediateStation.getName(currentLanguage)} (${option.intermediateStation.code})",
                    style = MaterialTheme.typography.labelSmall.copy(color = RailSecondaryOrange, fontWeight = FontWeight.Medium)
                )

                TextButton(
                    onClick = onViewDetails,
                    contentPadding = PaddingValues(0.dp)
                ) {
                    Text(AppStrings.viewDetails(currentLanguage), color = RailBluePrimary, fontWeight = FontWeight.Bold, fontSize = 12.sp)
                }
            }
        }
    }
}

@Composable
fun TrainResultCard(train: Train, currentLanguage: AppLanguage) {
    Card(
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = Modifier
            .fillMaxWidth()
            .testTag("train_card_${train.number}")
    ) {
        Column(
            modifier = Modifier.padding(14.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "${train.number} - ${train.getName(currentLanguage)}",
                        style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold)
                    )
                    Text(
                        text = "${train.type} • ${train.getRunsOn(currentLanguage).joinToString(", ")}",
                        style = MaterialTheme.typography.labelSmall.copy(color = MaterialTheme.colorScheme.onSurfaceVariant)
                    )
                }
            }

            // Timings in IST
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = train.departureTimeIst,
                        style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold, color = RailBluePrimary)
                    )
                    Text(
                        text = "${train.departureStation.getName(currentLanguage)} (${train.departureStation.code})",
                        style = MaterialTheme.typography.labelSmall.copy(color = MaterialTheme.colorScheme.onSurfaceVariant)
                    )
                }

                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = MaterialTheme.colorScheme.surfaceVariant
                ) {
                    Text(
                        text = train.getDuration(currentLanguage),
                        style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.SemiBold),
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }

                Column(horizontalAlignment = Alignment.End) {
                    Text(
                        text = train.arrivalTimeIst,
                        style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold, color = RailBluePrimary)
                    )
                    Text(
                        text = "${train.arrivalStation.getName(currentLanguage)} (${train.arrivalStation.code})",
                        style = MaterialTheme.typography.labelSmall.copy(color = MaterialTheme.colorScheme.onSurfaceVariant)
                    )
                }
            }

            HorizontalDivider(color = MaterialTheme.colorScheme.outline.copy(alpha = 0.2f))

            // Class Availabilities Row
            Text(
                text = if (currentLanguage == AppLanguage.BENGALI) "শ্রেণি ও সিটের বর্তমান স্থিতি (সরাসরি):"
                else if (currentLanguage == AppLanguage.HINDI) "श्रेणी एवं सीट की वर्तमान स्थिति (सीधा):"
                else "Class & Quota Availability (Direct):",
                style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.SemiBold)
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                train.classAvailabilities.forEach { avail ->
                    val isAvailable = avail.isAvailable
                    val bg = if (isAvailable) RailConfirmedGreenContainer else RailWaitlistRedContainer
                    val textColor = if (isAvailable) RailConfirmedGreen else RailWaitlistRed

                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        color = bg,
                        modifier = Modifier.weight(1f)
                    ) {
                        Column(
                            modifier = Modifier.padding(6.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(2.dp)
                        ) {
                            Text(
                                text = avail.trainClass.code,
                                style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold)
                            )
                            Text(
                                text = avail.statusText,
                                style = MaterialTheme.typography.labelSmall.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = textColor,
                                    fontSize = 10.sp
                                )
                            )
                            Text(
                                text = "₹${avail.fare}",
                                style = MaterialTheme.typography.labelSmall.copy(
                                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                                    fontSize = 10.sp
                                )
                            )
                        }
                    }
                }
            }
        }
    }
}

fun formatJourneyDateDisplay(dateStr: String, lang: AppLanguage): String {
    return when (lang) {
        AppLanguage.BENGALI -> {
            dateStr
                .replace("Jan", "জানুয়ারি")
                .replace("Feb", "ফেব্রুয়ারি")
                .replace("Mar", "মার্চ")
                .replace("Apr", "এপ্রিল")
                .replace("May", "মে")
                .replace("Jun", "জুন")
                .replace("Jul", "জুলাই")
                .replace("Aug", "আগস্ট")
                .replace("Sep", "সেপ্টেম্বর")
                .replace("Oct", "অক্টোবর")
                .replace("Nov", "নভেম্বর")
                .replace("Dec", "ডিসেম্বর")
        }
        AppLanguage.HINDI -> {
            dateStr
                .replace("Jan", "जनवरी")
                .replace("Feb", "फरवरी")
                .replace("Mar", "मार्च")
                .replace("Apr", "अप्रैल")
                .replace("May", "मई")
                .replace("Jun", "जून")
                .replace("Jul", "जुलाई")
                .replace("Aug", "अगस्त")
                .replace("Sep", "सितंबर")
                .replace("Oct", "अक्टूबर")
                .replace("Nov", "नवंबर")
                .replace("Dec", "दिसंबर")
        }
        AppLanguage.ENGLISH -> dateStr
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JourneyDatePickerDialog(
    currentLanguage: AppLanguage,
    onDateSelected: (String) -> Unit,
    onDismiss: () -> Unit
) {
    val datePickerState = rememberDatePickerState(
        initialSelectedDateMillis = System.currentTimeMillis()
    )

    DatePickerDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(
                onClick = {
                    val selectedMillis = datePickerState.selectedDateMillis
                    if (selectedMillis != null) {
                        val cal = Calendar.getInstance(TimeZone.getTimeZone("UTC")).apply {
                            timeInMillis = selectedMillis
                        }
                        val sdf = SimpleDateFormat("dd MMM yyyy", Locale.US).apply {
                            timeZone = TimeZone.getTimeZone("UTC")
                        }
                        onDateSelected(sdf.format(cal.time))
                    }
                    onDismiss()
                },
                modifier = Modifier.testTag("confirm_journey_date_button")
            ) {
                Text(
                    text = when (currentLanguage) {
                        AppLanguage.BENGALI -> "নিশ্চিত করুন"
                        AppLanguage.HINDI -> "पुष्टि करें"
                        AppLanguage.ENGLISH -> "Confirm"
                    },
                    fontWeight = FontWeight.Bold,
                    color = RailBluePrimary
                )
            }
        },
        dismissButton = {
            TextButton(
                onClick = onDismiss,
                modifier = Modifier.testTag("dismiss_journey_date_button")
            ) {
                Text(
                    text = when (currentLanguage) {
                        AppLanguage.BENGALI -> "বাতিল"
                        AppLanguage.HINDI -> "रद्द करें"
                        AppLanguage.ENGLISH -> "Cancel"
                    }
                )
            }
        }
    ) {
        DatePicker(state = datePickerState)
    }
}
