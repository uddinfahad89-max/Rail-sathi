export type Language = 'bn' | 'hi' | 'en';

export interface Station {
  code: string;
  nameBn: string;
  nameHi: string;
  nameEn: string;
  cityBn: string;
  cityHi: string;
  cityEn: string;
}

export const STATIONS: Station[] = [
  { code: 'HWH', nameBn: 'হাওড়া জংশন', nameHi: 'हावड़ा जंक्शन', nameEn: 'Howrah Junction', cityBn: 'কলকাতা', cityHi: 'कोलकाता', cityEn: 'Kolkata' },
  { code: 'SDAH', nameBn: 'শিয়ালদহ', nameHi: 'सियालदह', nameEn: 'Sealdah', cityBn: 'কলকাতা', cityHi: 'कोलकाता', cityEn: 'Kolkata' },
  { code: 'NDLS', nameBn: 'নতুন দিল্লি', nameHi: 'नई दिल्ली', nameEn: 'New Delhi', cityBn: 'দিল্লি', cityHi: 'दिल्ली', cityEn: 'New Delhi' },
  { code: 'DDU', nameBn: 'পণ্ডিত দীনদয়াল উপাধ্যায়', nameHi: 'पं. दीनदयाल उपाध्याय', nameEn: 'Pt. Deen Dayal Upadhyaya Jn', cityBn: 'মুঘলসরাই', cityHi: 'मुगलसराय', cityEn: 'Mughalsarai' },
  { code: 'PNBE', nameBn: 'পাটনা জংশন', nameHi: 'पटना जंक्शन', nameEn: 'Patna Junction', cityBn: 'পাটনা', cityHi: 'पटना', cityEn: 'Patna' },
  { code: 'CNB', nameBn: 'কানপুর সেন্ট্রাল', nameHi: 'कानपुर सेंट्रल', nameEn: 'Kanpur Central', cityBn: 'কানপুর', cityHi: 'कानपुर', cityEn: 'Kanpur' },
  { code: 'PURI', nameBn: 'পুরী', nameHi: 'पुरी', nameEn: 'Puri', cityBn: 'পুরী', cityHi: 'पुरी', cityEn: 'Puri' },
  { code: 'BBS', nameBn: 'ভুবনেশ্বর', nameHi: 'भुवनेश्वर', nameEn: 'Bhubaneswar', cityBn: 'ভুবনেশ্বর', cityHi: 'भुवनेश्वर', cityEn: 'Bhubaneswar' },
  { code: 'KGP', nameBn: 'খড়গপুর জংশন', nameHi: 'खड़गपुर जंक्शन', nameEn: 'Kharagpur Junction', cityBn: 'খড়গপুর', cityHi: 'खड़गपुर', cityEn: 'Kharagpur' },
  { code: 'NJP', nameBn: 'নিউ জলপাইগুড়ি', nameHi: 'न्यू जलपाईगुड़ी', nameEn: 'New Jalpaiguri', cityBn: 'শিলিগুড়ি', cityHi: 'सिलीगुड़ी', cityEn: 'Siliguri' },
  { code: 'CSMT', nameBn: 'মুম্বাই সিএসএমটি', nameHi: 'मुंबई सीएसएमटी', nameEn: 'Mumbai CSMT', cityBn: 'মুম্বাই', cityHi: 'मुंबई', cityEn: 'Mumbai' }
];

export const TRAINS_SAMPLE = [
  {
    number: '12301',
    nameBn: 'হাওড়া - নতুন দিল্লি রাজধানী এক্সপ্রেস',
    nameHi: 'हावड़ा - नई दिल्ली राजधानी एक्सप्रेस',
    nameEn: 'Howrah - New Delhi Rajdhani Express',
    type: 'Rajdhani Express',
    dep: '04:50 PM IST',
    arr: '10:05 AM IST',
    duration: '17h 15m',
    classes: [
      { code: '1A', status: 'WL 8', available: false, fare: 4850 },
      { code: '2A', status: 'WL 28', available: false, fare: 2890 },
      { code: '3A', status: 'WL 74', available: false, fare: 2040 }
    ]
  },
  {
    number: '12313',
    nameBn: 'শিয়ালদহ - নতুন দিল্লি রাজধানী এক্সপ্রেস',
    nameHi: 'सियालदह - नई दिल्ली राजधानी एक्सप्रेस',
    nameEn: 'Sealdah - New Delhi Rajdhani Express',
    type: 'Rajdhani Express',
    dep: '04:50 PM IST',
    arr: '10:50 AM IST',
    duration: '18h 00m',
    classes: [
      { code: '1A', status: 'AVAILABLE 2', available: true, fare: 4850 },
      { code: '2A', status: 'WL 14', available: false, fare: 2890 },
      { code: '3A', status: 'WL 52', available: false, fare: 2040 }
    ]
  },
  {
    number: '12259',
    nameBn: 'শিয়ালদহ - বিকানের দুরন্ত এক্সপ্রেস',
    nameHi: 'सियालदह - बीकानेर दुरंतो एक्सप्रेस',
    nameEn: 'Sealdah - Bikaner Duronto Express',
    type: 'Duronto Express',
    dep: '05:00 PM IST',
    arr: '11:00 AM IST',
    duration: '18h 00m',
    classes: [
      { code: '2A', status: 'WL 19', available: false, fare: 2750 },
      { code: '3A', status: 'WL 64', available: false, fare: 1980 },
      { code: 'SL', status: 'REGRET', available: false, fare: 720 }
    ]
  }
];

export const SPLIT_OPTIONS = [
  {
    id: 'split-1',
    trainNumber: '12301',
    trainNameBn: '১২৩০১ রাজধানী এক্সপ্রেস (একই ট্রেনে আসন পরিবর্তন)',
    trainNameHi: '12301 राजधानी एक्सप्रेस (सीट परिवर्तन)',
    trainNameEn: '12301 Rajdhani Express (Seat Switch)',
    intermediate: 'DDU (Pt. Deen Dayal Upadhyaya Jn)',
    seg1: { from: 'HWH', to: 'DDU', class: '2A', seat: 'Coach A2, Berth 18 (CNF)', fare: 1940, dep: '04:50 PM IST', arr: '12:50 AM IST' },
    seg2: { from: 'DDU', to: 'NDLS', class: '1A', seat: 'Coach H1, Cabin B, Berth 03 (CNF)', fare: 2520, dep: '01:00 AM IST', arr: '10:05 AM IST' },
    totalFare: 4460,
    haltMinutes: 10,
    noteBn: 'সরাসরি টিকিট WL ২৮। মধ্যবর্তী DDU স্টেশনে বার্থ বদল করে ১০০% নিশ্চিত সিট! ট্রেন পরিবর্তনের ঝামেলা নেই।',
    noteHi: 'सीधा टिकट WL 28। DDU जंक्शन पर बर्थ बदलकर 100% कन्फर्म सीट पाएं! ट्रेन बदलने की कोई जरूरत नहीं।',
    noteEn: 'Direct ticket is WL 28. Switch berth at DDU Jn for 100% Confirmed seat! No train change needed.'
  }
];

export const PNR_DEMOS: Record<string, any> = {
  '6428190342': {
    pnr: '6428190342',
    train: '12301 Howrah Rajdhani Express',
    date: '28 Sep 2026',
    from: 'Howrah (HWH)',
    to: 'New Delhi (NDLS)',
    dep: '04:50 PM IST',
    arr: '10:05 AM IST',
    chart: 'Chart Not Prepared (Prepares 4h before departure)',
    chartBn: 'চার্ট তৈরি হয়নি (প্রস্তুতি বাকি)',
    prob: 94,
    levelBn: 'খুব বেশি সম্ভাবনা (High)',
    levelHi: 'उच्च संभावना (High)',
    levelEn: 'High Probability (94%)',
    passengers: [
      { name: 'Passenger 1 (M/35)', booking: 'GNWL 14', current: 'WL 2', coach: 'WL', berth: '2' },
      { name: 'Passenger 2 (F/32)', booking: 'GNWL 15', current: 'WL 3', coach: 'WL', berth: '3' }
    ],
    adviceBn: 'নিশ্চিত হওয়ার সম্ভাবনা ৯৪%। ঐতিহাসিক তথ্য অনুযায়ী চার্ট তৈরির সময় (দুপুর ১২:৫০ PM IST) কনফার্ম হবে।',
    adviceHi: 'कन्फर्म होने की 94% संभावना है। चार्ट बनने पर दोपहर 12:50 PM IST तक सीट कन्फर्म हो जाएगी।',
    adviceEn: '94% chance of confirmation. Expected to confirm when charting completes around 12:50 PM IST.'
  },
  '2841957201': {
    pnr: '2841957201',
    train: '12837 Howrah - Puri Superfast Express',
    date: '29 Sep 2026',
    from: 'Howrah (HWH)',
    to: 'Puri (PURI)',
    dep: '10:35 PM IST',
    arr: '07:10 AM IST',
    chart: 'Chart Not Prepared',
    chartBn: 'চার্ট তৈরি হয়নি',
    prob: 98,
    levelBn: 'সুনিশ্চিত সম্ভাবনা (Very High)',
    levelHi: 'अति उच्च संभावना (Very High)',
    levelEn: 'Very High Probability (98%)',
    passengers: [
      { name: 'Passenger 1 (M/42)', booking: 'WL 8', current: 'RAC 4', coach: 'RAC', berth: '4 (Side Lower)' }
    ],
    adviceBn: 'বর্তমানে RAC ৪। চার্ট প্রস্তুত হলে সম্পূর্ণ বার্থ বরাদ্দ হওয়ার ৯৮% সম্ভাবনা।',
    adviceHi: 'वर्तमान में RAC 4 पर है। चार्ट बनने पर पूर्ण कन्फर्म बर्थ मिलने की 98% संभावना है।',
    adviceEn: 'Currently at RAC 4. High likelihood of full berth allocation after final chart preparation.'
  }
};
