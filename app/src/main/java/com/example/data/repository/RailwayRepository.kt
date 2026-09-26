package com.example.data.repository

import com.example.data.model.*
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

class RailwayRepository {

    val allStations = listOf(
        Station("HWH", "Howrah Junction", "হাওড়া জংশন", "हावड़ा जंक्शन", "Kolkata", "কলকাতা", "कोलकाता", "West Bengal"),
        Station("SDAH", "Sealdah", "শিয়ালদহ", "सियालदह", "Kolkata", "কলকাতা", "कोलकाता", "West Bengal"),
        Station("KOAA", "Kolkata Terminal", "কলকাতা টার্মিনাল", "कोलकाता टर्मिनल", "Kolkata", "কলকাতা", "कोलकाता", "West Bengal"),
        Station("NDLS", "New Delhi", "নতুন দিল্লি", "नई दिल्ली", "New Delhi", "নতুন দিল্লি", "नई दिल्ली", "Delhi"),
        Station("DDU", "Pt. Deen Dayal Upadhyaya Jn", "পণ্ডিত দীনদয়াল উপাধ্যায়", "पं. दीनदयाल उपाध्याय", "Mughalsarai", "মুঘলসরাই", "मुगलसराय", "Uttar Pradesh"),
        Station("PNBE", "Patna Junction", "পাটনা জংশন", "पटना जंक्शन", "Patna", "পাটনা", "पटना", "Bihar"),
        Station("CNB", "Kanpur Central", "কানপুর সেন্ট্রাল", "कानपुर सेंट्रल", "Kanpur", "কানপুর", "कानपुर", "Uttar Pradesh"),
        Station("PRYJ", "Prayagraj Junction", "প্রয়াগরাজ জংশন", "प्रयागराज जंक्शन", "Prayagraj", "প্রয়াগরাজ", "प्रयागराज", "Uttar Pradesh"),
        Station("PURI", "Puri", "পুরী", "पुरी", "Puri", "পুরী", "पुरी", "Odisha"),
        Station("BBS", "Bhubaneswar", "ভুবনেশ্বর", "भुवनेश्वर", "Bhubaneswar", "ভুবনেশ্বর", "भुवनेश्वर", "Odisha"),
        Station("KGP", "Kharagpur Junction", "খড়গপুর জংশন", "खड़गपुर जंक्शन", "Kharagpur", "খড়গপুর", "खड़गपुर", "West Bengal"),
        Station("CSMT", "Chhatrapati Shivaji Maharaj Terminus", "মুম্বাই সিএসএমটি", "मुंबई सीएसएमटी", "Mumbai", "মুম্বাই", "मुंबई", "Maharashtra"),
        Station("NJP", "New Jalpaiguri", "নিউ জলপাইগুড়ি", "न्यू जलपाईगुड़ी", "Siliguri", "শিলিগুড়ি", "सिलीगुड़ी", "West Bengal"),
        Station("GHY", "Guwahati", "গুয়াহাটি", "गुवाहाटी", "Guwahati", "গুয়াহাটি", "गुवाहाटी", "Assam"),
        Station("SBC", "KSR Bengaluru", "কেএসআর বেঙ্গালুরু", "केएसआर बेंगलुरु", "Bengaluru", "বেঙ্গালুরু", "बेंगलुरु", "Karnataka"),
        Station("MAS", "Puratchi Thalaivar Dr. MGR Chennai Central", "চেন্নাই সেন্ট্রাল", "चेन्नई सेंट्रल", "Chennai", "চেন্নাই", "चेन्नई", "Tamil Nadu"),
        Station("ASN", "Asansol Junction", "আসানসোল জংশন", "आसनसोल जंक्शन", "Asansol", "আসানসোল", "आसनसोल", "West Bengal"),
        Station("DHN", "Dhanbad Junction", "ধানবাদ জংশন", "धनबाद जंक्शन", "Dhanbad", "ধানবাদ", "धनबाद", "Jharkhand"),
        Station("GAYA", "Gaya Junction", "গয়া জংশন", "गया जंक्शन", "Gaya", "গয়া", "गया", "Bihar"),
        Station("RNC", "Ranchi Junction", "রাঁচি জংশন", "रांची जंक्शन", "Ranchi", "রাঁচি", "रांची", "Jharkhand")
    )

    fun getStationByCode(code: String): Station {
        return allStations.find { it.code.equals(code, ignoreCase = true) }
            ?: Station(code.uppercase(), code.uppercase(), code.uppercase(), code.uppercase(), code.uppercase(), code.uppercase(), code.uppercase(), "India")
    }

    fun getTrainsBetween(sourceCode: String, destCode: String): List<Train> {
        val s = getStationByCode(sourceCode)
        val d = getStationByCode(destCode)

        return when {
            (sourceCode == "HWH" || sourceCode == "SDAH") && destCode == "NDLS" -> {
                listOf(
                    Train(
                        number = "12301",
                        nameEn = "Howrah - New Delhi Rajdhani Express",
                        nameBn = "হাওড়া - নতুন দিল্লি রাজধানী এক্সপ্রেস",
                        nameHi = "हावड़ा - नई दिल्ली राजधानी एक्सप्रेस",
                        type = "Rajdhani Express",
                        departureStation = s,
                        arrivalStation = d,
                        departureTimeIst = "04:50 PM IST",
                        arrivalTimeIst = "10:05 AM IST",
                        durationHoursMinutesBn = "১৭ ঘণ্টা ১৫ মিনিট",
                        durationHoursMinutesHi = "17 घंटे 15 मिनट",
                        durationHoursMinutesEn = "17 hrs 15 mins",
                        runsOnDaysBn = listOf("সোম", "মঙ্গল", "বুধ", "বৃহ", "শুক্র", "শনি"),
                        runsOnDaysHi = listOf("सोम", "मंगल", "बुध", "गुरु", "शुक्र", "शनि"),
                        runsOnDaysEn = listOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat"),
                        intermediateStations = listOf(
                            StationStop(getStationByCode("ASN"), "07:00 PM IST", "07:02 PM IST", 2, "প্লাটফর্ম ২", "प्लेटफॉर्म 2", "Platform 2", 200),
                            StationStop(getStationByCode("DHN"), "07:55 PM IST", "08:00 PM IST", 5, "প্লাটফর্ম ৩", "प्लेटफॉर्म 3", "Platform 3", 259),
                            StationStop(getStationByCode("GAYA"), "10:30 PM IST", "10:33 PM IST", 3, "প্লাটফর্ম ১", "प्लेटफॉर्म 1", "Platform 1", 458),
                            StationStop(getStationByCode("DDU"), "12:50 AM IST", "01:00 AM IST", 10, "প্লাটফর্ম ৭", "प्लेटफॉर्म 7", "Platform 7", 663),
                            StationStop(getStationByCode("PRYJ"), "02:33 AM IST", "02:35 AM IST", 2, "প্লাটফর্ম ১", "प्लेटफॉर्म 1", "Platform 1", 816),
                            StationStop(getStationByCode("CNB"), "04:50 AM IST", "04:55 AM IST", 5, "প্লাটফর্ম ১", "प्लेटफॉर्म 1", "Platform 1", 1010)
                        ),
                        classAvailabilities = listOf(
                            ClassAvailability(TrainClass.FIRST_AC, "WL 8", false, 4850, 42),
                            ClassAvailability(TrainClass.SECOND_AC, "WL 28", false, 2890, 68),
                            ClassAvailability(TrainClass.THIRD_AC, "WL 74", false, 2040, 51),
                            ClassAvailability(TrainClass.THIRD_AC_ECONOMY, "WL 45", false, 1890, 58)
                        )
                    ),
                    Train(
                        number = "12313",
                        nameEn = "Sealdah - New Delhi Rajdhani Express",
                        nameBn = "শিয়ালদহ - নতুন দিল্লি রাজধানী এক্সপ্রেস",
                        nameHi = "सियालदह - नई दिल्ली राजधानी एक्सप्रेस",
                        type = "Rajdhani Express",
                        departureStation = getStationByCode("SDAH"),
                        arrivalStation = d,
                        departureTimeIst = "04:50 PM IST",
                        arrivalTimeIst = "10:50 AM IST",
                        durationHoursMinutesBn = "১৮ ঘণ্টা ০০ মিনিট",
                        durationHoursMinutesHi = "18 घंटे 00 मिनट",
                        durationHoursMinutesEn = "18 hrs 00 mins",
                        runsOnDaysBn = listOf("প্রতিদিন"),
                        runsOnDaysHi = listOf("प्रतिदिन"),
                        runsOnDaysEn = listOf("Daily"),
                        intermediateStations = listOf(
                            StationStop(getStationByCode("ASN"), "06:56 PM IST", "06:58 PM IST", 2, "প্লাটফর্ম ৩", "प्लेटफॉर्म 3", "Platform 3", 213),
                            StationStop(getStationByCode("DDU"), "01:15 AM IST", "01:25 AM IST", 10, "প্লাটফর্ম ৬", "प्लेटफॉर्म 6", "Platform 6", 670),
                            StationStop(getStationByCode("CNB"), "05:20 AM IST", "05:25 AM IST", 5, "প্লাটফর্ম ১", "प्लेटफॉर्म 1", "Platform 1", 1017)
                        ),
                        classAvailabilities = listOf(
                            ClassAvailability(TrainClass.FIRST_AC, "AVAILABLE 2", true, 4850, 100),
                            ClassAvailability(TrainClass.SECOND_AC, "WL 14", false, 2890, 75),
                            ClassAvailability(TrainClass.THIRD_AC, "WL 52", false, 2040, 60)
                        )
                    ),
                    Train(
                        number = "12259",
                        nameEn = "Sealdah - Bikaner AC Duronto Express",
                        nameBn = "শিয়ালদহ - নতুন দিল্লি এসি দুরন্ত এক্সপ্রেস",
                        nameHi = "सियालदह - नई दिल्ली एसी दुरंतो एक्सप्रेस",
                        type = "Duronto Express",
                        departureStation = getStationByCode("SDAH"),
                        arrivalStation = d,
                        departureTimeIst = "05:00 PM IST",
                        arrivalTimeIst = "11:00 AM IST",
                        durationHoursMinutesBn = "১৮ ঘণ্টা ০০ মিনিট",
                        durationHoursMinutesHi = "18 घंटे 00 मिनट",
                        durationHoursMinutesEn = "18 hrs 00 mins",
                        runsOnDaysBn = listOf("সোম", "বুধ", "বৃহ", "রবি"),
                        runsOnDaysHi = listOf("सोम", "बुध", "गुरु", "रवि"),
                        runsOnDaysEn = listOf("Mon", "Wed", "Thu", "Sun"),
                        intermediateStations = listOf(
                            StationStop(getStationByCode("DHN"), "08:10 PM IST", "08:15 PM IST", 5, "প্লাটফর্ম ২", "प्लेटफॉर्म 2", "Platform 2", 266),
                            StationStop(getStationByCode("DDU"), "01:25 AM IST", "01:35 AM IST", 10, "প্লাটফর্ম ৫", "प्लेटफॉर्म 5", "Platform 5", 670),
                            StationStop(getStationByCode("CNB"), "05:30 AM IST", "05:35 AM IST", 5, "প্লাটফর্ম ১", "प्लेटफॉर्म 1", "Platform 1", 1017)
                        ),
                        classAvailabilities = listOf(
                            ClassAvailability(TrainClass.SECOND_AC, "WL 19", false, 2750, 71),
                            ClassAvailability(TrainClass.THIRD_AC, "WL 64", false, 1980, 54),
                            ClassAvailability(TrainClass.SLEEPER, "REGRET", false, 720, 15)
                        )
                    )
                )
            }
            (sourceCode == "HWH" || sourceCode == "SDAH") && destCode == "PURI" -> {
                listOf(
                    Train(
                        number = "12837",
                        nameEn = "Howrah - Puri Superfast Express",
                        nameBn = "হাওড়া - পুরী সুপারফাস্ট এক্সপ্রেস",
                        nameHi = "हावड़ा - पुरी सुपरफास्ट एक्सप्रेस",
                        type = "Superfast",
                        departureStation = s,
                        arrivalStation = d,
                        departureTimeIst = "10:35 PM IST",
                        arrivalTimeIst = "07:10 AM IST",
                        durationHoursMinutesBn = "০৮ ঘণ্টা ৩৫ মিনিট",
                        durationHoursMinutesHi = "08 घंटे 35 मिनट",
                        durationHoursMinutesEn = "08 hrs 35 mins",
                        runsOnDaysBn = listOf("প্রতিদিন"),
                        runsOnDaysHi = listOf("प्रतिदिन"),
                        runsOnDaysEn = listOf("Daily"),
                        intermediateStations = listOf(
                            StationStop(getStationByCode("KGP"), "12:15 AM IST", "12:20 AM IST", 5, "প্লাটফর্ম ৩", "प्लेटफॉर्म 3", "Platform 3", 115),
                            StationStop(getStationByCode("BBS"), "05:05 AM IST", "05:10 AM IST", 5, "প্লাটফর্ম ৪", "प्लेटफॉर्म 4", "Platform 4", 437)
                        ),
                        classAvailabilities = listOf(
                            ClassAvailability(TrainClass.FIRST_AC, "WL 4", false, 2100, 65),
                            ClassAvailability(TrainClass.SECOND_AC, "WL 18", false, 1250, 78),
                            ClassAvailability(TrainClass.THIRD_AC, "WL 42", false, 890, 55),
                            ClassAvailability(TrainClass.SLEEPER, "WL 98", false, 340, 40)
                        )
                    ),
                    Train(
                        number = "22895",
                        nameEn = "Howrah - Puri Vande Bharat Express",
                        nameBn = "হাওড়া - পুরী বন্দে ভারত এক্সপ্রেস",
                        nameHi = "हावड़ा - पुरी वंदे भारत एक्सप्रेस",
                        type = "Vande Bharat",
                        departureStation = s,
                        arrivalStation = d,
                        departureTimeIst = "06:10 AM IST",
                        arrivalTimeIst = "12:35 PM IST",
                        durationHoursMinutesBn = "০৬ ঘণ্টা ২৫ মিনিট",
                        durationHoursMinutesHi = "06 घंटे 25 मिनट",
                        durationHoursMinutesEn = "06 hrs 25 mins",
                        runsOnDaysBn = listOf("সোম", "মঙ্গল", "বুধ", "শুক্র", "শনি", "রবি"),
                        runsOnDaysHi = listOf("सोम", "मंगल", "बुध", "शुक्र", "शनि", "रवि"),
                        runsOnDaysEn = listOf("Mon", "Tue", "Wed", "Fri", "Sat", "Sun"),
                        intermediateStations = listOf(
                            StationStop(getStationByCode("KGP"), "07:38 AM IST", "07:40 AM IST", 2, "প্লাটফর্ম ২", "प्लेटफॉर्म 2", "Platform 2", 115),
                            StationStop(getStationByCode("BBS"), "11:06 AM IST", "11:08 AM IST", 2, "প্লাটফর্ম ১", "प्लेटफॉर्म 1", "Platform 1", 437)
                        ),
                        classAvailabilities = listOf(
                            ClassAvailability(TrainClass.EXEC_CHAIR_CAR, "AVAILABLE 8", true, 2420, 100),
                            ClassAvailability(TrainClass.CHAIR_CAR, "WL 22", false, 1265, 70)
                        )
                    )
                )
            }
            sourceCode == "HWH" && destCode == "NJP" -> {
                listOf(
                    Train(
                        number = "22301",
                        nameEn = "Howrah - New Jalpaiguri Vande Bharat Express",
                        nameBn = "হাওড়া - নিউ জলপাইগুড়ি বন্দে ভারত এক্সপ্রেস",
                        nameHi = "हावड़ा - न्यू जलपाईगुड़ी वंदे भारत एक्सप्रेस",
                        type = "Vande Bharat",
                        departureStation = s,
                        arrivalStation = d,
                        departureTimeIst = "05:55 AM IST",
                        arrivalTimeIst = "01:25 PM IST",
                        durationHoursMinutesBn = "০৭ ঘণ্টা ৩০ মিনিট",
                        durationHoursMinutesHi = "07 घंटे 30 मिनट",
                        durationHoursMinutesEn = "07 hrs 30 mins",
                        runsOnDaysBn = listOf("সোম", "মঙ্গল", "বুধ", "বৃহ", "শুক্র", "রবি"),
                        runsOnDaysHi = listOf("सोम", "मंगल", "बुध", "गुरु", "शुक्र", "रवि"),
                        runsOnDaysEn = listOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sun"),
                        intermediateStations = listOf(
                            StationStop(getStationByCode("ASN"), "07:40 AM IST", "07:42 AM IST", 2, "প্লাটফর্ম ১", "प्लेटफॉर्म 1", "Platform 1", 200)
                        ),
                        classAvailabilities = listOf(
                            ClassAvailability(TrainClass.EXEC_CHAIR_CAR, "WL 6", false, 2825, 62),
                            ClassAvailability(TrainClass.CHAIR_CAR, "WL 38", false, 1565, 50)
                        )
                    )
                )
            }
            else -> {
                listOf(
                    Train(
                        number = "12841",
                        nameEn = "${s.nameEn} - ${d.nameEn} Superfast Express",
                        nameBn = "${s.nameBn} - ${d.nameBn} সুপারফাস্ট এক্সপ্রেস",
                        nameHi = "${s.nameHi} - ${d.nameHi} सुपरफास्ट एक्सप्रेस",
                        type = "Superfast",
                        departureStation = s,
                        arrivalStation = d,
                        departureTimeIst = "08:15 AM IST",
                        arrivalTimeIst = "09:30 PM IST",
                        durationHoursMinutesBn = "১৩ ঘণ্টা ১৫ মিনিট",
                        durationHoursMinutesHi = "13 घंटे 15 मिनट",
                        durationHoursMinutesEn = "13 hrs 15 mins",
                        runsOnDaysBn = listOf("প্রতিদিন"),
                        runsOnDaysHi = listOf("प्रतिदिन"),
                        runsOnDaysEn = listOf("Daily"),
                        intermediateStations = listOf(
                            StationStop(getStationByCode("KGP"), "10:00 AM IST", "10:05 AM IST", 5, "প্লাটফর্ম ২", "प्लेटफॉर्म 2", "Platform 2", 115)
                        ),
                        classAvailabilities = listOf(
                            ClassAvailability(TrainClass.SECOND_AC, "WL 12", false, 1850, 76),
                            ClassAvailability(TrainClass.THIRD_AC, "WL 35", false, 1290, 64),
                            ClassAvailability(TrainClass.SLEEPER, "AVAILABLE 18", true, 485, 100)
                        )
                    )
                )
            }
        }
    }

    fun calculateSplitTickets(sourceCode: String, destCode: String, date: String): List<SplitTicketOption> {
        val s = getStationByCode(sourceCode)
        val d = getStationByCode(destCode)
        val options = mutableListOf<SplitTicketOption>()

        if ((sourceCode == "HWH" || sourceCode == "SDAH") && destCode == "NDLS") {
            val ddu = getStationByCode("DDU")
            options.add(
                SplitTicketOption(
                    id = "split-12301-ddu-2a-1a",
                    trainNumber = "12301",
                    trainNameBn = "১২৩০১ হাওড়া - নতুন দিল্লি রাজধানী এক্সপ্রেস",
                    trainNameHi = "12301 हावड़ा - नई दिल्ली राजधानी एक्सप्रेस",
                    trainNameEn = "12301 Howrah - New Delhi Rajdhani Express",
                    splitType = SplitType.SAME_TRAIN_SEAT_SWITCH,
                    intermediateStation = ddu,
                    segment1 = TicketSegment(
                        trainNumber = "12301",
                        trainNameBn = "১২৩০১ রাজধানী এক্সপ্রেস",
                        trainNameHi = "12301 राजधानी एक्सप्रेस",
                        trainNameEn = "12301 Rajdhani Express",
                        fromStation = s,
                        toStation = ddu,
                        departureTimeIst = "04:50 PM IST",
                        arrivalTimeIst = "12:50 AM IST",
                        trainClass = TrainClass.SECOND_AC,
                        seatBerthInfoBn = "কোচ A2, বার্থ 18 (লোয়ার বার্থ)",
                        seatBerthInfoHi = "कोच A2, बर्थ 18 (लोअर बर्थ)",
                        seatBerthInfoEn = "Coach A2, Berth 18 (Lower Berth)",
                        statusTextBn = "নিশ্চিত (CNF)",
                        statusTextHi = "कन्फर्म (CNF)",
                        statusTextEn = "Confirmed (CNF)",
                        fare = 1940
                    ),
                    segment2 = TicketSegment(
                        trainNumber = "12301",
                        trainNameBn = "১২৩০১ রাজধানী এক্সপ্রেস",
                        trainNameHi = "12301 राजधानी एक्सप्रेस",
                        trainNameEn = "12301 Rajdhani Express",
                        fromStation = ddu,
                        toStation = d,
                        departureTimeIst = "01:00 AM IST",
                        arrivalTimeIst = "10:05 AM IST",
                        trainClass = TrainClass.FIRST_AC,
                        seatBerthInfoBn = "কোচ H1, কেবিন B বার্থ 03 (আপার)",
                        seatBerthInfoHi = "कोच H1, केबिन B बर्थ 03 (अपर)",
                        seatBerthInfoEn = "Coach H1, Cabin B Berth 03 (Upper)",
                        statusTextBn = "নিশ্চিত (CNF)",
                        statusTextHi = "कन्फर्म (CNF)",
                        statusTextEn = "Confirmed (CNF)",
                        fare = 2520
                    ),
                    totalFare = 4460,
                    comparisonBn = "সরাসরি টিকিট: WL ২৮ ❌ | স্প্লিট-টিকিটে ১০০% নিশ্চিত কনফার্ম সিট! ট্রেন পরিবর্তনের ঝামেলা নেই, DDU স্টেশনে বার্থ বদল।",
                    comparisonHi = "सीधा टिकट: WL 28 ❌ | स्प्लिट-टिकट में 100% कन्फर्म सीट! ट्रेन बदलने की जरूरत नहीं, केवल DDU पर बर्थ बदलें।",
                    comparisonEn = "Direct ticket: WL 28 ❌ | Split-ticket gives 100% Confirmed Seat! No train change needed, just switch berths at DDU.",
                    layoverMinutes = 10,
                    overallConfirmationPercent = 100
                )
            )

            val gaya = getStationByCode("GAYA")
            options.add(
                SplitTicketOption(
                    id = "split-12301-gaya-3a-2a",
                    trainNumber = "12301",
                    trainNameBn = "১২৩০১ হাওড়া - নতুন দিল্লি রাজধানী এক্সপ্রেস",
                    trainNameHi = "12301 हावड़ा - नई दिल्ली राजधानी एक्सप्रेस",
                    trainNameEn = "12301 Howrah - New Delhi Rajdhani Express",
                    splitType = SplitType.SAME_TRAIN_SEAT_SWITCH,
                    intermediateStation = gaya,
                    segment1 = TicketSegment(
                        trainNumber = "12301",
                        trainNameBn = "১২৩০১ রাজধানী এক্সপ্রেস",
                        trainNameHi = "12301 राजधानी एक्सप्रेस",
                        trainNameEn = "12301 Rajdhani Express",
                        fromStation = s,
                        toStation = gaya,
                        departureTimeIst = "04:50 PM IST",
                        arrivalTimeIst = "10:30 PM IST",
                        trainClass = TrainClass.THIRD_AC,
                        seatBerthInfoBn = "কোচ B4, বার্থ 35 (সাইড লোয়ার)",
                        seatBerthInfoHi = "कोच B4, बर्थ 35 (साइड लोअर)",
                        seatBerthInfoEn = "Coach B4, Berth 35 (Side Lower)",
                        statusTextBn = "নিশ্চিত (CNF)",
                        statusTextHi = "कन्फर्म (CNF)",
                        statusTextEn = "Confirmed (CNF)",
                        fare = 1120
                    ),
                    segment2 = TicketSegment(
                        trainNumber = "12301",
                        trainNameBn = "১২৩০১ রাজধানী এক্সপ্রেস",
                        trainNameHi = "12301 राजधानी एक्सप्रेस",
                        trainNameEn = "12301 Rajdhani Express",
                        fromStation = gaya,
                        toStation = d,
                        departureTimeIst = "10:33 PM IST",
                        arrivalTimeIst = "10:05 AM IST",
                        trainClass = TrainClass.SECOND_AC,
                        seatBerthInfoBn = "কোচ A1, বার্থ 22 (সাইড আপার)",
                        seatBerthInfoHi = "कोच A1, बर्थ 22 (साइड अपर)",
                        seatBerthInfoEn = "Coach A1, Berth 22 (Side Upper)",
                        statusTextBn = "নিশ্চিত (CNF)",
                        statusTextHi = "कन्फर्म (CNF)",
                        statusTextEn = "Confirmed (CNF)",
                        fare = 2240
                    ),
                    totalFare = 3360,
                    comparisonBn = "সরাসরি টিকিট: WL ৭৪ ❌ | স্প্লিট-টিকিটে ১০০% নিশ্চিত আসন (3A + 2A)। একই ট্রেনে গয়া স্টেশনে সিট পরিবর্তন।",
                    comparisonHi = "सीधा टिकट: WL 74 ❌ | स्प्लिट-टिकट में 100% कन्फर्म सीट (3A + 2A)। गया स्टेशन पर सीट बदलें।",
                    comparisonEn = "Direct ticket: WL 74 ❌ | Split-ticket gives 100% Confirmed Seat (3A + 2A). Same train seat switch at Gaya.",
                    layoverMinutes = 3,
                    overallConfirmationPercent = 100
                )
            )
        } else {
            val intermediate = getStationByCode("KGP")
            options.add(
                SplitTicketOption(
                    id = "split-generic-${sourceCode}-${destCode}",
                    trainNumber = "12837",
                    trainNameBn = "১২৮৩৭ সুপারফাস্ট এক্সপ্রেস (আসন পরিবর্তন)",
                    trainNameHi = "12837 सुपरफास्ट एक्सप्रेस (सीट परिवर्तन)",
                    trainNameEn = "12837 Superfast Express (Seat Switch)",
                    splitType = SplitType.SAME_TRAIN_SEAT_SWITCH,
                    intermediateStation = intermediate,
                    segment1 = TicketSegment(
                        trainNumber = "12837",
                        trainNameBn = "১২৮৩৭ এক্সপ্রেস",
                        trainNameHi = "12837 एक्सप्रेस",
                        trainNameEn = "12837 Express",
                        fromStation = s,
                        toStation = intermediate,
                        departureTimeIst = "10:35 PM IST",
                        arrivalTimeIst = "12:15 AM IST",
                        trainClass = TrainClass.THIRD_AC,
                        seatBerthInfoBn = "কোচ B1, বার্থ 25",
                        seatBerthInfoHi = "कोच B1, बर्थ 25",
                        seatBerthInfoEn = "Coach B1, Berth 25",
                        statusTextBn = "নিশ্চিত (CNF)",
                        statusTextHi = "कन्फर्म (CNF)",
                        statusTextEn = "Confirmed (CNF)",
                        fare = 420
                    ),
                    segment2 = TicketSegment(
                        trainNumber = "12837",
                        trainNameBn = "১২৮৩৭ এক্সপ্রেস",
                        trainNameHi = "12837 एक्सप्रेस",
                        trainNameEn = "12837 Express",
                        fromStation = intermediate,
                        toStation = d,
                        departureTimeIst = "12:20 AM IST",
                        arrivalTimeIst = "07:10 AM IST",
                        trainClass = TrainClass.SECOND_AC,
                        seatBerthInfoBn = "কোচ A2, বার্থ 08",
                        seatBerthInfoHi = "कोच A2, बर्थ 08",
                        seatBerthInfoEn = "Coach A2, Berth 08",
                        statusTextBn = "নিশ্চিত (CNF)",
                        statusTextHi = "कन्फर्म (CNF)",
                        statusTextEn = "Confirmed (CNF)",
                        fare = 1120
                    ),
                    totalFare = 1540,
                    comparisonBn = "সরাসরি কোটা ফাঁকা নেই। ${intermediate.nameBn} জংশনে সিট বদল করে নিশ্চিত টিকিট!",
                    comparisonHi = "सीधा कोटा उपलब्ध नहीं है। ${intermediate.nameHi} जंक्शन पर सीट बदलकर कन्फर्म टिकट पाएं!",
                    comparisonEn = "Direct quota unavailable. Switch seats at ${intermediate.nameEn} for confirmed ticket!",
                    layoverMinutes = 5,
                    overallConfirmationPercent = 98
                )
            )
        }
        return options
    }

    fun checkPnrStatus(pnr: String): PnrDetails {
        val cleanPnr = pnr.trim().replace("-", "").replace(" ", "")

        when (cleanPnr) {
            "6428190342" -> {
                return PnrDetails(
                    pnrNumber = "6428190342",
                    trainNumber = "12301",
                    trainNameBn = "১২৩০১ হাওড়া - নতুন দিল্লি রাজধানী এক্সপ্রেস",
                    trainNameHi = "12301 हावड़ा - नई दिल्ली राजधानी एक्सप्रेस",
                    trainNameEn = "12301 Howrah - New Delhi Rajdhani Express",
                    fromStation = getStationByCode("HWH"),
                    toStation = getStationByCode("NDLS"),
                    journeyDateIstBn = "২৮ সেপ্টেম্বর ২০২৬",
                    journeyDateIstHi = "28 सितंबर 2026",
                    journeyDateIstEn = "28 Sep 2026",
                    departureTimeIst = "04:50 PM IST",
                    arrivalTimeIst = "10:05 AM IST",
                    trainClass = TrainClass.SECOND_AC,
                    chartStatusBn = "চার্ট তৈরি হয়নি (প্রস্তুতি বাকি)",
                    chartStatusHi = "चार्ट नहीं बना (प्रक्रियाधीन)",
                    chartStatusEn = "Chart Not Prepared",
                    isChartPrepared = false,
                    confirmationProbabilityPercent = 94,
                    confirmationLevelBn = "খুব বেশি সম্ভাবনা (High)",
                    confirmationLevelHi = "बहुत अधिक संभावना (High)",
                    confirmationLevelEn = "High Probability",
                    passengers = listOf(
                        Passenger(1, "যাত্রী ১ (পুরুষ, ৩৫)", "यात्री 1 (पुरुष, 35)", "Passenger 1 (M, 35)", "GNWL 14", "WL 2", "W/L", "2", "ওয়েটিংলিস্ট ২", "वेटिंग लिस्ट 2", "Waiting List 2"),
                        Passenger(2, "যাত্রী ২ (মহিলা, ৩২)", "यात्री 2 (महिला, 32)", "Passenger 2 (F, 32)", "GNWL 15", "WL 3", "W/L", "3", "ওয়েটিংলিস্ট ৩", "वेटिंग लिस्ट 3", "Waiting List 3")
                    ),
                    aiPredictionAdviceBn = "নিশ্চিত হওয়ার সম্ভাবনা ৯৪%। ঐতিহাসিক তথ্য অনুযায়ী রাজধানী এক্সপ্রেসে চার্ট তৈরির সময় (দুপুর ১২:৫০ PM IST) আপনার আসন নিশ্চিত হয়ে যাবে।",
                    aiPredictionAdviceHi = "कन्फर्म होने की संभावना 94% है। राजधानी एक्सप्रेस में चार्ट बनने के समय (दोपहर 12:50 PM IST) आपकी सीट कन्फर्म होने की पूरी उम्मीद है।",
                    aiPredictionAdviceEn = "94% confirmation probability. Historical data indicates your ticket is very likely to confirm when the chart is prepared around 12:50 PM IST."
                )
            }
            "2841957201" -> {
                return PnrDetails(
                    pnrNumber = "2841957201",
                    trainNumber = "12837",
                    trainNameBn = "১২৮৩৭ হাওড়া - পুরী সুপারফাস্ট এক্সপ্রেস",
                    trainNameHi = "12837 हावड़ा - पुरी सुपरफास्ट एक्सप्रेस",
                    trainNameEn = "12837 Howrah - Puri Superfast Express",
                    fromStation = getStationByCode("HWH"),
                    toStation = getStationByCode("PURI"),
                    journeyDateIstBn = "২৯ সেপ্টেম্বর ২০২৬",
                    journeyDateIstHi = "29 सितंबर 2026",
                    journeyDateIstEn = "29 Sep 2026",
                    departureTimeIst = "10:35 PM IST",
                    arrivalTimeIst = "07:10 AM IST",
                    trainClass = TrainClass.THIRD_AC,
                    chartStatusBn = "চার্ট তৈরি হয়নি",
                    chartStatusHi = "चार्ट नहीं बना",
                    chartStatusEn = "Chart Not Prepared",
                    isChartPrepared = false,
                    confirmationProbabilityPercent = 98,
                    confirmationLevelBn = "সুনিশ্চিত সম্ভাবনা (Very High)",
                    confirmationLevelHi = "अति उच्च संभावना (Very High)",
                    confirmationLevelEn = "Very High Probability",
                    passengers = listOf(
                        Passenger(1, "যাত্রী ১ (পুরুষ, ৪২)", "यात्री 1 (पुरुष, 42)", "Passenger 1 (M, 42)", "WL 8", "RAC 4", "RAC", "4", "আরএসি আসন (Side Lower)", "आरएसी सीट (Side Lower)", "RAC Seat (Side Lower)")
                    ),
                    aiPredictionAdviceBn = "আপনার টিকিট বর্তমানে RAC ৪ এ আছে। চার্ট তৈরি হলে পূর্ণাঙ্গ বার্থ কনফার্ম হওয়ার সম্ভাবনা ৯৮%।",
                    aiPredictionAdviceHi = "आपका टिकट वर्तमान में RAC 4 पर है। चार्ट बनने पर पूर्ण कन्फर्म बर्थ मिलने की 98% संभावना है।",
                    aiPredictionAdviceEn = "Your ticket is currently at RAC 4. There is a 98% chance of full berth confirmation upon chart preparation."
                )
            }
            "8923014756" -> {
                return PnrDetails(
                    pnrNumber = "8923014756",
                    trainNumber = "22301",
                    trainNameBn = "২২৩০১ হাওড়া - এনজেপি বন্দে ভারত এক্সপ্রেস",
                    trainNameHi = "22301 हावड़ा - एनजेपी वंदे भारत एक्सप्रेस",
                    trainNameEn = "22301 Howrah - NJP Vande Bharat Express",
                    fromStation = getStationByCode("HWH"),
                    toStation = getStationByCode("NJP"),
                    journeyDateIstBn = "২৭ সেপ্টেম্বর ২০২৬",
                    journeyDateIstHi = "27 सितंबर 2026",
                    journeyDateIstEn = "27 Sep 2026",
                    departureTimeIst = "05:55 AM IST",
                    arrivalTimeIst = "01:25 PM IST",
                    trainClass = TrainClass.CHAIR_CAR,
                    chartStatusBn = "চার্ট প্রস্তুত সম্পন্ন হয়েছে",
                    chartStatusHi = "चार्ट तैयार हो चुका है",
                    chartStatusEn = "Chart Prepared",
                    isChartPrepared = true,
                    confirmationProbabilityPercent = 100,
                    confirmationLevelBn = "নিশ্চিত (Confirmed)",
                    confirmationLevelHi = "कन्फर्म (Confirmed)",
                    confirmationLevelEn = "Confirmed",
                    passengers = listOf(
                        Passenger(1, "যাত্রী ১ (পুরুষ, ২৯)", "यात्री 1 (पुरुष, 29)", "Passenger 1 (M, 29)", "CNF", "CNF C4-32", "C4", "32", "উইন্ডো সিট (Window)", "विंडो सीट (Window)", "Window Seat"),
                        Passenger(2, "যাত্রী ২ (মহিলা, ২৭)", "यात्री 2 (महिला, 27)", "Passenger 2 (F, 27)", "CNF", "CNF C4-33", "C4", "33", "মিডল সিট (Middle)", "मिडिल सीट (Middle)", "Middle Seat")
                    ),
                    aiPredictionAdviceBn = "অভিনন্দন! আপনার বন্দে ভারত এক্সপ্রেসের টিকিট সম্পূর্ণ নিশ্চিত (CNF)। শুভ যাত্রা!",
                    aiPredictionAdviceHi = "बधाई हो! आपका वंदे भारत एक्सप्रेस का टिकट पूरी तरह कन्फर्म (CNF) है। शुभ यात्रा!",
                    aiPredictionAdviceEn = "Congratulations! Your Vande Bharat Express ticket is fully confirmed (CNF). Have a pleasant journey!"
                )
            }
        }

        // Generic generator for any PNR
        val hash = cleanPnr.hashCode().let { if (it < 0) -it else it }
        val probability = 40 + (hash % 58)
        val isConfirmed = probability >= 90
        val isRac = !isConfirmed && probability >= 75
        val wlNum = (hash % 18) + 1

        val currentStatus = when {
            isConfirmed -> "CNF B3-${(hash % 64) + 1}"
            isRac -> "RAC ${(hash % 12) + 1}"
            else -> "WL $wlNum"
        }

        return PnrDetails(
            pnrNumber = if (cleanPnr.length == 10) cleanPnr else "6428190342",
            trainNumber = "12301",
            trainNameBn = "১২৩০১ হাওড়া - নতুন দিল্লি রাজধানী এক্সপ্রেস",
            trainNameHi = "12301 हावड़ा - नई दिल्ली राजधानी एक्सप्रेस",
            trainNameEn = "12301 Howrah - New Delhi Rajdhani Express",
            fromStation = getStationByCode("HWH"),
            toStation = getStationByCode("NDLS"),
            journeyDateIstBn = "২৮ সেপ্টেম্বর ২০২৬",
            journeyDateIstHi = "28 सितंबर 2026",
            journeyDateIstEn = "28 Sep 2026",
            departureTimeIst = "04:50 PM IST",
            arrivalTimeIst = "10:05 AM IST",
            trainClass = TrainClass.SECOND_AC,
            chartStatusBn = "চার্ট তৈরি হয়নি (প্রস্তুতি বাকি)",
            chartStatusHi = "चार्ट नहीं बना (प्रक्रियाधीन)",
            chartStatusEn = "Chart Not Prepared",
            isChartPrepared = false,
            confirmationProbabilityPercent = probability,
            confirmationLevelBn = if (probability >= 85) "খুব বেশি সম্ভাবনা (High)" else if (probability >= 65) "মাঝারি সম্ভাবনা (Medium)" else "কম সম্ভাবনা (Low)",
            confirmationLevelHi = if (probability >= 85) "उच्च संभावना (High)" else if (probability >= 65) "मध्यम संभावना (Medium)" else "कम संभावना (Low)",
            confirmationLevelEn = if (probability >= 85) "High Probability" else if (probability >= 65) "Medium Probability" else "Low Probability",
            passengers = listOf(
                Passenger(
                    1,
                    "যাত্রী ১",
                    "यात्री 1",
                    "Passenger 1",
                    "GNWL ${wlNum + 8}",
                    currentStatus,
                    if (isConfirmed) "B3" else "W/L",
                    if (isConfirmed) "${(hash % 64) + 1}" else "$wlNum",
                    if (isConfirmed) "লোয়ার বার্থ" else "ওয়েটিংলিস্ট",
                    if (isConfirmed) "लोअर बर्थ" else "वेटिंग लिस्ट",
                    if (isConfirmed) "Lower Berth" else "Waiting List"
                )
            ),
            aiPredictionAdviceBn = "টিকিট নিশ্চিত হওয়ার সম্ভাবনা $probability%। চার্ট তৈরির সময় লক্ষ্য রাখুন।",
            aiPredictionAdviceHi = "टिकट कन्फर्म होने की संभावना $probability% है। चार्ट बनने के समय पर नज़र रखें।",
            aiPredictionAdviceEn = "Confirmation probability is $probability%. Keep track of chart preparation status."
        )
    }

    fun getLiveRunningStatus(trainNumber: String): LiveTrainStatus {
        return when (trainNumber) {
            "22301" -> {
                LiveTrainStatus(
                    trainNumber = "22301",
                    trainNameBn = "২২৩০১ হাওড়া - এনজেপি বন্দে ভারত এক্সপ্রেস",
                    trainNameHi = "22301 हावड़ा - एनजेपी वंदे भारत एक्सप्रेस",
                    trainNameEn = "Howrah - New Jalpaiguri Vande Bharat Express",
                    sourceStation = getStationByCode("HWH"),
                    destinationStation = getStationByCode("NJP"),
                    currentStationNameBn = "মালদা টাউন অতিক্রম করছে",
                    currentStationNameHi = "मालदा टाउन पार कर रही है",
                    currentStationNameEn = "Crossing Malda Town",
                    nextStationNameBn = "বারসোই জংশন (BOE)",
                    nextStationNameHi = "बारसोई जंक्शन (BOE)",
                    nextStationNameEn = "Barsoi Junction (BOE)",
                    distanceToNextKm = 48,
                    currentSpeedKmph = 124,
                    delayMinutes = 0,
                    delayStatusBn = "সঠিক সময়ে চলছে (Right Time)",
                    delayStatusHi = "समय पर चल रही है (Right Time)",
                    delayStatusEn = "Running on Time (Right Time)",
                    isRunningOnTime = true,
                    lastUpdatedIst = IstTimeUtil.getCurrentIstTimeString(),
                    timeline = listOf(
                        LiveStationStop(getStationByCode("HWH"), "05:55 AM IST", "05:55 AM IST", "05:55 AM IST", "05:55 AM IST", "প্লাটফর্ম ১০", "प्लेटफॉर्म 10", "Platform 10", StopStatus.DEPARTED, 0),
                        LiveStationStop(getStationByCode("ASN"), "07:40 AM IST", "07:42 AM IST", "07:40 AM IST", "07:42 AM IST", "প্লাটফর্ম ১", "प्लेटफॉर्म 1", "Platform 1", StopStatus.DEPARTED, 0),
                        LiveStationStop(Station("MLDT", "Malda Town", "মালদা টাউন", "मालदा टाउन", "Malda", "মালদা", "मालदा", "WB"), "10:32 AM IST", "10:35 AM IST", "10:32 AM IST", "10:35 AM IST", "প্লাটফর্ম ১", "प्लेटफॉर्म 1", "Platform 1", StopStatus.DEPARTED, 0),
                        LiveStationStop(Station("BOE", "Barsoi Junction", "বারসোই জংশন", "बारसोई जंक्शन", "Barsoi", "বারসোই", "बारसोई", "Bihar"), "11:50 AM IST", "11:52 AM IST", "11:50 AM IST", "11:52 AM IST", "প্লাটফর্ম ২", "प्लेटफॉर्म 2", "Platform 2", StopStatus.UPCOMING, 0),
                        LiveStationStop(getStationByCode("NJP"), "01:25 PM IST", "01:25 PM IST", "01:25 PM IST", "01:25 PM IST", "প্লাটফর্ম ১A", "प्लेटफॉर्म 1A", "Platform 1A", StopStatus.UPCOMING, 0)
                    )
                )
            }
            "12837" -> {
                LiveTrainStatus(
                    trainNumber = "12837",
                    trainNameBn = "১২৮৩৭ হাওড়া - পুরী সুপারফাস্ট এক্সপ্রেস",
                    trainNameHi = "12837 हावड़ा - पुरी सुपरफास्ट एक्सप्रेस",
                    trainNameEn = "Howrah - Puri Superfast Express",
                    sourceStation = getStationByCode("HWH"),
                    destinationStation = getStationByCode("PURI"),
                    currentStationNameBn = "ভদ্রক অতিক্রম করেছে, কটকের দিকে অগ্রসর",
                    currentStationNameHi = "भद्रक पार किया, कटक की ओर अग्रसर",
                    currentStationNameEn = "Departed Bhadrak, proceeding to Cuttack",
                    nextStationNameBn = "কটক জংশন (CTC)",
                    nextStationNameHi = "कटक जंक्शन (CTC)",
                    nextStationNameEn = "Cuttack Junction (CTC)",
                    distanceToNextKm = 36,
                    currentSpeedKmph = 98,
                    delayMinutes = 12,
                    delayStatusBn = "১২ মিনিট দেরিতে চলছে",
                    delayStatusHi = "12 मिनट देरी से चल रही है",
                    delayStatusEn = "Running 12 mins late",
                    isRunningOnTime = false,
                    lastUpdatedIst = IstTimeUtil.getCurrentIstTimeString(),
                    timeline = listOf(
                        LiveStationStop(getStationByCode("HWH"), "10:35 PM IST", "10:35 PM IST", "10:35 PM IST", "10:35 PM IST", "প্লাটফর্ম ১৮", "प्लेटफॉर्म 18", "Platform 18", StopStatus.DEPARTED, 0),
                        LiveStationStop(getStationByCode("KGP"), "12:15 AM IST", "12:20 AM IST", "12:22 AM IST", "12:26 AM IST", "প্লাটফর্ম ৩", "प्लेटफॉर्म 3", "Platform 3", StopStatus.DEPARTED, 6),
                        LiveStationStop(Station("CTC", "Cuttack Junction", "কটক জংশন", "कटक जंक्शन", "Cuttack", "কটক", "कटक", "Odisha"), "04:15 AM IST", "04:20 AM IST", "04:27 AM IST", "04:32 AM IST", "প্লাটফর্ম ৪", "प्लेटफॉर्म 4", "Platform 4", StopStatus.UPCOMING, 12),
                        LiveStationStop(getStationByCode("PURI"), "07:10 AM IST", "07:10 AM IST", "07:22 AM IST", "07:22 AM IST", "প্লাটফর্ম ৬", "प्लेटफॉर्म 6", "Platform 6", StopStatus.UPCOMING, 12)
                    )
                )
            }
            else -> {
                LiveTrainStatus(
                    trainNumber = "12301",
                    trainNameBn = "১২৩০১ হাওড়া - নতুন দিল্লি রাজধানী এক্সপ্রেস",
                    trainNameHi = "12301 हावड़ा - नई दिल्ली राजधानी एक्सप्रेस",
                    trainNameEn = "Howrah - New Delhi Rajdhani Express",
                    sourceStation = getStationByCode("HWH"),
                    destinationStation = getStationByCode("NDLS"),
                    currentStationNameBn = "পণ্ডিত দীনদয়াল উপাধ্যায় (DDU) জংশনে বিরতি",
                    currentStationNameHi = "पं. दीनदयाल उपाध्याय (DDU) जंक्शन पर ठहराव",
                    currentStationNameEn = "Halting at Pt. Deen Dayal Upadhyaya (DDU) Jn",
                    nextStationNameBn = "প্রয়াগরাজ জংশন (PRYJ)",
                    nextStationNameHi = "प्रयागराज जंक्शन (PRYJ)",
                    nextStationNameEn = "Prayagraj Junction (PRYJ)",
                    distanceToNextKm = 153,
                    currentSpeedKmph = 0,
                    delayMinutes = 8,
                    delayStatusBn = "৮ মিনিট দেরিতে চলছে",
                    delayStatusHi = "8 मिनट देरी से चल रही है",
                    delayStatusEn = "Running 8 mins late",
                    isRunningOnTime = false,
                    lastUpdatedIst = IstTimeUtil.getCurrentIstTimeString(),
                    timeline = listOf(
                        LiveStationStop(getStationByCode("HWH"), "04:50 PM IST", "04:50 PM IST", "04:50 PM IST", "04:50 PM IST", "প্লাটফর্ম ৯", "प्लेटफॉर्म 9", "Platform 9", StopStatus.DEPARTED, 0),
                        LiveStationStop(getStationByCode("ASN"), "07:00 PM IST", "07:02 PM IST", "07:03 PM IST", "07:05 PM IST", "প্লাটফর্ম ২", "प्लेटफॉर्म 2", "Platform 2", StopStatus.DEPARTED, 3),
                        LiveStationStop(getStationByCode("DHN"), "07:55 PM IST", "08:00 PM IST", "08:00 PM IST", "08:04 PM IST", "প্লাটফর্ম ৩", "प्लेटफॉर्म 3", "Platform 3", StopStatus.DEPARTED, 4),
                        LiveStationStop(getStationByCode("GAYA"), "10:30 PM IST", "10:33 PM IST", "10:35 PM IST", "10:38 PM IST", "প্লাটফর্ম ১", "प्लेटफॉर्म 1", "Platform 1", StopStatus.DEPARTED, 5),
                        LiveStationStop(getStationByCode("DDU"), "12:50 AM IST", "01:00 AM IST", "12:58 AM IST", "01:08 AM IST", "প্লাটফর্ম ৭", "प्लेटफॉर्म 7", "Platform 7", StopStatus.CURRENT, 8),
                        LiveStationStop(getStationByCode("PRYJ"), "02:33 AM IST", "02:35 AM IST", "02:41 AM IST", "02:43 AM IST", "প্লাটফর্ম ১", "प्लेटफॉर्म 1", "Platform 1", StopStatus.UPCOMING, 8),
                        LiveStationStop(getStationByCode("CNB"), "04:50 AM IST", "04:55 AM IST", "04:58 AM IST", "05:03 AM IST", "প্লাটফর্ম ১", "प्लेटफॉर्म 1", "Platform 1", StopStatus.UPCOMING, 8),
                        LiveStationStop(getStationByCode("NDLS"), "10:05 AM IST", "10:05 AM IST", "10:13 AM IST", "10:13 AM IST", "প্লাটফর্ম ১২", "प्लेटफॉर्म 12", "Platform 12", StopStatus.UPCOMING, 8)
                    )
                )
            }
        }
    }
}
