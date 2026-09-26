package com.example.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.AppLanguage
import com.example.data.model.LiveStationStop
import com.example.data.model.LiveTrainStatus
import com.example.data.model.StopStatus
import com.example.ui.theme.*
import com.example.ui.util.AppStrings

@Composable
fun LiveTrackingScreen(
    currentLanguage: AppLanguage,
    currentTrainNumber: String,
    liveStatus: LiveTrainStatus?,
    isLoading: Boolean,
    onSelectTrain: (String) -> Unit,
    onRefresh: () -> Unit
) {
    var customTrainInput by remember { mutableStateOf("") }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .testTag("live_tracking_screen"),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Quick Selector & Search
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
                        text = AppStrings.liveHeader(currentLanguage),
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Bold,
                            color = RailBluePrimary
                        )
                    )

                    // Popular train selector chips
                    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                        Text(
                            text = AppStrings.popularTrains(currentLanguage),
                            style = MaterialTheme.typography.labelSmall.copy(color = MaterialTheme.colorScheme.onSurfaceVariant)
                        )
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            val chip1 = when (currentLanguage) {
                                AppLanguage.BENGALI -> "১২৩০১ রাজধানী"
                                AppLanguage.HINDI -> "12301 राजधानी"
                                AppLanguage.ENGLISH -> "12301 Rajdhani"
                            }
                            val chip2 = when (currentLanguage) {
                                AppLanguage.BENGALI -> "২২৩০১ বন্দে ভারত"
                                AppLanguage.HINDI -> "22301 वंदे भारत"
                                AppLanguage.ENGLISH -> "22301 Vande Bharat"
                            }
                            val chip3 = when (currentLanguage) {
                                AppLanguage.BENGALI -> "১২৮৩৭ পুরী এক্সপ."
                                AppLanguage.HINDI -> "12837 पुरी एक्स."
                                AppLanguage.ENGLISH -> "12837 Puri Exp"
                            }

                            FilterChip(
                                selected = currentTrainNumber == "12301",
                                onClick = { onSelectTrain("12301") },
                                label = { Text(chip1, fontSize = 11.sp) },
                                modifier = Modifier.testTag("live_chip_12301")
                            )
                            FilterChip(
                                selected = currentTrainNumber == "22301",
                                onClick = { onSelectTrain("22301") },
                                label = { Text(chip2, fontSize = 11.sp) },
                                modifier = Modifier.testTag("live_chip_22301")
                            )
                            FilterChip(
                                selected = currentTrainNumber == "12837",
                                onClick = { onSelectTrain("12837") },
                                label = { Text(chip3, fontSize = 11.sp) },
                                modifier = Modifier.testTag("live_chip_12837")
                            )
                        }
                    }

                    // Search Other Train
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        OutlinedTextField(
                            value = customTrainInput,
                            onValueChange = { customTrainInput = it },
                            placeholder = { Text(AppStrings.trainInputPlaceholder(currentLanguage)) },
                            singleLine = true,
                            modifier = Modifier
                                .weight(1f)
                                .testTag("train_number_search_field"),
                            shape = RoundedCornerShape(12.dp)
                        )

                        Button(
                            onClick = {
                                if (customTrainInput.isNotBlank()) {
                                    onSelectTrain(customTrainInput.trim())
                                } else {
                                    onRefresh()
                                }
                            },
                            shape = RoundedCornerShape(12.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = RailBluePrimary),
                            modifier = Modifier.testTag("track_train_button")
                        ) {
                            Icon(imageVector = Icons.Default.GpsFixed, contentDescription = null)
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(AppStrings.trackButton(currentLanguage))
                        }
                    }
                }
            }
        }

        // Live Status Details Card
        if (liveStatus != null) {
            item {
                LiveStatusSummaryCard(
                    status = liveStatus,
                    currentLanguage = currentLanguage,
                    onRefresh = onRefresh
                )
            }

            // Timeline Header
            item {
                Text(
                    text = AppStrings.routeHeader(currentLanguage),
                    style = MaterialTheme.typography.titleSmall.copy(
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onBackground
                    ),
                    modifier = Modifier.padding(top = 4.dp)
                )
            }

            // Station Timeline
            itemsIndexed(liveStatus.timeline) { index, stop ->
                StationTimelineItem(
                    stop = stop,
                    currentLanguage = currentLanguage,
                    isFirst = index == 0,
                    isLast = index == liveStatus.timeline.lastIndex
                )
            }
        }
    }
}

@Composable
fun LiveStatusSummaryCard(
    status: LiveTrainStatus,
    currentLanguage: AppLanguage,
    onRefresh: () -> Unit
) {
    val statusColor = if (status.isRunningOnTime) RailConfirmedGreen else RailRacOrange
    val statusContainer = if (status.isRunningOnTime) RailConfirmedGreenContainer else RailRacOrangeContainer

    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
        modifier = Modifier
            .fillMaxWidth()
            .testTag("live_summary_card")
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Header
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "${status.trainNumber} - ${status.getTrainName(currentLanguage)}",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Bold,
                            color = RailBluePrimary
                        )
                    )
                    Text(
                        text = "${status.sourceStation.getName(currentLanguage)} ➜ ${status.destinationStation.getName(currentLanguage)}",
                        style = MaterialTheme.typography.bodySmall.copy(color = MaterialTheme.colorScheme.onSurfaceVariant)
                    )
                }

                IconButton(onClick = onRefresh, modifier = Modifier.testTag("refresh_live_button")) {
                    Icon(imageVector = Icons.Default.Refresh, contentDescription = "রিফ্রেশ", tint = RailBluePrimary)
                }
            }

            // Running Delay / On-Time Badge
            Surface(
                shape = RoundedCornerShape(10.dp),
                color = statusContainer,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Icon(
                        imageVector = if (status.isRunningOnTime) Icons.Default.CheckCircle else Icons.Default.Schedule,
                        contentDescription = null,
                        tint = statusColor
                    )
                    Column {
                        Text(
                            text = status.getDelayStatus(currentLanguage),
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontWeight = FontWeight.Bold,
                                color = statusColor
                            )
                        )
                        Text(
                            text = "${AppStrings.lastUpdated(currentLanguage)} ${status.lastUpdatedIst}",
                            style = MaterialTheme.typography.labelSmall.copy(
                                color = statusColor.copy(alpha = 0.85f)
                            )
                        )
                    }
                }
            }

            // Current Location & Speed Metrics
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // Current Location
                Surface(
                    shape = RoundedCornerShape(10.dp),
                    color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
                    modifier = Modifier.weight(1.3f)
                ) {
                    Column(modifier = Modifier.padding(10.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                            Icon(imageVector = Icons.Default.LocationOn, contentDescription = null, tint = RailSecondaryOrange, modifier = Modifier.size(16.dp))
                            Text(AppStrings.currentLocation(currentLanguage), style = MaterialTheme.typography.labelSmall.copy(color = MaterialTheme.colorScheme.onSurfaceVariant))
                        }
                        Text(
                            text = status.getCurrentStation(currentLanguage),
                            style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Bold)
                        )
                    }
                }

                // Speed
                Surface(
                    shape = RoundedCornerShape(10.dp),
                    color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
                    modifier = Modifier.weight(0.7f)
                ) {
                    Column(
                        modifier = Modifier.padding(10.dp),
                        verticalArrangement = Arrangement.spacedBy(4.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                            Icon(imageVector = Icons.Default.Speed, contentDescription = null, tint = RailBluePrimary, modifier = Modifier.size(16.dp))
                            Text(AppStrings.speed(currentLanguage), style = MaterialTheme.typography.labelSmall.copy(color = MaterialTheme.colorScheme.onSurfaceVariant))
                        }
                        Text(
                            text = "${status.currentSpeedKmph} ${AppStrings.kmph(currentLanguage)}",
                            style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Bold, color = RailBluePrimary)
                        )
                    }
                }
            }

            // Next Station
            Surface(
                shape = RoundedCornerShape(10.dp),
                color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f),
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.padding(10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "${AppStrings.nextStation(currentLanguage)} ${status.getNextStation(currentLanguage)}",
                        style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.SemiBold)
                    )
                    Text(
                        text = "${AppStrings.distance(currentLanguage)} ${status.distanceToNextKm} ${AppStrings.km(currentLanguage)}",
                        style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold, color = RailSecondaryOrange)
                    )
                }
            }
        }
    }
}

@Composable
fun StationTimelineItem(
    stop: LiveStationStop,
    currentLanguage: AppLanguage,
    isFirst: Boolean,
    isLast: Boolean
) {
    val nodeColor = when (stop.status) {
        StopStatus.DEPARTED -> RailConfirmedGreen
        StopStatus.CURRENT -> RailSecondaryOrange
        StopStatus.UPCOMING -> Color.Gray
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .testTag("timeline_stop_${stop.station.code}"),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // Vertical Timeline Column
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.width(28.dp)
        ) {
            if (!isFirst) {
                Box(
                    modifier = Modifier
                        .width(3.dp)
                        .height(16.dp)
                        .background(nodeColor.copy(alpha = 0.5f))
                )
            } else {
                Spacer(modifier = Modifier.height(16.dp))
            }

            Box(
                modifier = Modifier
                    .size(20.dp)
                    .clip(CircleShape)
                    .background(nodeColor)
                    .border(2.dp, Color.White, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                if (stop.status == StopStatus.CURRENT) {
                    Icon(
                        imageVector = Icons.Default.DirectionsTransit,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(12.dp)
                    )
                }
            }

            if (!isLast) {
                Box(
                    modifier = Modifier
                        .width(3.dp)
                        .height(36.dp)
                        .background(nodeColor.copy(alpha = 0.5f))
                )
            }
        }

        // Station Details Content
        Surface(
            shape = RoundedCornerShape(10.dp),
            color = if (stop.status == StopStatus.CURRENT) RailSecondaryOrange.copy(alpha = 0.1f) else MaterialTheme.colorScheme.surface,
            border = if (stop.status == StopStatus.CURRENT) BorderStroke(1.5.dp, RailSecondaryOrange) else null,
            shadowElevation = 1.dp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        ) {
            Column(
                modifier = Modifier.padding(12.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "${stop.station.getName(currentLanguage)} (${stop.station.code})",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontWeight = FontWeight.Bold,
                            color = if (stop.status == StopStatus.CURRENT) RailSecondaryOrange else MaterialTheme.colorScheme.onSurface
                        )
                    )

                    Surface(
                        shape = RoundedCornerShape(4.dp),
                        color = MaterialTheme.colorScheme.surfaceVariant
                    ) {
                        Text(
                            text = stop.getPlatform(currentLanguage),
                            style = MaterialTheme.typography.labelSmall.copy(
                                fontWeight = FontWeight.Bold,
                                fontSize = 10.sp
                            ),
                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                        )
                    }
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Text(
                            text = "${AppStrings.arrival(currentLanguage)} ${stop.actualOrExpectedArrivalIst}",
                            style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.SemiBold)
                        )
                        Text(
                            text = "${AppStrings.scheduled(currentLanguage)} ${stop.scheduledArrivalIst}",
                            style = MaterialTheme.typography.labelSmall.copy(color = MaterialTheme.colorScheme.onSurfaceVariant)
                        )
                    }

                    if (stop.delayMinutes > 0) {
                        Surface(
                            shape = RoundedCornerShape(4.dp),
                            color = RailRacOrangeContainer
                        ) {
                            Text(
                                text = AppStrings.lateMins(currentLanguage, stop.delayMinutes),
                                style = MaterialTheme.typography.labelSmall.copy(
                                    color = RailRacOrange,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 10.sp
                                ),
                                modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                            )
                        }
                    } else {
                        Surface(
                            shape = RoundedCornerShape(4.dp),
                            color = RailConfirmedGreenContainer
                        ) {
                            Text(
                                text = AppStrings.onTimeBadge(currentLanguage),
                                style = MaterialTheme.typography.labelSmall.copy(
                                    color = RailConfirmedGreen,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 10.sp
                                ),
                                modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}
