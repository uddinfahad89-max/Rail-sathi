package com.example.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.AppLanguage
import com.example.data.model.PnrDetails
import com.example.ui.theme.*
import com.example.ui.util.AppStrings

@Composable
fun PnrScreen(
    currentLanguage: AppLanguage,
    pnrInput: String,
    pnrDetails: PnrDetails?,
    isLoading: Boolean,
    errorMessage: String?,
    onPnrInputChange: (String) -> Unit,
    onCheckPnr: (String?) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .testTag("pnr_screen"),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // PNR Input Card
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
                        text = AppStrings.pnrHeader(currentLanguage),
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Bold,
                            color = RailBluePrimary
                        )
                    )
                    Text(
                        text = AppStrings.pnrSubtitle(currentLanguage),
                        style = MaterialTheme.typography.bodySmall.copy(color = MaterialTheme.colorScheme.onSurfaceVariant)
                    )

                    OutlinedTextField(
                        value = pnrInput,
                        onValueChange = onPnrInputChange,
                        placeholder = { Text(AppStrings.pnrPlaceholder(currentLanguage)) },
                        leadingIcon = {
                            Icon(imageVector = Icons.Default.ConfirmationNumber, contentDescription = null, tint = RailBluePrimary)
                        },
                        trailingIcon = {
                            if (pnrInput.isNotEmpty()) {
                                IconButton(onClick = { onPnrInputChange("") }) {
                                    Icon(imageVector = Icons.Default.Clear, contentDescription = "Clear")
                                }
                            }
                        },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        singleLine = true,
                        isError = errorMessage != null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("pnr_text_field"),
                        shape = RoundedCornerShape(12.dp)
                    )

                    if (errorMessage != null) {
                        Text(
                            text = errorMessage,
                            color = MaterialTheme.colorScheme.error,
                            style = MaterialTheme.typography.bodySmall
                        )
                    }

                    Button(
                        onClick = { onCheckPnr(pnrInput) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp)
                            .testTag("check_pnr_button"),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = RailBluePrimary)
                    ) {
                        if (isLoading) {
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
                            Text(AppStrings.checkPnrButton(currentLanguage), fontWeight = FontWeight.Bold)
                        }
                    }

                    // Demo PNR quick chips
                    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                        Text(
                            text = AppStrings.demoPnrLabel(currentLanguage),
                            style = MaterialTheme.typography.labelSmall.copy(color = MaterialTheme.colorScheme.onSurfaceVariant)
                        )
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            val chipRajdhani = when (currentLanguage) {
                                AppLanguage.BENGALI -> "রাজধানী (WL➜৯৪%)"
                                AppLanguage.HINDI -> "राजधानी (WL➜94%)"
                                AppLanguage.ENGLISH -> "Rajdhani (WL➜94%)"
                            }
                            val chipPuri = when (currentLanguage) {
                                AppLanguage.BENGALI -> "পুরী (RAC➜৯৮%)"
                                AppLanguage.HINDI -> "पुरी (RAC➜98%)"
                                AppLanguage.ENGLISH -> "Puri (RAC➜98%)"
                            }
                            val chipVande = when (currentLanguage) {
                                AppLanguage.BENGALI -> "বন্দে ভারত (CNF)"
                                AppLanguage.HINDI -> "वंदे भारत (CNF)"
                                AppLanguage.ENGLISH -> "Vande Bharat (CNF)"
                            }

                            AssistChip(
                                onClick = { onCheckPnr("6428190342") },
                                label = { Text(chipRajdhani, fontSize = 11.sp) },
                                modifier = Modifier.testTag("demo_pnr_rajdhani")
                            )
                            AssistChip(
                                onClick = { onCheckPnr("2841957201") },
                                label = { Text(chipPuri, fontSize = 11.sp) },
                                modifier = Modifier.testTag("demo_pnr_puri")
                            )
                            AssistChip(
                                onClick = { onCheckPnr("8923014756") },
                                label = { Text(chipVande, fontSize = 11.sp) },
                                modifier = Modifier.testTag("demo_pnr_vande")
                            )
                        }
                    }
                }
            }
        }

        // PNR Results Card
        if (pnrDetails != null) {
            item {
                PnrDetailsCard(pnrDetails = pnrDetails, currentLanguage = currentLanguage)
            }
        }
    }
}

@Composable
fun PnrDetailsCard(pnrDetails: PnrDetails, currentLanguage: AppLanguage) {
    val prob = pnrDetails.confirmationProbabilityPercent
    val isHigh = prob >= 85
    val isMedium = prob in 60..84
    val probColor = when {
        isHigh -> RailConfirmedGreen
        isMedium -> RailRacOrange
        else -> RailWaitlistRed
    }
    val probContainer = when {
        isHigh -> RailConfirmedGreenContainer
        isMedium -> RailRacOrangeContainer
        else -> RailWaitlistRedContainer
    }

    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
        modifier = Modifier
            .fillMaxWidth()
            .testTag("pnr_details_card")
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            // PNR Number & Train Header
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "PNR: ${pnrDetails.pnrNumber}",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Bold,
                            color = RailBluePrimary
                        )
                    )
                    Text(
                        text = pnrDetails.getTrainName(currentLanguage),
                        style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold)
                    )
                }

                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = RailBluePrimary.copy(alpha = 0.1f)
                ) {
                    Text(
                        text = pnrDetails.trainClass.code,
                        style = MaterialTheme.typography.labelMedium.copy(
                            fontWeight = FontWeight.Bold,
                            color = RailBluePrimary
                        ),
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }
            }

            // Route & IST Timings
            Surface(
                shape = RoundedCornerShape(12.dp),
                color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.6f),
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "${pnrDetails.fromStation.getName(currentLanguage)} (${pnrDetails.fromStation.code})",
                            style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Bold)
                        )
                        Text(
                            text = pnrDetails.departureTimeIst,
                            style = MaterialTheme.typography.labelSmall.copy(
                                fontWeight = FontWeight.Bold,
                                color = RailBluePrimary
                            )
                        )
                    }

                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                        contentDescription = null,
                        tint = RailSecondaryOrange
                    )

                    Column(horizontalAlignment = Alignment.End) {
                        Text(
                            text = "${pnrDetails.toStation.getName(currentLanguage)} (${pnrDetails.toStation.code})",
                            style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Bold)
                        )
                        Text(
                            text = pnrDetails.arrivalTimeIst,
                            style = MaterialTheme.typography.labelSmall.copy(
                                fontWeight = FontWeight.Bold,
                                color = RailBluePrimary
                            )
                        )
                    }
                }
            }

            // Prediction Meter Box
            Surface(
                shape = RoundedCornerShape(12.dp),
                color = probContainer,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(14.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Analytics,
                                contentDescription = null,
                                tint = probColor
                            )
                            Text(
                                text = AppStrings.predictionHeader(currentLanguage),
                                style = MaterialTheme.typography.titleSmall.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = probColor
                                )
                            )
                        }

                        Text(
                            text = "$prob%",
                            style = MaterialTheme.typography.titleLarge.copy(
                                fontWeight = FontWeight.ExtraBold,
                                color = probColor
                            )
                        )
                    }

                    LinearProgressIndicator(
                        progress = { prob / 100f },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(8.dp)
                            .clip(RoundedCornerShape(4.dp)),
                        color = probColor,
                        trackColor = Color.White.copy(alpha = 0.6f)
                    )

                    Text(
                        text = "${pnrDetails.getConfirmationLevel(currentLanguage)} ($prob%)",
                        style = MaterialTheme.typography.labelMedium.copy(
                            fontWeight = FontWeight.SemiBold,
                            color = probColor
                        )
                    )
                }
            }

            // Chart Status Info
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = AppStrings.chartStatusLabel(currentLanguage),
                    style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.SemiBold)
                )
                Surface(
                    shape = RoundedCornerShape(6.dp),
                    color = if (pnrDetails.isChartPrepared) RailConfirmedGreenContainer else MaterialTheme.colorScheme.surfaceVariant
                ) {
                    Text(
                        text = pnrDetails.getChartStatus(currentLanguage),
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontWeight = FontWeight.Bold,
                            color = if (pnrDetails.isChartPrepared) RailConfirmedGreen else MaterialTheme.colorScheme.onSurfaceVariant
                        ),
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }
            }

            HorizontalDivider()

            // Passengers Breakdown
            Text(
                text = AppStrings.passengersLabel(currentLanguage),
                style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold)
            )

            pnrDetails.passengers.forEach { p ->
                Surface(
                    shape = RoundedCornerShape(10.dp),
                    color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(
                                text = p.getName(currentLanguage),
                                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold)
                            )
                            Text(
                                text = "${AppStrings.tabPnr(currentLanguage)}: ${p.bookingStatus}",
                                style = MaterialTheme.typography.labelSmall.copy(color = MaterialTheme.colorScheme.onSurfaceVariant)
                            )
                        }

                        Column(horizontalAlignment = Alignment.End) {
                            Surface(
                                shape = RoundedCornerShape(6.dp),
                                color = if (p.currentStatus.startsWith("CNF")) RailConfirmedGreenContainer else RailRacOrangeContainer
                            ) {
                                Text(
                                    text = p.currentStatus,
                                    style = MaterialTheme.typography.labelSmall.copy(
                                        fontWeight = FontWeight.Bold,
                                        color = if (p.currentStatus.startsWith("CNF")) RailConfirmedGreen else RailRacOrange
                                    ),
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                                )
                            }
                            Text(
                                text = p.getBerthType(currentLanguage),
                                style = MaterialTheme.typography.labelSmall.copy(color = MaterialTheme.colorScheme.onSurfaceVariant)
                            )
                        }
                    }
                }
            }

            // AI Advice Card
            Surface(
                shape = RoundedCornerShape(12.dp),
                color = RailBluePrimary.copy(alpha = 0.08f),
                border = BorderStroke(1.dp, RailBluePrimary.copy(alpha = 0.2f)),
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.padding(12.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Psychology,
                        contentDescription = null,
                        tint = RailBluePrimary
                    )
                    Column {
                        Text(
                            text = AppStrings.aiAdviceLabel(currentLanguage),
                            style = MaterialTheme.typography.titleSmall.copy(
                                fontWeight = FontWeight.Bold,
                                color = RailBluePrimary
                            )
                        )
                        Text(
                            text = pnrDetails.getAiAdvice(currentLanguage),
                            style = MaterialTheme.typography.bodySmall.copy(
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        )
                    }
                }
            }
        }
    }
}
