package com.example.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AirlineSeatReclineExtra
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.SwapHoriz
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.data.model.AppLanguage
import com.example.data.model.SplitTicketOption
import com.example.data.model.SplitType
import com.example.data.model.TicketSegment
import com.example.ui.theme.RailBluePrimary
import com.example.ui.theme.RailConfirmedGreen
import com.example.ui.theme.RailConfirmedGreenContainer
import com.example.ui.theme.RailSecondaryOrange
import com.example.ui.util.AppStrings

@Composable
fun SplitTicketDetailDialog(
    splitOption: SplitTicketOption,
    currentLanguage: AppLanguage,
    onDismiss: () -> Unit
) {
    Dialog(onDismissRequest = onDismiss) {
        Surface(
            shape = RoundedCornerShape(20.dp),
            color = MaterialTheme.colorScheme.surface,
            tonalElevation = 8.dp,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.9f)
                .testTag("split_ticket_detail_dialog")
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp)
            ) {
                // Header
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.SwapHoriz,
                                contentDescription = null,
                                tint = RailSecondaryOrange
                            )
                            Text(
                                text = AppStrings.splitDialogTitle(currentLanguage),
                                style = MaterialTheme.typography.titleMedium.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = RailBluePrimary
                                )
                            )
                        }
                        Text(
                            text = if (splitOption.splitType == SplitType.SAME_TRAIN_SEAT_SWITCH)
                                AppStrings.sameTrainType(currentLanguage)
                            else AppStrings.connectingType(currentLanguage),
                            style = MaterialTheme.typography.bodySmall.copy(color = MaterialTheme.colorScheme.onSurfaceVariant)
                        )
                    }

                    IconButton(
                        onClick = onDismiss,
                        modifier = Modifier.testTag("split_dialog_close_button")
                    ) {
                        Icon(imageVector = Icons.Default.Close, contentDescription = "বন্ধ")
                    }
                }

                HorizontalDivider(modifier = Modifier.padding(vertical = 12.dp))

                Column(
                    modifier = Modifier
                        .weight(1f)
                        .verticalScroll(rememberScrollState()),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    // Confirmation Highlight Banner
                    Surface(
                        shape = RoundedCornerShape(12.dp),
                        color = RailConfirmedGreenContainer,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(
                            modifier = Modifier.padding(12.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.CheckCircle,
                                contentDescription = null,
                                tint = RailConfirmedGreen
                            )
                            Column {
                                Text(
                                    text = AppStrings.confirmedSeatBadge(currentLanguage),
                                    style = MaterialTheme.typography.bodyMedium.copy(
                                        fontWeight = FontWeight.Bold,
                                        color = RailConfirmedGreen
                                    )
                                )
                                Text(
                                    text = if (currentLanguage == AppLanguage.BENGALI)
                                        "সরাসরি টিকিট ওয়েটিংলিস্টে থাকলে স্প্লিট কোটায় নিশ্চিত সিট বরাদ্দ।"
                                    else if (currentLanguage == AppLanguage.HINDI)
                                        "सीधा टिकट वेटिंग में होने पर स्प्लिट कोटे से कन्फर्म सीट मिलेगी।"
                                    else "Confirmed seats allotted via split quotas when direct tickets are waitlisted.",
                                    style = MaterialTheme.typography.bodySmall.copy(
                                        color = RailConfirmedGreen.copy(alpha = 0.9f)
                                    )
                                )
                            }
                        }
                    }

                    // Segment 1 Card
                    SegmentCard(
                        segmentNumber = 1,
                        segment = splitOption.segment1,
                        currentLanguage = currentLanguage
                    )

                    // Intermediate Transfer Info
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Surface(
                            shape = RoundedCornerShape(16.dp),
                            color = RailSecondaryOrange.copy(alpha = 0.12f),
                            border = BorderStroke(1.dp, RailSecondaryOrange.copy(alpha = 0.4f))
                        ) {
                            Row(
                                modifier = Modifier.padding(horizontal = 14.dp, vertical = 8.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.ArrowDownward,
                                    contentDescription = null,
                                    tint = RailSecondaryOrange,
                                    modifier = Modifier.size(16.dp)
                                )
                                Text(
                                    text = AppStrings.seatSwitchNotice(
                                        currentLanguage,
                                        splitOption.intermediateStation.getName(currentLanguage),
                                        splitOption.layoverMinutes
                                    ),
                                    style = MaterialTheme.typography.labelMedium.copy(
                                        fontWeight = FontWeight.Bold,
                                        color = RailSecondaryOrange
                                    )
                                )
                            }
                        }
                    }

                    // Segment 2 Card
                    SegmentCard(
                        segmentNumber = 2,
                        segment = splitOption.segment2,
                        currentLanguage = currentLanguage
                    )

                    // Price & Savings Summary
                    Card(
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
                    ) {
                        Column(
                            modifier = Modifier.padding(14.dp),
                            verticalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            Text(
                                text = if (currentLanguage == AppLanguage.BENGALI) "ভাড়া ও ভ্রমণ বিশ্লেষণ:"
                                else if (currentLanguage == AppLanguage.HINDI) "किराया एवं यात्रा विवरण:"
                                else "Fare & Journey Breakdown:",
                                style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold)
                            )
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    if (currentLanguage == AppLanguage.BENGALI) "১ম অংশ ভাড়া (${splitOption.segment1.trainClass.code}):"
                                    else if (currentLanguage == AppLanguage.HINDI) "1ली भाग किराया (${splitOption.segment1.trainClass.code}):"
                                    else "Part 1 Fare (${splitOption.segment1.trainClass.code}):"
                                )
                                Text("₹${splitOption.segment1.fare}", fontWeight = FontWeight.SemiBold)
                            }
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    if (currentLanguage == AppLanguage.BENGALI) "২য় অংশ ভাড়া (${splitOption.segment2.trainClass.code}):"
                                    else if (currentLanguage == AppLanguage.HINDI) "2री भाग किराया (${splitOption.segment2.trainClass.code}):"
                                    else "Part 2 Fare (${splitOption.segment2.trainClass.code}):"
                                )
                                Text("₹${splitOption.segment2.fare}", fontWeight = FontWeight.SemiBold)
                            }
                            HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = AppStrings.totalFare(currentLanguage),
                                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold)
                                )
                                Text(
                                    text = "₹${splitOption.totalFare}",
                                    style = MaterialTheme.typography.titleMedium.copy(
                                        fontWeight = FontWeight.Bold,
                                        color = RailBluePrimary
                                    )
                                )
                            }
                            Text(
                                text = splitOption.getComparison(currentLanguage),
                                style = MaterialTheme.typography.bodySmall.copy(
                                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                                    fontSize = 11.sp
                                )
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                Button(
                    onClick = onDismiss,
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("split_ticket_understood_button"),
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = RailBluePrimary)
                ) {
                    Text(AppStrings.understoodButton(currentLanguage))
                }
            }
        }
    }
}

@Composable
fun SegmentCard(
    segmentNumber: Int,
    segment: TicketSegment,
    currentLanguage: AppLanguage
) {
    Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.7f)),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    shape = RoundedCornerShape(6.dp),
                    color = RailBluePrimary
                ) {
                    val partLabel = when (currentLanguage) {
                        AppLanguage.BENGALI -> "অংশ $segmentNumber: ${segment.trainClass.getName(currentLanguage)}"
                        AppLanguage.HINDI -> "भाग $segmentNumber: ${segment.trainClass.getName(currentLanguage)}"
                        AppLanguage.ENGLISH -> "Part $segmentNumber: ${segment.trainClass.getName(currentLanguage)}"
                    }
                    Text(
                        text = partLabel,
                        style = MaterialTheme.typography.labelSmall.copy(
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        ),
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }

                Surface(
                    shape = RoundedCornerShape(6.dp),
                    color = RailConfirmedGreenContainer
                ) {
                    Text(
                        text = segment.getStatus(currentLanguage),
                        style = MaterialTheme.typography.labelSmall.copy(
                            color = RailConfirmedGreen,
                            fontWeight = FontWeight.Bold
                        ),
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }
            }

            Text(
                text = "${segment.fromStation.getName(currentLanguage)} (${segment.fromStation.code}) ➜ ${segment.toStation.getName(currentLanguage)} (${segment.toStation.code})",
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold)
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = if (currentLanguage == AppLanguage.BENGALI) "প্রস্থান (IST)"
                        else if (currentLanguage == AppLanguage.HINDI) "प्रस्थान (IST)"
                        else "Departure (IST)",
                        style = MaterialTheme.typography.labelSmall.copy(color = MaterialTheme.colorScheme.onSurfaceVariant)
                    )
                    Text(
                        text = segment.departureTimeIst,
                        style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Bold)
                    )
                }
                Column(horizontalAlignment = Alignment.End) {
                    Text(
                        text = if (currentLanguage == AppLanguage.BENGALI) "পৌঁছানো (IST)"
                        else if (currentLanguage == AppLanguage.HINDI) "आगमन (IST)"
                        else "Arrival (IST)",
                        style = MaterialTheme.typography.labelSmall.copy(color = MaterialTheme.colorScheme.onSurfaceVariant)
                    )
                    Text(
                        text = segment.arrivalTimeIst,
                        style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Bold)
                    )
                }
            }

            Surface(
                shape = RoundedCornerShape(6.dp),
                color = MaterialTheme.colorScheme.surface
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.AirlineSeatReclineExtra,
                        contentDescription = null,
                        tint = RailSecondaryOrange,
                        modifier = Modifier.size(16.dp)
                    )
                    Text(
                        text = segment.getSeatInfo(currentLanguage),
                        style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Medium)
                    )
                }
            }
        }
    }
}
