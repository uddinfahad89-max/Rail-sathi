package com.example.ui.util

import com.example.data.model.AppLanguage

object AppStrings {

    fun appTitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.BENGALI -> "রেলসখা (RailSathi)"
        AppLanguage.HINDI -> "रेलसखा (RailSathi)"
        AppLanguage.ENGLISH -> "RailSathi"
    }

    fun appSubtitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.BENGALI -> "ভারতীয় রেল ভ্রমণ সহায়ক"
        AppLanguage.HINDI -> "भारतीय रेल यात्रा सहायक"
        AppLanguage.ENGLISH -> "Indian Railways Travel Planner"
    }

    // Tabs
    fun tabSearch(lang: AppLanguage): String = when (lang) {
        AppLanguage.BENGALI -> "ট্রেন ও স্প্লিট"
        AppLanguage.HINDI -> "ट्रेन व स्प्लिट"
        AppLanguage.ENGLISH -> "Trains & Split"
    }

    fun tabPnr(lang: AppLanguage): String = when (lang) {
        AppLanguage.BENGALI -> "পিএনআর"
        AppLanguage.HINDI -> "पीएनआर"
        AppLanguage.ENGLISH -> "PNR Status"
    }

    fun tabLive(lang: AppLanguage): String = when (lang) {
        AppLanguage.BENGALI -> "লাইভ ট্র্যাক"
        AppLanguage.HINDI -> "लाइव ट्रैक"
        AppLanguage.ENGLISH -> "Live Tracking"
    }

    fun tabAi(lang: AppLanguage): String = when (lang) {
        AppLanguage.BENGALI -> "রেল এআই"
        AppLanguage.HINDI -> "रेल एआई"
        AppLanguage.ENGLISH -> "Rail AI"
    }

    // Search Screen
    fun searchHeader(lang: AppLanguage): String = when (lang) {
        AppLanguage.BENGALI -> "ট্রেন অনুসন্ধান ও নিশ্চিত সিট প্ল্যানার"
        AppLanguage.HINDI -> "ट्रेन खोज एवं कन्फर्म सीट प्लानर"
        AppLanguage.ENGLISH -> "Train Search & Confirm Seat Planner"
    }

    fun fromLabel(lang: AppLanguage): String = when (lang) {
        AppLanguage.BENGALI -> "কোথা থেকে (From)"
        AppLanguage.HINDI -> "कहाँ से (From)"
        AppLanguage.ENGLISH -> "From Station"
    }

    fun toLabel(lang: AppLanguage): String = when (lang) {
        AppLanguage.BENGALI -> "কোথায় যাবেন (To)"
        AppLanguage.HINDI -> "कहाँ तक (To)"
        AppLanguage.ENGLISH -> "To Station"
    }

    fun journeyDate(lang: AppLanguage): String = when (lang) {
        AppLanguage.BENGALI -> "যাত্রার তারিখ (IST):"
        AppLanguage.HINDI -> "यात्रा की तारीख (IST):"
        AppLanguage.ENGLISH -> "Journey Date (IST):"
    }

    fun pickFromCalendar(lang: AppLanguage): String = when (lang) {
        AppLanguage.BENGALI -> "ক্যালেন্ডার"
        AppLanguage.HINDI -> "कैलेंडर"
        AppLanguage.ENGLISH -> "Calendar"
    }

    fun selectDateDialogTitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.BENGALI -> "যাত্রার তারিখ নির্বাচন করুন"
        AppLanguage.HINDI -> "यात्रा की तारीख चुनें"
        AppLanguage.ENGLISH -> "Select Journey Date"
    }

    fun popularStationsLabel(lang: AppLanguage): String = when (lang) {
        AppLanguage.BENGALI -> "জনপ্রিয় স্টেশন:"
        AppLanguage.HINDI -> "लोकप्रिय स्टेशन:"
        AppLanguage.ENGLISH -> "Popular Stations:"
    }

    fun typeToSearchHint(lang: AppLanguage): String = when (lang) {
        AppLanguage.BENGALI -> "স্টেশনের নাম বা কোড লিখুন (যেমন Howrah, HWH, হাওড়া...)"
        AppLanguage.HINDI -> "स्टेशन का नाम या कोड लिखें (जैसे Howrah, HWH, हावड़ा...)"
        AppLanguage.ENGLISH -> "Type station name or code (e.g. Howrah, HWH...)"
    }

    fun searchButton(lang: AppLanguage): String = when (lang) {
        AppLanguage.BENGALI -> "ট্রেন ও স্প্লিট টিকিট অনুসন্ধান করুন"
        AppLanguage.HINDI -> "ट्रेन एवं स्प्लिट टिकट खोजें"
        AppLanguage.ENGLISH -> "Search Trains & Split-Tickets"
    }

    fun searching(lang: AppLanguage): String = when (lang) {
        AppLanguage.BENGALI -> "খোঁজা হচ্ছে..."
        AppLanguage.HINDI -> "खोजा जा रहा है..."
        AppLanguage.ENGLISH -> "Searching..."
    }

    fun splitBannerTitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.BENGALI -> "রেডরেল স্প্লিট-টিকিট সমাধান (Seat-Switching)"
        AppLanguage.HINDI -> "रेडरेल स्प्लिट-टिकट समाधान (Seat-Switching)"
        AppLanguage.ENGLISH -> "Redrail Split-Ticket Solutions (Seat-Switching)"
    }

    fun splitBannerSubtitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.BENGALI -> "সরাসরি টিকিট না থাকলেও মধ্যবর্তী স্টেশনে আসন পরিবর্তনে ১০০% নিশ্চিত সিট পান!"
        AppLanguage.HINDI -> "सीधा टिकट न होने पर भी मध्यवर्ती स्टेशन पर सीट बदलकर 100% कन्फर्म सीट पाएं!"
        AppLanguage.ENGLISH -> "Get 100% confirmed seats by switching seats at intermediate stations!"
    }

    fun directTrainsHeader(lang: AppLanguage, count: Int): String = when (lang) {
        AppLanguage.BENGALI -> "সরাসরি উপলব্ধ ট্রেনসমূহ (${count}টি ট্রেন পাওয়া গেছে):"
        AppLanguage.HINDI -> "सीधी उपलब्ध ट्रेनें (${count} ट्रेनें मिलीं):"
        AppLanguage.ENGLISH -> "Direct Available Trains ($count trains found):"
    }

    fun totalFare(lang: AppLanguage): String = when (lang) {
        AppLanguage.BENGALI -> "মোট ভাড়া:"
        AppLanguage.HINDI -> "कुल किराया:"
        AppLanguage.ENGLISH -> "Total Fare:"
    }

    fun viewDetails(lang: AppLanguage): String = when (lang) {
        AppLanguage.BENGALI -> "বিস্তারিত দেখুন >"
        AppLanguage.HINDI -> "विवरण देखें >"
        AppLanguage.ENGLISH -> "View Details >"
    }

    fun confirmedSeatBadge(lang: AppLanguage): String = when (lang) {
        AppLanguage.BENGALI -> "১০০% নিশ্চিত সিট (CNF)"
        AppLanguage.HINDI -> "100% कन्फर्म सीट (CNF)"
        AppLanguage.ENGLISH -> "100% Confirmed Seat (CNF)"
    }

    fun seatSwitchNotice(lang: AppLanguage, stn: String, mins: Int): String = when (lang) {
        AppLanguage.BENGALI -> "স্থানান্তর: $stn জংশনে আসন পরিবর্তন (বিরতি: $mins মি. IST)"
        AppLanguage.HINDI -> "बदलाव: $stn जंक्शन पर सीट परिवर्तन (स्टॉप: $mins मिनट IST)"
        AppLanguage.ENGLISH -> "Transfer: Seat switch at $stn Jn (halt: $mins min IST)"
    }

    // Split Dialog
    fun splitDialogTitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.BENGALI -> "স্প্লিট-টিকিট সমাধান"
        AppLanguage.HINDI -> "स्प्लिट-टिकट समाधान"
        AppLanguage.ENGLISH -> "Split-Ticket Solution"
    }

    fun sameTrainType(lang: AppLanguage): String = when (lang) {
        AppLanguage.BENGALI -> "একই ট্রেনে আসন পরিবর্তন (Same Train Split)"
        AppLanguage.HINDI -> "एक ही ट्रेन में सीट परिवर्तन (Same Train Split)"
        AppLanguage.ENGLISH -> "Seat Switch in Same Train (Same Train Split)"
    }

    fun connectingType(lang: AppLanguage): String = when (lang) {
        AppLanguage.BENGALI -> "সংযোগকারী ট্রেন স্প্লিট (Connecting Split)"
        AppLanguage.HINDI -> "कनेक्टिंग ट्रेन स्प्लिट (Connecting Split)"
        AppLanguage.ENGLISH -> "Connecting Train Split"
    }

    fun understoodButton(lang: AppLanguage): String = when (lang) {
        AppLanguage.BENGALI -> "বুঝতে পেরেছি (টিকিট বুকিং নির্দেশনা)"
        AppLanguage.HINDI -> "समझ गए (टिकट बुकिंग निर्देश)"
        AppLanguage.ENGLISH -> "Understood (Booking Guide)"
    }

    // PNR
    fun pnrHeader(lang: AppLanguage): String = when (lang) {
        AppLanguage.BENGALI -> "পিএনআর স্টেটাস ও সম্ভাবনা প্রেডিকশন"
        AppLanguage.HINDI -> "पीएनआर स्थिति एवं संभावना भविष्यवाणी"
        AppLanguage.ENGLISH -> "PNR Status & Confirmation Prediction"
    }

    fun pnrSubtitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.BENGALI -> "আপনার ১০ সংখ্যার পিএনআর নম্বর দিয়ে বর্তমান অবস্থা ও কনফার্ম হওয়ার শতকরা সম্ভাবনা জানুন।"
        AppLanguage.HINDI -> "अपना 10 अंकों का पीएनआर नंबर डालकर वर्तमान स्थिति और कन्फर्म होने का प्रतिशत जानें।"
        AppLanguage.ENGLISH -> "Enter your 10-digit PNR to check current status and confirmation probability."
    }

    fun pnrPlaceholder(lang: AppLanguage): String = when (lang) {
        AppLanguage.BENGALI -> "১০ সংখ্যার PNR লিখুন"
        AppLanguage.HINDI -> "10 अंकों का PNR दर्ज करें"
        AppLanguage.ENGLISH -> "Enter 10-digit PNR"
    }

    fun checkPnrButton(lang: AppLanguage): String = when (lang) {
        AppLanguage.BENGALI -> "পিএনআর স্টেটাস ও প্রেডিকশন দেখুন"
        AppLanguage.HINDI -> "पीएनआर स्थिति और भविष्यवाणी देखें"
        AppLanguage.ENGLISH -> "Check PNR Status & Prediction"
    }

    fun demoPnrLabel(lang: AppLanguage): String = when (lang) {
        AppLanguage.BENGALI -> "দ্রুত পরীক্ষার জন্য নমুনা PNR ট্যাপ করুন:"
        AppLanguage.HINDI -> "त्वरित जांच के लिए नमूना PNR चुनें:"
        AppLanguage.ENGLISH -> "Tap sample PNR for quick demo:"
    }

    fun chartStatusLabel(lang: AppLanguage): String = when (lang) {
        AppLanguage.BENGALI -> "চার্ট তৈরির অবস্থা (Chart Status):"
        AppLanguage.HINDI -> "चार्ट बनने की स्थिति (Chart Status):"
        AppLanguage.ENGLISH -> "Chart Status:"
    }

    fun passengersLabel(lang: AppLanguage): String = when (lang) {
        AppLanguage.BENGALI -> "যাত্রীদের বিস্তারিত আসন স্টেটাস:"
        AppLanguage.HINDI -> "यात्रियों की विस्तृत सीट स्थिति:"
        AppLanguage.ENGLISH -> "Passenger Seat Status Breakdown:"
    }

    fun aiAdviceLabel(lang: AppLanguage): String = when (lang) {
        AppLanguage.BENGALI -> "এআই ভবিষ্যদ্বাণী ও ভ্রমণ পরামর্শ:"
        AppLanguage.HINDI -> "एआई भविष्यवाणी एवं यात्रा सलाह:"
        AppLanguage.ENGLISH -> "AI Prediction & Travel Advice:"
    }

    fun predictionHeader(lang: AppLanguage): String = when (lang) {
        AppLanguage.BENGALI -> "কনফার্মেশনের সম্ভাবনা প্রেডিকশন:"
        AppLanguage.HINDI -> "कन्फर्म होने की संभावना भविष्यवाणी:"
        AppLanguage.ENGLISH -> "Confirmation Probability Prediction:"
    }

    // Live Tracking
    fun liveHeader(lang: AppLanguage): String = when (lang) {
        AppLanguage.BENGALI -> "লাইভ ট্রেন রানিং স্টেটাস (IST ট্র্যাকিং)"
        AppLanguage.HINDI -> "लाइव ट्रेन रनिंग स्थिति (IST ट्रैकिंग)"
        AppLanguage.ENGLISH -> "Live Train Running Status (IST Tracking)"
    }

    fun popularTrains(lang: AppLanguage): String = when (lang) {
        AppLanguage.BENGALI -> "জনপ্রিয় ট্রেনসমূহ নির্বাচন করুন:"
        AppLanguage.HINDI -> "लोकप्रिय ट्रेनें चुनें:"
        AppLanguage.ENGLISH -> "Select Popular Trains:"
    }

    fun trainInputPlaceholder(lang: AppLanguage): String = when (lang) {
        AppLanguage.BENGALI -> "ট্রেন নম্বর (যেমন ১২৩১৩)"
        AppLanguage.HINDI -> "ट्रेन नंबर (जैसे 12313)"
        AppLanguage.ENGLISH -> "Train number (e.g. 12301)"
    }

    fun trackButton(lang: AppLanguage): String = when (lang) {
        AppLanguage.BENGALI -> "ট্র্যাক"
        AppLanguage.HINDI -> "ट्रैक"
        AppLanguage.ENGLISH -> "Track"
    }

    fun lastUpdated(lang: AppLanguage): String = when (lang) {
        AppLanguage.BENGALI -> "সর্বশেষ আপডেট:"
        AppLanguage.HINDI -> "अंतिम अपडेट:"
        AppLanguage.ENGLISH -> "Last Updated:"
    }

    fun currentLocation(lang: AppLanguage): String = when (lang) {
        AppLanguage.BENGALI -> "বর্তমান অবস্থান:"
        AppLanguage.HINDI -> "वर्तमान स्थान:"
        AppLanguage.ENGLISH -> "Current Location:"
    }

    fun speed(lang: AppLanguage): String = when (lang) {
        AppLanguage.BENGALI -> "গতিবেগ"
        AppLanguage.HINDI -> "गति"
        AppLanguage.ENGLISH -> "Speed"
    }

    fun kmph(lang: AppLanguage): String = when (lang) {
        AppLanguage.BENGALI -> "কিমি/ঘণ্টা"
        AppLanguage.HINDI -> "किमी/घंटा"
        AppLanguage.ENGLISH -> "km/h"
    }

    fun nextStation(lang: AppLanguage): String = when (lang) {
        AppLanguage.BENGALI -> "পরবর্তী স্টেশন:"
        AppLanguage.HINDI -> "अगला स्टेशन:"
        AppLanguage.ENGLISH -> "Next Station:"
    }

    fun distance(lang: AppLanguage): String = when (lang) {
        AppLanguage.BENGALI -> "দূরত্ব:"
        AppLanguage.HINDI -> "दूरी:"
        AppLanguage.ENGLISH -> "Distance:"
    }

    fun km(lang: AppLanguage): String = when (lang) {
        AppLanguage.BENGALI -> "কিমি"
        AppLanguage.HINDI -> "किमी"
        AppLanguage.ENGLISH -> "km"
    }

    fun routeHeader(lang: AppLanguage): String = when (lang) {
        AppLanguage.BENGALI -> "স্টেশন অনুযায়ী ট্রেনের রুট ও পৌঁছানোর সময় (IST):"
        AppLanguage.HINDI -> "स्टेशन अनुसार ट्रेन रूट एवं आगमन समय (IST):"
        AppLanguage.ENGLISH -> "Station-wise Route & Arrival Time (IST):"
    }

    fun arrival(lang: AppLanguage): String = when (lang) {
        AppLanguage.BENGALI -> "পৌঁছানো:"
        AppLanguage.HINDI -> "आगमन:"
        AppLanguage.ENGLISH -> "Arrival:"
    }

    fun scheduled(lang: AppLanguage): String = when (lang) {
        AppLanguage.BENGALI -> "নির্ধারিত:"
        AppLanguage.HINDI -> "निर्धारित:"
        AppLanguage.ENGLISH -> "Scheduled:"
    }

    fun lateMins(lang: AppLanguage, mins: Int): String = when (lang) {
        AppLanguage.BENGALI -> "+$mins মি. দেরি"
        AppLanguage.HINDI -> "+$mins मिनट देरी"
        AppLanguage.ENGLISH -> "+$mins min late"
    }

    fun onTimeBadge(lang: AppLanguage): String = when (lang) {
        AppLanguage.BENGALI -> "সময়মতো (RT)"
        AppLanguage.HINDI -> "समय पर (RT)"
        AppLanguage.ENGLISH -> "Right Time (RT)"
    }

    // AI Assistant
    fun aiSubBar(lang: AppLanguage): String = when (lang) {
        AppLanguage.BENGALI -> "ভারতীয় রেলওয়ে এআই সহায়ক ও ট্রাভেল প্ল্যানার"
        AppLanguage.HINDI -> "भारतीय रेलवे एआई सहायक एवं यात्रा प्लानर"
        AppLanguage.ENGLISH -> "Indian Railways AI Assistant & Travel Planner"
    }

    fun aiInputPlaceholder(lang: AppLanguage): String = when (lang) {
        AppLanguage.BENGALI -> "বাংলায় যেকোনো প্রশ্ন লিখুন..."
        AppLanguage.HINDI -> "हिन्दी में कोई भी प्रश्न लिखें..."
        AppLanguage.ENGLISH -> "Ask any railway question..."
    }

    fun aiThinking(lang: AppLanguage): String = when (lang) {
        AppLanguage.BENGALI -> "রেলসখা ভাবছে..."
        AppLanguage.HINDI -> "रेलसखा सोच रहा है..."
        AppLanguage.ENGLISH -> "RailSathi is thinking..."
    }

    fun aiWelcomeMessage(lang: AppLanguage): String = when (lang) {
        AppLanguage.BENGALI -> "নমস্কার! আমি আপনার ভারতীয় রেল এআই ট্রাভেল প্ল্যানার।\n\nআপনি আমাকে স্প্লিট টিকিট, ট্রেনের সময়সূচী (IST), পিএনআর কনফার্মেশন প্রেডিকশন বা লাইভ লোকেশন সম্পর্কে যেকোনো প্রশ্ন করতে পারেন।"
        AppLanguage.HINDI -> "नमस्ते! मैं आपका भारतीय रेल एआई यात्रा योजनाकार हूँ।\n\nआप मुझसे स्प्लिट टिकट, ट्रेन समय सारिणी (IST), पीएनआर कन्फर्मेशन भविष्यवाणी या लाइव रनिंग स्थिति के बारे में पूछ सकते हैं।"
        AppLanguage.ENGLISH -> "Hello! I am your Indian Railways AI Travel Planner.\n\nYou can ask me about split-tickets, train schedules (IST), PNR confirmation predictions, or live running status."
    }

    fun aiSuggestions(lang: AppLanguage): List<String> = when (lang) {
        AppLanguage.BENGALI -> listOf(
            "স্প্লিট টিকিট কীভাবে কাজ করে?",
            "হাওড়া থেকে দিল্লি ট্রাভেল প্ল্যান",
            "পিএনআর কনফার্মেশন প্রেডিকশন টিপস",
            "তৎকাল টিকিট বুকিং নিয়ম ও সময় (IST)",
            "বন্দে ভারত এক্সপ্রেসের সুবিধা",
            "টিকিট বাতিল ও রিফান্ডের নিয়ম"
        )
        AppLanguage.HINDI -> listOf(
            "स्प्लिट टिकट कैसे काम करता है?",
            "हावड़ा से दिल्ली यात्रा योजना",
            "पीएनआर कन्फर्मेशन भविष्यवाणी टिप्स",
            "तत्काल टिकट बुकिंग नियम व समय (IST)",
            "वंदे भारत एक्सप्रेस की सुविधाएं",
            "टिकट रद्दीकरण एवं रिफंड नियम"
        )
        AppLanguage.ENGLISH -> listOf(
            "How does split-ticketing work?",
            "Howrah to Delhi travel plan",
            "PNR confirmation prediction tips",
            "Tatkal ticket booking rules & time (IST)",
            "Vande Bharat Express features",
            "Cancellation & refund rules"
        )
    }
}
