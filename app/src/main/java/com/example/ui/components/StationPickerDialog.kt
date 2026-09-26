package com.example.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddLocation
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.data.model.AppLanguage
import com.example.data.model.Station
import com.example.data.repository.StationDatabase
import com.example.ui.theme.RailBluePrimary

@Composable
fun StationPickerDialog(
    title: String,
    currentLanguage: AppLanguage,
    stations: List<Station>,
    onSelectStation: (Station) -> Unit,
    onDismiss: () -> Unit
) {
    var searchQuery by remember { mutableStateOf("") }

    val filteredStations = remember(searchQuery, stations, currentLanguage) {
        if (searchQuery.isBlank()) stations
        else {
            val q = searchQuery.trim().lowercase()
            stations.filter {
                it.code.lowercase().contains(q) ||
                it.nameBn.lowercase().contains(q) ||
                it.nameHi.lowercase().contains(q) ||
                it.nameEn.lowercase().contains(q) ||
                it.cityBn.lowercase().contains(q) ||
                it.cityHi.lowercase().contains(q) ||
                it.cityEn.lowercase().contains(q) ||
                it.state.lowercase().contains(q)
            }
        }
    }

    val placeholderText = when (currentLanguage) {
        AppLanguage.BENGALI -> "স্টেশনের নাম বা কোড খুঁজুন (যেমন HWH, NDLS, KOAA...)"
        AppLanguage.HINDI -> "स्टेशन का नाम या कोड खोजें (जैसे HWH, NDLS, KOAA...)"
        AppLanguage.ENGLISH -> "Search station name or code (e.g. HWH, NDLS...)"
    }

    val countLabel = when (currentLanguage) {
        AppLanguage.BENGALI -> "অল-ইন্ডিয়া মাস্টার ডাটাবেস: ${filteredStations.size} টি স্টেশন"
        AppLanguage.HINDI -> "अखिल भारतीय मास्टर डेटाबेस: ${filteredStations.size} स्टेशन"
        AppLanguage.ENGLISH -> "All-India Master Database: ${filteredStations.size} stations"
    }

    Dialog(onDismissRequest = onDismiss) {
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = MaterialTheme.colorScheme.surface,
            tonalElevation = 6.dp,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.88f)
                .testTag("station_picker_dialog")
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                // Header
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = title,
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.Bold,
                                color = RailBluePrimary
                            )
                        )
                        Text(
                            text = countLabel,
                            style = MaterialTheme.typography.labelSmall.copy(
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        )
                    }
                    IconButton(
                        onClick = onDismiss,
                        modifier = Modifier.testTag("station_picker_close")
                    ) {
                        Icon(imageVector = Icons.Default.Close, contentDescription = "বন্ধ করুন")
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                // Search Box
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    placeholder = { Text(placeholderText, fontSize = 13.sp) },
                    leadingIcon = {
                        Icon(imageVector = Icons.Default.Search, contentDescription = "অনুসন্ধান", tint = RailBluePrimary)
                    },
                    trailingIcon = {
                        if (searchQuery.isNotEmpty()) {
                            IconButton(onClick = { searchQuery = "" }) {
                                Icon(Icons.Default.Close, contentDescription = "Clear")
                            }
                        }
                    },
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("station_picker_search_input"),
                    shape = RoundedCornerShape(12.dp)
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Popular Stations Quick Chips
                val popularCodes = listOf("HWH", "NDLS", "SDAH", "CSMT", "KOAA", "PNBE", "MAS", "SBC", "GHY", "PURI")
                androidx.compose.foundation.lazy.LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    items(popularCodes) { code ->
                        val stn = StationDatabase.getStationByCode(code) ?: stations.firstOrNull { it.code == code }
                        if (stn != null) {
                            SuggestionChip(
                                onClick = { onSelectStation(stn) },
                                label = {
                                    Text(
                                        text = "${stn.code} - ${stn.getName(currentLanguage)}",
                                        fontSize = 11.sp,
                                        fontWeight = FontWeight.SemiBold
                                    )
                                },
                                shape = RoundedCornerShape(16.dp),
                                colors = SuggestionChipDefaults.suggestionChipColors(
                                    containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.6f)
                                )
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Station list
                LazyColumn(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    // If user typed a query that is not directly present, offer custom selection
                    val cleanQ = searchQuery.trim()
                    val exactFound = filteredStations.any { it.code.equals(cleanQ, ignoreCase = true) }
                    if (cleanQ.length >= 2 && !exactFound) {
                        item {
                            Card(
                                colors = CardDefaults.cardColors(
                                    containerColor = RailBluePrimary.copy(alpha = 0.12f)
                                ),
                                shape = RoundedCornerShape(10.dp),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        onSelectStation(StationDatabase.getOrSynthesize(cleanQ))
                                    }
                            ) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(12.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.AddLocation,
                                        contentDescription = null,
                                        tint = RailBluePrimary
                                    )
                                    Column(modifier = Modifier.weight(1f)) {
                                        Text(
                                            text = when (currentLanguage) {
                                                AppLanguage.BENGALI -> "কাস্টম স্টেশন: ${cleanQ.uppercase()} গ্রহণ করুন"
                                                AppLanguage.HINDI -> "कस्टम स्टेशन: ${cleanQ.uppercase()} चुनें"
                                                AppLanguage.ENGLISH -> "Use custom station: ${cleanQ.uppercase()}"
                                            },
                                            style = MaterialTheme.typography.bodyMedium.copy(
                                                fontWeight = FontWeight.Bold,
                                                color = RailBluePrimary
                                            )
                                        )
                                        Text(
                                            text = when (currentLanguage) {
                                                AppLanguage.BENGALI -> "যেকোনো ভারতীয় স্টেশন কোড বা নাম হিসাবে বুকিং করুন"
                                                AppLanguage.HINDI -> "किसी भी भारतीय रेलवे स्टेशन के रूप में चुनें"
                                                AppLanguage.ENGLISH -> "Select as custom Indian Railway station"
                                            },
                                            style = MaterialTheme.typography.bodySmall.copy(
                                                color = MaterialTheme.colorScheme.onSurfaceVariant
                                            )
                                        )
                                    }
                                }
                            }
                        }
                    }

                    items(filteredStations) { station ->
                        Card(
                            colors = CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
                            ),
                            shape = RoundedCornerShape(10.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    onSelectStation(station)
                                }
                                .testTag("station_item_${station.code}")
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(12.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                                    modifier = Modifier.weight(1f)
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.LocationOn,
                                        contentDescription = null,
                                        tint = RailBluePrimary
                                    )
                                    Column {
                                        Text(
                                            text = station.getName(currentLanguage),
                                            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold)
                                        )
                                        Text(
                                            text = "${station.nameEn} • ${station.state}",
                                            style = MaterialTheme.typography.bodySmall.copy(color = MaterialTheme.colorScheme.onSurfaceVariant)
                                        )
                                    }
                                }

                                Surface(
                                    shape = RoundedCornerShape(6.dp),
                                    color = RailBluePrimary.copy(alpha = 0.1f)
                                ) {
                                    Text(
                                        text = station.code,
                                        style = MaterialTheme.typography.labelMedium.copy(
                                            fontWeight = FontWeight.Bold,
                                            color = RailBluePrimary
                                        ),
                                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
