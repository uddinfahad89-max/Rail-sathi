package com.example.data.model

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

enum class AppLanguage(val code: String, val displayName: String, val shortLabel: String) {
    BENGALI("bn", "বাংলা", "বাং"),
    HINDI("hi", "हिन्दी", "हिं"),
    ENGLISH("en", "English", "Eng")
}

/**
 * Utility for Indian Standard Time (IST / Asia/Kolkata)
 */
object IstTimeUtil {
    private val istTimeZone = TimeZone.getTimeZone("Asia/Kolkata")

    fun getCurrentIstTimeString(): String {
        val sdf = SimpleDateFormat("hh:mm:ss a 'IST'", Locale.US)
        sdf.timeZone = istTimeZone
        return sdf.format(Date())
    }

    fun getCurrentIstDateString(lang: AppLanguage = AppLanguage.BENGALI): String {
        val sdf = SimpleDateFormat("dd MMM yyyy", Locale.US)
        sdf.timeZone = istTimeZone
        val str = sdf.format(Date())
        return str
    }

    fun formatIstTime(hours: Int, minutes: Int): String {
        val isPm = hours >= 12
        val h = if (hours % 12 == 0) 12 else hours % 12
        return String.format(Locale.US, "%02d:%02d %s IST", h, minutes, if (isPm) "PM" else "AM")
    }

    fun formatBengaliDigits(input: String): String {
        val bengaliDigits = charArrayOf('০', '১', '২', '৩', '৪', '৫', '৬', '৭', '৮', '৯')
        val sb = StringBuilder()
        for (c in input) {
            if (c in '0'..'9') {
                sb.append(bengaliDigits[c - '0'])
            } else {
                sb.append(c)
            }
        }
        return sb.toString()
    }
}

data class Station(
    val code: String,
    val nameEn: String,
    val nameBn: String,
    val nameHi: String,
    val cityEn: String,
    val cityBn: String,
    val cityHi: String,
    val state: String
) {
    fun getName(lang: AppLanguage): String = when (lang) {
        AppLanguage.BENGALI -> nameBn
        AppLanguage.HINDI -> nameHi
        AppLanguage.ENGLISH -> nameEn
    }

    fun getCity(lang: AppLanguage): String = when (lang) {
        AppLanguage.BENGALI -> cityBn
        AppLanguage.HINDI -> cityHi
        AppLanguage.ENGLISH -> cityEn
    }

    fun getDisplayLabel(lang: AppLanguage): String = "${getName(lang)} ($code) - ${getCity(lang)}"
}

enum class TrainClass(
    val code: String,
    val nameBn: String,
    val nameHi: String,
    val nameEn: String
) {
    FIRST_AC("1A", "প্রথম এসি (1A)", "प्रथम एसी (1A)", "AC First Class (1A)"),
    SECOND_AC("2A", "দ্বিতীয় এসি (2A)", "द्वितीय एसी (2A)", "AC 2 Tier (2A)"),
    THIRD_AC("3A", "তৃতীয় এসি (3A)", "तृतीय एसी (3A)", "AC 3 Tier (3A)"),
    THIRD_AC_ECONOMY("3E", "৩ এসি ইকোনমি (3E)", "3 एसी इकोनॉमी (3E)", "3 AC Economy (3E)"),
    CHAIR_CAR("CC", "এসি চেয়ার কার (CC)", "एसी चेयर कार (CC)", "AC Chair Car (CC)"),
    EXEC_CHAIR_CAR("EC", "এক্সিকিউটিভ কার (EC)", "कार्यकारी चेयर कार (EC)", "Exec Chair Car (EC)"),
    SLEEPER("SL", "স্লিপার (SL)", "स्लीपर (SL)", "Sleeper Class (SL)"),
    SECOND_SITTING("2S", "সেকেন্ড সিটিং (2S)", "सेकंड सीटिंग (2S)", "Second Sitting (2S)");

    fun getName(lang: AppLanguage): String = when (lang) {
        AppLanguage.BENGALI -> nameBn
        AppLanguage.HINDI -> nameHi
        AppLanguage.ENGLISH -> nameEn
    }
}

data class ClassAvailability(
    val trainClass: TrainClass,
    val statusText: String, // e.g. "AVAILABLE 42", "WL 14", "REGRET"
    val isAvailable: Boolean,
    val fare: Int,
    val confirmationChancePercent: Int = 100
)

data class Train(
    val number: String,
    val nameEn: String,
    val nameBn: String,
    val nameHi: String,
    val type: String,
    val departureStation: Station,
    val arrivalStation: Station,
    val departureTimeIst: String,
    val arrivalTimeIst: String,
    val durationHoursMinutesBn: String,
    val durationHoursMinutesHi: String,
    val durationHoursMinutesEn: String,
    val runsOnDaysBn: List<String>,
    val runsOnDaysHi: List<String>,
    val runsOnDaysEn: List<String>,
    val intermediateStations: List<StationStop>,
    val classAvailabilities: List<ClassAvailability>
) {
    fun getName(lang: AppLanguage): String = when (lang) {
        AppLanguage.BENGALI -> nameBn
        AppLanguage.HINDI -> nameHi
        AppLanguage.ENGLISH -> nameEn
    }

    fun getDuration(lang: AppLanguage): String = when (lang) {
        AppLanguage.BENGALI -> durationHoursMinutesBn
        AppLanguage.HINDI -> durationHoursMinutesHi
        AppLanguage.ENGLISH -> durationHoursMinutesEn
    }

    fun getRunsOn(lang: AppLanguage): List<String> = when (lang) {
        AppLanguage.BENGALI -> runsOnDaysBn
        AppLanguage.HINDI -> runsOnDaysHi
        AppLanguage.ENGLISH -> runsOnDaysEn
    }
}

data class StationStop(
    val station: Station,
    val scheduledArrivalIst: String,
    val scheduledDepartureIst: String,
    val haltMinutes: Int,
    val platformNumberBn: String,
    val platformNumberHi: String,
    val platformNumberEn: String,
    val distanceFromOriginKm: Int
) {
    fun getPlatform(lang: AppLanguage): String = when (lang) {
        AppLanguage.BENGALI -> platformNumberBn
        AppLanguage.HINDI -> platformNumberHi
        AppLanguage.ENGLISH -> platformNumberEn
    }
}

data class SplitTicketOption(
    val id: String,
    val trainNumber: String,
    val trainNameBn: String,
    val trainNameHi: String,
    val trainNameEn: String,
    val splitType: SplitType,
    val intermediateStation: Station,
    val segment1: TicketSegment,
    val segment2: TicketSegment,
    val totalFare: Int,
    val comparisonBn: String,
    val comparisonHi: String,
    val comparisonEn: String,
    val layoverMinutes: Int = 0,
    val overallConfirmationPercent: Int = 100
) {
    fun getTrainName(lang: AppLanguage): String = when (lang) {
        AppLanguage.BENGALI -> trainNameBn
        AppLanguage.HINDI -> trainNameHi
        AppLanguage.ENGLISH -> trainNameEn
    }

    fun getComparison(lang: AppLanguage): String = when (lang) {
        AppLanguage.BENGALI -> comparisonBn
        AppLanguage.HINDI -> comparisonHi
        AppLanguage.ENGLISH -> comparisonEn
    }
}

enum class SplitType {
    SAME_TRAIN_SEAT_SWITCH,
    MULTI_TRAIN_CONNECTING
}

data class TicketSegment(
    val trainNumber: String,
    val trainNameBn: String,
    val trainNameHi: String,
    val trainNameEn: String,
    val fromStation: Station,
    val toStation: Station,
    val departureTimeIst: String,
    val arrivalTimeIst: String,
    val trainClass: TrainClass,
    val seatBerthInfoBn: String,
    val seatBerthInfoHi: String,
    val seatBerthInfoEn: String,
    val statusTextBn: String,
    val statusTextHi: String,
    val statusTextEn: String,
    val fare: Int
) {
    fun getTrainName(lang: AppLanguage): String = when (lang) {
        AppLanguage.BENGALI -> trainNameBn
        AppLanguage.HINDI -> trainNameHi
        AppLanguage.ENGLISH -> trainNameEn
    }

    fun getSeatInfo(lang: AppLanguage): String = when (lang) {
        AppLanguage.BENGALI -> seatBerthInfoBn
        AppLanguage.HINDI -> seatBerthInfoHi
        AppLanguage.ENGLISH -> seatBerthInfoEn
    }

    fun getStatus(lang: AppLanguage): String = when (lang) {
        AppLanguage.BENGALI -> statusTextBn
        AppLanguage.HINDI -> statusTextHi
        AppLanguage.ENGLISH -> statusTextEn
    }
}

data class Passenger(
    val number: Int,
    val nameBn: String,
    val nameHi: String,
    val nameEn: String,
    val bookingStatus: String,
    val currentStatus: String,
    val coach: String,
    val berth: String,
    val berthTypeBn: String,
    val berthTypeHi: String,
    val berthTypeEn: String
) {
    fun getName(lang: AppLanguage): String = when (lang) {
        AppLanguage.BENGALI -> nameBn
        AppLanguage.HINDI -> nameHi
        AppLanguage.ENGLISH -> nameEn
    }

    fun getBerthType(lang: AppLanguage): String = when (lang) {
        AppLanguage.BENGALI -> berthTypeBn
        AppLanguage.HINDI -> berthTypeHi
        AppLanguage.ENGLISH -> berthTypeEn
    }
}

data class PnrDetails(
    val pnrNumber: String,
    val trainNumber: String,
    val trainNameBn: String,
    val trainNameHi: String,
    val trainNameEn: String,
    val fromStation: Station,
    val toStation: Station,
    val journeyDateIstBn: String,
    val journeyDateIstHi: String,
    val journeyDateIstEn: String,
    val departureTimeIst: String,
    val arrivalTimeIst: String,
    val trainClass: TrainClass,
    val chartStatusBn: String,
    val chartStatusHi: String,
    val chartStatusEn: String,
    val isChartPrepared: Boolean,
    val confirmationProbabilityPercent: Int,
    val confirmationLevelBn: String,
    val confirmationLevelHi: String,
    val confirmationLevelEn: String,
    val passengers: List<Passenger>,
    val aiPredictionAdviceBn: String,
    val aiPredictionAdviceHi: String,
    val aiPredictionAdviceEn: String
) {
    fun getTrainName(lang: AppLanguage): String = when (lang) {
        AppLanguage.BENGALI -> trainNameBn
        AppLanguage.HINDI -> trainNameHi
        AppLanguage.ENGLISH -> trainNameEn
    }

    fun getJourneyDate(lang: AppLanguage): String = when (lang) {
        AppLanguage.BENGALI -> journeyDateIstBn
        AppLanguage.HINDI -> journeyDateIstHi
        AppLanguage.ENGLISH -> journeyDateIstEn
    }

    fun getChartStatus(lang: AppLanguage): String = when (lang) {
        AppLanguage.BENGALI -> chartStatusBn
        AppLanguage.HINDI -> chartStatusHi
        AppLanguage.ENGLISH -> chartStatusEn
    }

    fun getConfirmationLevel(lang: AppLanguage): String = when (lang) {
        AppLanguage.BENGALI -> confirmationLevelBn
        AppLanguage.HINDI -> confirmationLevelHi
        AppLanguage.ENGLISH -> confirmationLevelEn
    }

    fun getAiAdvice(lang: AppLanguage): String = when (lang) {
        AppLanguage.BENGALI -> aiPredictionAdviceBn
        AppLanguage.HINDI -> aiPredictionAdviceHi
        AppLanguage.ENGLISH -> aiPredictionAdviceEn
    }
}

data class LiveTrainStatus(
    val trainNumber: String,
    val trainNameBn: String,
    val trainNameHi: String,
    val trainNameEn: String,
    val sourceStation: Station,
    val destinationStation: Station,
    val currentStationNameBn: String,
    val currentStationNameHi: String,
    val currentStationNameEn: String,
    val nextStationNameBn: String,
    val nextStationNameHi: String,
    val nextStationNameEn: String,
    val distanceToNextKm: Int,
    val currentSpeedKmph: Int,
    val delayMinutes: Int,
    val delayStatusBn: String,
    val delayStatusHi: String,
    val delayStatusEn: String,
    val isRunningOnTime: Boolean,
    val lastUpdatedIst: String,
    val timeline: List<LiveStationStop>
) {
    fun getTrainName(lang: AppLanguage): String = when (lang) {
        AppLanguage.BENGALI -> trainNameBn
        AppLanguage.HINDI -> trainNameHi
        AppLanguage.ENGLISH -> trainNameEn
    }

    fun getCurrentStation(lang: AppLanguage): String = when (lang) {
        AppLanguage.BENGALI -> currentStationNameBn
        AppLanguage.HINDI -> currentStationNameHi
        AppLanguage.ENGLISH -> currentStationNameEn
    }

    fun getNextStation(lang: AppLanguage): String = when (lang) {
        AppLanguage.BENGALI -> nextStationNameBn
        AppLanguage.HINDI -> nextStationNameHi
        AppLanguage.ENGLISH -> nextStationNameEn
    }

    fun getDelayStatus(lang: AppLanguage): String = when (lang) {
        AppLanguage.BENGALI -> delayStatusBn
        AppLanguage.HINDI -> delayStatusHi
        AppLanguage.ENGLISH -> delayStatusEn
    }
}

data class LiveStationStop(
    val station: Station,
    val scheduledArrivalIst: String,
    val scheduledDepartureIst: String,
    val actualOrExpectedArrivalIst: String,
    val actualOrExpectedDepartureIst: String,
    val platformBn: String,
    val platformHi: String,
    val platformEn: String,
    val status: StopStatus,
    val delayMinutes: Int
) {
    fun getPlatform(lang: AppLanguage): String = when (lang) {
        AppLanguage.BENGALI -> platformBn
        AppLanguage.HINDI -> platformHi
        AppLanguage.ENGLISH -> platformEn
    }
}

enum class StopStatus {
    DEPARTED,
    CURRENT,
    UPCOMING
}
