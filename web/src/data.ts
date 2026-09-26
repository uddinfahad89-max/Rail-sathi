export type Language = 'bn' | 'hi' | 'en';

export interface Station {
  code: string;
  nameBn: string;
  nameHi: string;
  nameEn: string;
  cityBn: string;
  cityHi: string;
  cityEn: string;
  state: string;
}

export const STATIONS: Station[] = [
  // West Bengal
  { code: 'HWH', nameBn: 'হাওড়া জংশন', nameHi: 'हावड़ा जंक्शन', nameEn: 'Howrah Junction', cityBn: 'কলকাতা', cityHi: 'कोलकाता', cityEn: 'Kolkata', state: 'West Bengal' },
  { code: 'SDAH', nameBn: 'শিয়ালদহ', nameHi: 'सियालदह', nameEn: 'Sealdah', cityBn: 'কলকাতা', cityHi: 'कोलकाता', cityEn: 'Kolkata', state: 'West Bengal' },
  { code: 'KOAA', nameBn: 'কলকাতা টার্মিনাল', nameHi: 'कोलकाता टर्मिनल', nameEn: 'Kolkata Terminal', cityBn: 'কলকাতা', cityHi: 'कोलकाता', cityEn: 'Kolkata', state: 'West Bengal' },
  { code: 'SHM', nameBn: 'শালিমার', nameHi: 'शालीमार', nameEn: 'Shalimar', cityBn: 'হাওড়া', cityHi: 'हावड़ा', cityEn: 'Howrah', state: 'West Bengal' },
  { code: 'SRC', nameBn: 'সাঁতরাগাছি জংশন', nameHi: 'सांतरागाछी जंक्शन', nameEn: 'Santragachi Jn', cityBn: 'হাওড়া', cityHi: 'हावड़ा', cityEn: 'Howrah', state: 'West Bengal' },
  { code: 'KGP', nameBn: 'খড়গপুর জংশন', nameHi: 'खड़गपुर जंक्शन', nameEn: 'Kharagpur Junction', cityBn: 'খড়গপুর', cityHi: 'खड़गपुर', cityEn: 'Kharagpur', state: 'West Bengal' },
  { code: 'ASN', nameBn: 'আসানসোল জংশন', nameHi: 'आसनसोल जंक्शन', nameEn: 'Asansol Junction', cityBn: 'আসানসোল', cityHi: 'आसनसोल', cityEn: 'Asansol', state: 'West Bengal' },
  { code: 'DGR', nameBn: 'দুর্গাপুর', nameHi: 'दुर्गापुर', nameEn: 'Durgapur', cityBn: 'দুর্গাপুর', cityHi: 'दुर्गापुर', cityEn: 'Durgapur', state: 'West Bengal' },
  { code: 'BWN', nameBn: 'বর্ধমান জংশন', nameHi: 'बर्द्धमान जंक्शन', nameEn: 'Barddhaman Jn', cityBn: 'বর্ধমান', cityHi: 'बर्द्धमान', cityEn: 'Bardhaman', state: 'West Bengal' },
  { code: 'MLDT', nameBn: 'মালদা টাউন', nameHi: 'मालदा टाउन', nameEn: 'Malda Town', cityBn: 'মালদা', cityHi: 'मालदा', cityEn: 'Malda', state: 'West Bengal' },
  { code: 'NJP', nameBn: 'নিউ জলপাইগুড়ি', nameHi: 'न्यू जलपाईगुड़ी', nameEn: 'New Jalpaiguri', cityBn: 'শিলিগুড়ি', cityHi: 'सिलीगुड़ी', cityEn: 'Siliguri', state: 'West Bengal' },
  { code: 'BOE', nameBn: 'বারসোই জংশন', nameHi: 'बारसोई जंक्शन', nameEn: 'Barsoi Junction', cityBn: 'বারসোই', cityHi: 'बारसोई', cityEn: 'Barsoi', state: 'Bihar' },
  { code: 'NFK', nameBn: 'নিউ ফারাক্কা জংশন', nameHi: 'न्यू फरक्का जंक्शन', nameEn: 'New Farakka Jn', cityBn: 'ফারাক্কা', cityHi: 'फरक्का', cityEn: 'Farakka', state: 'West Bengal' },

  // Delhi NCR
  { code: 'NDLS', nameBn: 'নতুন দিল্লি', nameHi: 'नई दिल्ली', nameEn: 'New Delhi', cityBn: 'দিল্লি', cityHi: 'दिल्ली', cityEn: 'New Delhi', state: 'Delhi' },
  { code: 'DLI', nameBn: 'পুরনো দিল্লি', nameHi: 'पुरानी दिल्ली', nameEn: 'Old Delhi Jn', cityBn: 'দিল্লি', cityHi: 'दिल्ली', cityEn: 'Delhi', state: 'Delhi' },
  { code: 'NZM', nameBn: 'হযরত নিজামুদ্দীন', nameHi: 'हज़रत निज़ामुद्दीन', nameEn: 'Hazrat Nizamuddin', cityBn: 'দিল্লি', cityHi: 'दिल्ली', cityEn: 'Delhi', state: 'Delhi' },
  { code: 'ANVT', nameBn: 'আনন্দ বিহার টার্মিনাল', nameHi: 'आनंद विहार टर्मिनल', nameEn: 'Anand Vihar Terminal', cityBn: 'দিল্লি', cityHi: 'दिल्ली', cityEn: 'Delhi', state: 'Delhi' },
  { code: 'DEE', nameBn: 'দিল্লি সরাই রোহিল্লা', nameHi: 'दिल्ली सराय रोहिल्ला', nameEn: 'Delhi Sarai Rohilla', cityBn: 'দিল্লি', cityHi: 'दिल्ली', cityEn: 'Delhi', state: 'Delhi' },
  { code: 'GZB', nameBn: 'গাজিয়াবাদ জংশন', nameHi: 'गाज़ियाबाद जंक्शन', nameEn: 'Ghaziabad Jn', cityBn: 'গাজিয়াবাদ', cityHi: 'गाज़ियाबाद', cityEn: 'Ghaziabad', state: 'Uttar Pradesh' },

  // Uttar Pradesh
  { code: 'DDU', nameBn: 'পণ্ডিত দীনদয়াল উপাধ্যায়', nameHi: 'पं. दीनदयाल उपाध्याय', nameEn: 'Pt. Deen Dayal Upadhyaya Jn', cityBn: 'মুঘলসরাই', cityHi: 'मुगलसराय', cityEn: 'Mughalsarai', state: 'Uttar Pradesh' },
  { code: 'CNB', nameBn: 'কানপুর সেন্ট্রাল', nameHi: 'कानपुर सेंट्रल', nameEn: 'Kanpur Central', cityBn: 'কানপুর', cityHi: 'कानपुर', cityEn: 'Kanpur', state: 'Uttar Pradesh' },
  { code: 'PRYJ', nameBn: 'প্রয়াগরাজ জংশন', nameHi: 'प्रयागराज जंक्शन', nameEn: 'Prayagraj Junction', cityBn: 'প্রয়াগরাজ', cityHi: 'प्रयागराज', cityEn: 'Prayagraj', state: 'Uttar Pradesh' },
  { code: 'LKO', nameBn: 'লখনউ চারবাগ', nameHi: 'लखनऊ चारबाग', nameEn: 'Lucknow Charbagh', cityBn: 'লখনউ', cityHi: 'लखनऊ', cityEn: 'Lucknow', state: 'Uttar Pradesh' },
  { code: 'BSB', nameBn: 'বারাণসী জংশন', nameHi: 'वाराणसी जंक्शन', nameEn: 'Varanasi Junction', cityBn: 'বারাণসী', cityHi: 'वाराणसी', cityEn: 'Varanasi', state: 'Uttar Pradesh' },
  { code: 'GKP', nameBn: 'গোরখপুর জংশন', nameHi: 'गोरखपुर जंक्शन', nameEn: 'Gorakhpur Junction', cityBn: 'গোরখপুর', cityHi: 'गोरखपुर', cityEn: 'Gorakhpur', state: 'Uttar Pradesh' },
  { code: 'AGC', nameBn: 'আগ্রা ক্যান্ট', nameHi: 'आगरा कैंट', nameEn: 'Agra Cantt', cityBn: 'আগ্রা', cityHi: 'आगरा', cityEn: 'Agra', state: 'Uttar Pradesh' },
  { code: 'MTJ', nameBn: 'মথুরা জংশন', nameHi: 'मथुरा जंक्शन', nameEn: 'Mathura Junction', cityBn: 'মথুরা', cityHi: 'मथुरा', cityEn: 'Mathura', state: 'Uttar Pradesh' },
  { code: 'MB', nameBn: 'মোরাদাবাদ', nameHi: 'मुरादाबाद', nameEn: 'Moradabad Jn', cityBn: 'মোরাদাবাদ', cityHi: 'मुरादाबाद', cityEn: 'Moradabad', state: 'Uttar Pradesh' },
  { code: 'BE', nameBn: 'বরেলি জংশন', nameHi: 'बरेली जंक्शन', nameEn: 'Bareilly Jn', cityBn: 'বরেলি', cityHi: 'बरेली', cityEn: 'Bareilly', state: 'Uttar Pradesh' },
  { code: 'ALJN', nameBn: 'আলীগড় জংশন', nameHi: 'अलीगढ़ जंक्शन', nameEn: 'Aligarh Jn', cityBn: 'আলীগড়', cityHi: 'अलीगढ़', cityEn: 'Aligarh', state: 'Uttar Pradesh' },
  { code: 'VGLJ', nameBn: 'ভিজিএল ঝাঁসি', nameHi: 'वीरांगना लक्ष्मीबाई झाँसी', nameEn: 'VGL Jhansi Jn', cityBn: 'ঝাঁসি', cityHi: 'झाँसी', cityEn: 'Jhansi', state: 'Uttar Pradesh' },
  { code: 'AY', nameBn: 'অযোধ্যা ধাম', nameHi: 'अयोध्या धाम', nameEn: 'Ayodhya Dham Jn', cityBn: 'অযোধ্যা', cityHi: 'अयोध्या', cityEn: 'Ayodhya', state: 'Uttar Pradesh' },

  // Bihar
  { code: 'PNBE', nameBn: 'পাটনা জংশন', nameHi: 'पटना जंक्शन', nameEn: 'Patna Junction', cityBn: 'পাটনা', cityHi: 'पटना', cityEn: 'Patna', state: 'Bihar' },
  { code: 'PPTA', nameBn: 'পাটলিপুত্র জংশন', nameHi: 'पाटलिपुत्र जंक्शन', nameEn: 'Patliputra Jn', cityBn: 'পাটনা', cityHi: 'पटना', cityEn: 'Patna', state: 'Bihar' },
  { code: 'DNR', nameBn: 'দানাপুর', nameHi: 'दानापुर', nameEn: 'Danapur', cityBn: 'দানাপুর', cityHi: 'दानापुर', cityEn: 'Danapur', state: 'Bihar' },
  { code: 'GAYA', nameBn: 'গয়া জংশন', nameHi: 'गया जंक्शन', nameEn: 'Gaya Junction', cityBn: 'গয়া', cityHi: 'गया', cityEn: 'Gaya', state: 'Bihar' },
  { code: 'MFP', nameBn: 'মুজাফফরপুর জংশন', nameHi: 'मुजफ्फरपुर जंक्शन', nameEn: 'Muzaffarpur Jn', cityBn: 'মুজাফফরপুর', cityHi: 'मुजफ्फरपुर', cityEn: 'Muzaffarpur', state: 'Bihar' },
  { code: 'DBG', nameBn: 'দ্বারভাঙা জংশন', nameHi: 'दरभंगा जंक्शन', nameEn: 'Darbhanga Jn', cityBn: 'দ্বারভাঙা', cityHi: 'दरभंगा', cityEn: 'Darbhanga', state: 'Bihar' },
  { code: 'BJU', nameBn: 'বরৌনী জংশন', nameHi: 'बरौनी जंक्शन', nameEn: 'Barauni Jn', cityBn: 'বরৌনী', cityHi: 'बरौनी', cityEn: 'Barauni', state: 'Bihar' },
  { code: 'BGP', nameBn: 'ভাগলপুর জংশন', nameHi: 'भागलपुर जंक्शन', nameEn: 'Bhagalpur Jn', cityBn: 'ভাগলপুর', cityHi: 'भागलपुर', cityEn: 'Bhagalpur', state: 'Bihar' },
  { code: 'KIR', nameBn: 'কাটিহার জংশন', nameHi: 'कटिहार जंक्शन', nameEn: 'Katihar Jn', cityBn: 'কাটিহার', cityHi: 'कटिहार', cityEn: 'Katihar', state: 'Bihar' },
  { code: 'BXR', nameBn: 'বক্সার', nameHi: 'बक्सर', nameEn: 'Buxar', cityBn: 'বক্সার', cityHi: 'बक्सर', cityEn: 'Buxar', state: 'Bihar' },

  // Maharashtra
  { code: 'CSMT', nameBn: 'মুম্বাই সিএসএমটি', nameHi: 'मुंबई सीएसएमटी', nameEn: 'Mumbai CSMT', cityBn: 'মুম্বাই', cityHi: 'मुंबई', cityEn: 'Mumbai', state: 'Maharashtra' },
  { code: 'MMCT', nameBn: 'মুম্বাই সেন্ট্রাল', nameHi: 'मुंबई सेंट्रल', nameEn: 'Mumbai Central', cityBn: 'মুম্বাই', cityHi: 'मुंबई', cityEn: 'Mumbai', state: 'Maharashtra' },
  { code: 'BDTS', nameBn: 'বান্দ্রা টার্মিনাস', nameHi: 'बांद्रा टर्मिनस', nameEn: 'Bandra Terminus', cityBn: 'মুম্বাই', cityHi: 'मुंबई', cityEn: 'Mumbai', state: 'Maharashtra' },
  { code: 'LTT', nameBn: 'লোকমান্য তিলক টার্মিনাস', nameHi: 'लोकमान्य तिलक टर्मिनस', nameEn: 'Lokmanya Tilak (LTT)', cityBn: 'মুম্বাই', cityHi: 'मुंबई', cityEn: 'Mumbai', state: 'Maharashtra' },
  { code: 'PUNE', nameBn: 'পুনে জংশন', nameHi: 'पुणे जंक्शन', nameEn: 'Pune Junction', cityBn: 'পুনে', cityHi: 'पुणे', cityEn: 'Pune', state: 'Maharashtra' },
  { code: 'NGP', nameBn: 'নাগপুর জংশন', nameHi: 'नागपुर जंक्शन', nameEn: 'Nagpur Junction', cityBn: 'নাগপুর', cityHi: 'नागपुर', cityEn: 'Nagpur', state: 'Maharashtra' },
  { code: 'BSL', nameBn: 'ভুসাবল জংশন', nameHi: 'भुसावल जंक्शन', nameEn: 'Bhusaval Jn', cityBn: 'ভুসাবল', cityHi: 'भुसावल', cityEn: 'Bhusawal', state: 'Maharashtra' },
  { code: 'SUR', nameBn: 'সোলাপুর', nameHi: 'सोलापुर', nameEn: 'Solapur', cityBn: 'সোলাপুর', cityHi: 'सोलापुर', cityEn: 'Solapur', state: 'Maharashtra' },
  { code: 'NK', nameBn: 'নাসিক রোড', nameHi: 'नासिक रोड', nameEn: 'Nashik Road', cityBn: 'নাসিক', cityHi: 'नासिक', cityEn: 'Nashik', state: 'Maharashtra' },
  { code: 'MMR', nameBn: 'মনমাদ জংশন', nameHi: 'मनमाड जंक्शन', nameEn: 'Manmad Jn', cityBn: 'মনমাদ', cityHi: 'मनमाड', cityEn: 'Manmad', state: 'Maharashtra' },

  // Odisha
  { code: 'PURI', nameBn: 'পুরী', nameHi: 'पुरी', nameEn: 'Puri', cityBn: 'পুরী', cityHi: 'पुरी', cityEn: 'Puri', state: 'Odisha' },
  { code: 'BBS', nameBn: 'ভুবনেশ্বর', nameHi: 'भुवनेश्वर', nameEn: 'Bhubaneswar', cityBn: 'ভুবনেশ্বর', cityHi: 'भुवनेश्वर', cityEn: 'Bhubaneswar', state: 'Odisha' },
  { code: 'CTC', nameBn: 'কটক জংশন', nameHi: 'कटक जंक्शन', nameEn: 'Cuttack Junction', cityBn: 'কটক', cityHi: 'कटक', cityEn: 'Cuttack', state: 'Odisha' },
  { code: 'ROU', nameBn: 'রাউরকেল্লা', nameHi: 'राउरकेला', nameEn: 'Rourkela Jn', cityBn: 'রাউরকেল্লা', cityHi: 'राउरकेला', cityEn: 'Rourkela', state: 'Odisha' },
  { code: 'SBP', nameBn: 'সম্বলপুর', nameHi: 'संबलपुर', nameEn: 'Sambalpur Jn', cityBn: 'সম্বলপুর', cityHi: 'संबलपुर', cityEn: 'Sambalpur', state: 'Odisha' },
  { code: 'BLS', nameBn: 'বালেশ্বর', nameHi: 'बालेश्वर', nameEn: 'Balasore', cityBn: 'বালেশ্বর', cityHi: 'बालेश्वर', cityEn: 'Balasore', state: 'Odisha' },

  // Jharkhand
  { code: 'RNC', nameBn: 'রাঁচি জংশন', nameHi: 'रांची जंक्शन', nameEn: 'Ranchi Junction', cityBn: 'রাঁচি', cityHi: 'रांची', cityEn: 'Ranchi', state: 'Jharkhand' },
  { code: 'DHN', nameBn: 'ধানবাদ জংশন', nameHi: 'धनबाद जंक्शन', nameEn: 'Dhanbad Junction', cityBn: 'ধানবাদ', cityHi: 'धनबाद', cityEn: 'Dhanbad', state: 'Jharkhand' },
  { code: 'TATA', nameBn: 'টাটানগর জংশন', nameHi: 'टाटानगर जंक्शन', nameEn: 'Tatanagar Junction', cityBn: 'জামশেদপুর', cityHi: 'जमशेदपुर', cityEn: 'Jamshedpur', state: 'Jharkhand' },
  { code: 'BKSC', nameBn: 'বোকারো স্টিল সিটি', nameHi: 'बोकारो स्टील सिटी', nameEn: 'Bokaro Steel City', cityBn: 'বোকারো', cityHi: 'बोकारो', cityEn: 'Bokaro', state: 'Jharkhand' },
  { code: 'JSME', nameBn: 'জসিডি জংশন (দেওঘর)', nameHi: 'जसीडीह जंक्शन', nameEn: 'Jasidih Jn (Deoghar)', cityBn: 'দেওঘর', cityHi: 'देवघर', cityEn: 'Deoghar', state: 'Jharkhand' },

  // Gujarat
  { code: 'ADI', nameBn: 'আহমেদাবাদ জংশন', nameHi: 'अहमदाबाद जंक्शन', nameEn: 'Ahmedabad Junction', cityBn: 'আহমেদাবাদ', cityHi: 'अहमदाबाद', cityEn: 'Ahmedabad', state: 'Gujarat' },
  { code: 'BRC', nameBn: 'ভদোদরা জংশন', nameHi: 'वडोदरा जंक्शन', nameEn: 'Vadodara Junction', cityBn: 'ভদোদরা', cityHi: 'वडोदरा', cityEn: 'Vadodara', state: 'Gujarat' },
  { code: 'ST', nameBn: 'সুরাট', nameHi: 'सूरत', nameEn: 'Surat', cityBn: 'সুরাট', cityHi: 'सूरत', cityEn: 'Surat', state: 'Gujarat' },
  { code: 'RJT', nameBn: 'রাজকোট জংশন', nameHi: 'राजकोट जंक्शन', nameEn: 'Rajkot Jn', cityBn: 'রাজকোট', cityHi: 'राजकोट', cityEn: 'Rajkot', state: 'Gujarat' },
  { code: 'DWK', nameBn: 'দ্বারকা', nameHi: 'द्वारका', nameEn: 'Dwarka', cityBn: 'দ্বারকা', cityHi: 'द्वारका', cityEn: 'Dwarka', state: 'Gujarat' },

  // Rajasthan
  { code: 'JP', nameBn: 'জয়পুর জংশন', nameHi: 'जयपुर जंक्शन', nameEn: 'Jaipur Junction', cityBn: 'জয়পুর', cityHi: 'जयपुर', cityEn: 'Jaipur', state: 'Rajasthan' },
  { code: 'JU', nameBn: 'যোধপুর জংশন', nameHi: 'जोधपुर जंक्शन', nameEn: 'Jodhpur Junction', cityBn: 'যোধপুর', cityHi: 'जोधपुर', cityEn: 'Jodhpur', state: 'Rajasthan' },
  { code: 'AII', nameBn: 'আজমীর জংশন', nameHi: 'अजमेर जंक्शन', nameEn: 'Ajmer Junction', cityBn: 'আজমীর', cityHi: 'अजमेर', cityEn: 'Ajmer', state: 'Rajasthan' },
  { code: 'UDZ', nameBn: 'উদয়পুর সিটি', nameHi: 'उदयपुर सिटी', nameEn: 'Udaipur City', cityBn: 'উদয়পুর', cityHi: 'उदयपुर', cityEn: 'Udaipur', state: 'Rajasthan' },
  { code: 'KOTA', nameBn: 'কোটা জংশন', nameHi: 'कोटा जंक्शन', nameEn: 'Kota Junction', cityBn: 'কোটা', cityHi: 'कोटा', cityEn: 'Kota', state: 'Rajasthan' },
  { code: 'BKN', nameBn: 'বিকানের জংশন', nameHi: 'बीकानेर जंक्शन', nameEn: 'Bikaner Junction', cityBn: 'বিকানের', cityHi: 'बीकानेर', cityEn: 'Bikaner', state: 'Rajasthan' },

  // Karnataka
  { code: 'SBC', nameBn: 'কেএসআর বেঙ্গালুরু', nameHi: 'केएसआर बेंगलुरु', nameEn: 'KSR Bengaluru City', cityBn: 'বেঙ্গালুরু', cityHi: 'बेंगलुरु', cityEn: 'Bengaluru', state: 'Karnataka' },
  { code: 'SMVB', nameBn: 'এসএমভিটি বেঙ্গালুরু', nameHi: 'सर एम. विश्वेश्वरैया बेंगलुरु', nameEn: 'SMVT Bengaluru', cityBn: 'বেঙ্গালুরু', cityHi: 'बेंगलुरु', cityEn: 'Bengaluru', state: 'Karnataka' },
  { code: 'YPR', nameBn: 'যশবন্তপুর জংশন', nameHi: 'यशवंतपुर जंक्शन', nameEn: 'Yesvantpur Junction', cityBn: 'বেঙ্গালুরু', cityHi: 'बेंगलुरु', cityEn: 'Bengaluru', state: 'Karnataka' },
  { code: 'MYS', nameBn: 'মহীশূর জংশন', nameHi: 'मैसूर जंक्शन', nameEn: 'Mysuru Junction', cityBn: 'মহীশূর', cityHi: 'मैसूर', cityEn: 'Mysuru', state: 'Karnataka' },
  { code: 'UBL', nameBn: 'হুবলি জংশন', nameHi: 'हुबली जंक्शन', nameEn: 'SSS Hubballi Jn', cityBn: 'হুবলি', cityHi: 'हुबली', cityEn: 'Hubli', state: 'Karnataka' },
  { code: 'MAQ', nameBn: 'ম্যাঙ্গালোর সেন্ট্রাল', nameHi: 'मंगलौर सेंट्रल', nameEn: 'Mangaluru Central', cityBn: 'ম্যাঙ্গালোর', cityHi: 'मंगलौर', cityEn: 'Mangalore', state: 'Karnataka' },

  // Tamil Nadu
  { code: 'MAS', nameBn: 'চেন্নাই সেন্ট্রাল', nameHi: 'चेन्नई सेंट्रल', nameEn: 'Chennai Central', cityBn: 'চেন্নাই', cityHi: 'चेन्नई', cityEn: 'Chennai', state: 'Tamil Nadu' },
  { code: 'MS', nameBn: 'চেন্নাই এগমোর', nameHi: 'चेन्नई एग्मोर', nameEn: 'Chennai Egmore', cityBn: 'চেন্নাই', cityHi: 'चेन्नई', cityEn: 'Chennai', state: 'Tamil Nadu' },
  { code: 'CBE', nameBn: 'কোয়েম্বাটোর জংশন', nameHi: 'कोयंबटूर जंक्शन', nameEn: 'Coimbatore Jn', cityBn: 'কোয়েম্বাটোর', cityHi: 'कोयंबटूर', cityEn: 'Coimbatore', state: 'Tamil Nadu' },
  { code: 'MDU', nameBn: 'মাদুরাই জংশন', nameHi: 'मदुरै जंक्शन', nameEn: 'Madurai Junction', cityBn: 'মাদুরাই', cityHi: 'मदुरै', cityEn: 'Madurai', state: 'Tamil Nadu' },
  { code: 'CAPE', nameBn: 'কন্যাকুমারী', nameHi: 'कन्याकुमारी', nameEn: 'Kanyakumari', cityBn: 'কন্যাকুমারী', cityHi: 'कन्याकुमारी', cityEn: 'Kanyakumari', state: 'Tamil Nadu' },
  { code: 'RMM', nameBn: 'রামেশ্বরম', nameHi: 'रामेश्वरम', nameEn: 'Rameswaram', cityBn: 'রামেশ্বরম', cityHi: 'रामेश्वरम', cityEn: 'Rameswaram', state: 'Tamil Nadu' },

  // Telangana & Andhra Pradesh
  { code: 'SC', nameBn: 'সেকেন্দ্রাবাদ জংশন', nameHi: 'सिकंदराबाद जंक्शन', nameEn: 'Secunderabad Junction', cityBn: 'হায়দ্রাবাদ', cityHi: 'हैदराबाद', cityEn: 'Hyderabad', state: 'Telangana' },
  { code: 'HYB', nameBn: 'হায়দ্রাবাদ ডেকান', nameHi: 'हैदराबाद डेक्कन', nameEn: 'Hyderabad Deccan', cityBn: 'হায়দ্রাবাদ', cityHi: 'हैदराबाद', cityEn: 'Hyderabad', state: 'Telangana' },
  { code: 'BZA', nameBn: 'বিজয়ওয়াড়া জংশন', nameHi: 'विजयवाड़ा जंक्शन', nameEn: 'Vijayawada Junction', cityBn: 'বিজয়ওয়াড়া', cityHi: 'विजयवाड़ा', cityEn: 'Vijayawada', state: 'Andhra Pradesh' },
  { code: 'VSKP', nameBn: 'বিশাখাপত্তনম জংশন', nameHi: 'विशाखापट्टनम जंक्शन', nameEn: 'Visakhapatnam Junction', cityBn: 'বিশাখাপত্তনম', cityHi: 'विशाखापट्टनम', cityEn: 'Visakhapatnam', state: 'Andhra Pradesh' },
  { code: 'TPTY', nameBn: 'তিরুপতি', nameHi: 'तिरुपति', nameEn: 'Tirupati', cityBn: 'তিরুপতি', cityHi: 'तिरुपति', cityEn: 'Tirupati', state: 'Andhra Pradesh' },

  // Kerala
  { code: 'TVC', nameBn: 'তিরুবনন্তপুরম সেন্ট্রাল', nameHi: 'तिरुवनंतपुरम सेंट्रल', nameEn: 'Thiruvananthapuram Central', cityBn: 'ত্রিবান্দ্রম', cityHi: 'त्रिवेंद्रम', cityEn: 'Trivandrum', state: 'Kerala' },
  { code: 'ERS', nameBn: 'এর্নাকুলাম জংশন (কোচি)', nameHi: 'एर्नाकुलम जंक्शन (कोच्चि)', nameEn: 'Ernakulam Jn (Kochi)', cityBn: 'কোচি', cityHi: 'कोच्चि', cityEn: 'Kochi', state: 'Kerala' },
  { code: 'CLT', nameBn: 'কোজিকোড (কালিকট)', nameHi: 'कोझिकोड (कालीकट)', nameEn: 'Kozhikode (Calicut)', cityBn: 'ক্যালিকাট', cityHi: 'कालीकट', cityEn: 'Calicut', state: 'Kerala' },

  // Madhya Pradesh
  { code: 'BPL', nameBn: 'ভোপাল জংশন', nameHi: 'भोपाल जंक्शन', nameEn: 'Bhopal Junction', cityBn: 'ভোপাল', cityHi: 'भोपाल', cityEn: 'Bhopal', state: 'Madhya Pradesh' },
  { code: 'RKMP', nameBn: 'রানী কমলাপতি (হাবিবগঞ্জ)', nameHi: 'रानी कमलापति', nameEn: 'Rani Kamlapati', cityBn: 'ভোপাল', cityHi: 'भोपाल', cityEn: 'Bhopal', state: 'Madhya Pradesh' },
  { code: 'INDB', nameBn: 'ইন্দোর জংশন', nameHi: 'इंदौर जंक्शन', nameEn: 'Indore Junction', cityBn: 'ইন্দোর', cityHi: 'इंदौर', cityEn: 'Indore', state: 'Madhya Pradesh' },
  { code: 'GWL', nameBn: 'গোয়ালিয়র জংশন', nameHi: 'ग्वालियर जंक्शन', nameEn: 'Gwalior Junction', cityBn: 'গোয়ালিয়র', cityHi: 'ग्वालियर', cityEn: 'Gwalior', state: 'Madhya Pradesh' },
  { code: 'JBP', nameBn: 'জবলপুর জংশন', nameHi: 'जबलपुर जंक्शन', nameEn: 'Jabalpur Junction', cityBn: 'জবলপুর', cityHi: 'जबलपुर', cityEn: 'Jabalpur', state: 'Madhya Pradesh' },
  { code: 'UJN', nameBn: 'উজ্জয়িনী জংশন', nameHi: 'उज्जैन जंक्शन', nameEn: 'Ujjain Junction', cityBn: 'উজ্জয়িনী', cityHi: 'उज्जैन', cityEn: 'Ujjain', state: 'Madhya Pradesh' },

  // Punjab, Haryana, Himachal, J&K
  { code: 'ASR', nameBn: 'অমৃতসর জংশন', nameHi: 'अमृतसर जंक्शन', nameEn: 'Amritsar Junction', cityBn: 'অমৃতসর', cityHi: 'अमृतसर', cityEn: 'Amritsar', state: 'Punjab' },
  { code: 'LDH', nameBn: 'লুধিয়ানা জংশন', nameHi: 'लुधियाना जंक्शन', nameEn: 'Ludhiana Junction', cityBn: 'লুধিয়ানা', cityHi: 'लुधियाना', cityEn: 'Ludhiana', state: 'Punjab' },
  { code: 'CDG', nameBn: 'চণ্ডীগড় জংশন', nameHi: 'चंडीगढ़ जंक्शन', nameEn: 'Chandigarh Junction', cityBn: 'চণ্ডীগড়', cityHi: 'चंडीगढ़', cityEn: 'Chandigarh', state: 'Chandigarh' },
  { code: 'UMB', nameBn: 'আম্বালা ক্যান্ট', nameHi: 'अंबाला कैंट', nameEn: 'Ambala Cantt', cityBn: 'আম্বালা', cityHi: 'अंबाला', cityEn: 'Ambala', state: 'Haryana' },
  { code: 'JAT', nameBn: 'জম্মু তাওয়াই', nameHi: 'जम्मू तवी', nameEn: 'Jammu Tawi', cityBn: 'জম্মু', cityHi: 'जम्मू', cityEn: 'Jammu', state: 'Jammu and Kashmir' },
  { code: 'SVDK', nameBn: 'শ্রী মাতা বৈষ্ণোদেবী কাটরা', nameHi: 'श्री माता वैष्णो देवी कटरा', nameEn: 'SMVD Katra', cityBn: 'কাটরা', cityHi: 'कटरा', cityEn: 'Katra', state: 'Jammu and Kashmir' },
  { code: 'HW', nameBn: 'হরিদ্বার', nameHi: 'हरिद्वार', nameEn: 'Haridwar', cityBn: 'হরিদ্বার', cityHi: 'हरिद्वार', cityEn: 'Haridwar', state: 'Uttarakhand' },
  { code: 'DDN', nameBn: 'দেরাদুন', nameHi: 'देहरादून', nameEn: 'Dehradun', cityBn: 'দেরাদুন', cityHi: 'देहरादून', cityEn: 'Dehradun', state: 'Uttarakhand' },

  // Assam & Northeast
  { code: 'GHY', nameBn: 'গুয়াহাটি', nameHi: 'गुवाहाटी', nameEn: 'Guwahati', cityBn: 'গুয়াহাটি', cityHi: 'गुवाहाटी', cityEn: 'Guwahati', state: 'Assam' },
  { code: 'KYQ', nameBn: 'কামাখ্যা জংশন', nameHi: 'कामाख्या जंक्शन', nameEn: 'Kamakhya Junction', cityBn: 'গুয়াহাটি', cityHi: 'गुवाहाटी', cityEn: 'Guwahati', state: 'Assam' },
  { code: 'DBRG', nameBn: 'ডিব্রুগড়', nameHi: 'डिब्रूगढ़', nameEn: 'Dibrugarh', cityBn: 'ডিব্রুগড়', cityHi: 'डिब्रूगढ़', cityEn: 'Dibrugarh', state: 'Assam' },
  { code: 'AGTL', nameBn: 'আগরতলা', nameHi: 'अगरतला', nameEn: 'Agartala', cityBn: 'আগরতলা', cityHi: 'अगरतला', cityEn: 'Agartala', state: 'Tripura' },

  // Goa & Chhattisgarh
  { code: 'MAO', nameBn: 'মাডগাঁও জংশন (গোয়া)', nameHi: 'मडगाँव जंक्शन (गोवा)', nameEn: 'Madgaon Jn (Goa)', cityBn: 'গোয়া', cityHi: 'गोवा', cityEn: 'Goa', state: 'Goa' },
  { code: 'R', nameBn: 'রায়পুর জংশন', nameHi: 'रायपुर जंक्शन', nameEn: 'Raipur Junction', cityBn: 'রায়পুর', cityHi: 'रायपुर', cityEn: 'Raipur', state: 'Chhattisgarh' },
  { code: 'BSP', nameBn: 'বিলাসপুর জংশন', nameHi: 'बिलासपुर जंक्शन', nameEn: 'Bilaspur Junction', cityBn: 'বিলাসপুর', cityHi: 'बिलासपुर', cityEn: 'Bilaspur', state: 'Chhattisgarh' }
];

export function getStation(code: string): Station {
  const found = STATIONS.find(s => s.code.toLowerCase() === code.toLowerCase());
  if (found) return found;
  return {
    code: code.toUpperCase(),
    nameBn: `${code.toUpperCase()} স্টেশন`,
    nameHi: `${code.toUpperCase()} स्टेशन`,
    nameEn: `${code.toUpperCase()} Station`,
    cityBn: code.toUpperCase(),
    cityHi: code.toUpperCase(),
    cityEn: code.toUpperCase(),
    state: 'India'
  };
}

export function generateTrainsForRoute(srcCode: string, dstCode: string, lang: Language) {
  const s = getStation(srcCode);
  const d = getStation(dstCode);

  const sName = lang === 'bn' ? s.nameBn : lang === 'hi' ? s.nameHi : s.nameEn;
  const dName = lang === 'bn' ? d.nameBn : lang === 'hi' ? d.nameHi : d.nameEn;

  // Specific major corridor checks
  if ((srcCode === 'HWH' || srcCode === 'SDAH') && dstCode === 'NDLS') {
    return [
      {
        number: '12301',
        name: lang === 'bn' ? '১২৩০১ হাওড়া - নতুন দিল্লি রাজধানী এক্সপ্রেস' : lang === 'hi' ? '12301 हावड़ा - नई दिल्ली राजधानी एक्सप्रेस' : '12301 Howrah - New Delhi Rajdhani Express',
        type: 'Rajdhani Express',
        dep: '04:50 PM IST',
        arr: '10:05 AM IST',
        duration: lang === 'bn' ? '১৭ ঘণ্টা ১৫ মিনিট' : lang === 'hi' ? '17 घंटे 15 मिनट' : '17 hrs 15 mins',
        classes: [
          { code: '1A', status: 'WL 8', available: false, fare: 4850 },
          { code: '2A', status: 'WL 28', available: false, fare: 2890 },
          { code: '3A', status: 'WL 74', available: false, fare: 2040 }
        ]
      },
      {
        number: '12313',
        name: lang === 'bn' ? '১২৩১৩ শিয়ালদহ - নতুন দিল্লি রাজধানী এক্সপ্রেস' : lang === 'hi' ? '12313 सियालदह - नई दिल्ली राजधानी एक्सप्रेस' : '12313 Sealdah - New Delhi Rajdhani Express',
        type: 'Rajdhani Express',
        dep: '04:50 PM IST',
        arr: '10:50 AM IST',
        duration: lang === 'bn' ? '১৮ ঘণ্টা ০০ মিনিট' : lang === 'hi' ? '18 घंटे 00 मिनट' : '18 hrs 00 mins',
        classes: [
          { code: '1A', status: 'AVAILABLE 2', available: true, fare: 4850 },
          { code: '2A', status: 'WL 14', available: false, fare: 2890 },
          { code: '3A', status: 'WL 52', available: false, fare: 2040 }
        ]
      },
      {
        number: '12259',
        name: lang === 'bn' ? '১২২৫৯ শিয়ালদহ - বিকানের দুরন্ত এক্সপ্রেস' : lang === 'hi' ? '12259 सियालदह - बीकानेर दुरंतो एक्सप्रेस' : '12259 Sealdah - Bikaner AC Duronto',
        type: 'Duronto Express',
        dep: '05:00 PM IST',
        arr: '11:00 AM IST',
        duration: lang === 'bn' ? '১৮ ঘণ্টা ০০ মিনিট' : lang === 'hi' ? '18 घंटे 00 मिनट' : '18 hrs 00 mins',
        classes: [
          { code: '2A', status: 'WL 19', available: false, fare: 2750 },
          { code: '3A', status: 'WL 64', available: false, fare: 1980 }
        ]
      }
    ];
  }

  if (srcCode === 'HWH' && dstCode === 'PURI') {
    return [
      {
        number: '12837',
        name: lang === 'bn' ? '১২৮৩৭ হাওড়া - পুরী সুপারফাস্ট এক্সপ্রেস' : lang === 'hi' ? '12837 हावड़ा - पुरी सुपरफास्ट एक्सप्रेस' : '12837 Howrah - Puri Superfast Express',
        type: 'Superfast',
        dep: '10:35 PM IST',
        arr: '07:10 AM IST',
        duration: lang === 'bn' ? '০৮ ঘণ্টা ৩৫ মিনিট' : lang === 'hi' ? '08 घंटे 35 मिनट' : '08 hrs 35 mins',
        classes: [
          { code: '1A', status: 'WL 4', available: false, fare: 2100 },
          { code: '2A', status: 'WL 18', available: false, fare: 1250 },
          { code: '3A', status: 'WL 42', available: false, fare: 890 }
        ]
      },
      {
        number: '22895',
        name: lang === 'bn' ? '২২৮৯৫ হাওড়া - পুরী বন্দে ভারত এক্সপ্রেস' : lang === 'hi' ? '22895 हावड़ा - पुरी वंदे भारत एक्सप्रेस' : '22895 Howrah - Puri Vande Bharat',
        type: 'Vande Bharat',
        dep: '06:10 AM IST',
        arr: '12:35 PM IST',
        duration: lang === 'bn' ? '০৬ ঘণ্টা ২৫ মিনিট' : lang === 'hi' ? '06 घंटे 25 मिनट' : '06 hrs 25 mins',
        classes: [
          { code: 'EC', status: 'AVAILABLE 8', available: true, fare: 2420 },
          { code: 'CC', status: 'WL 22', available: false, fare: 1265 }
        ]
      }
    ];
  }

  // General realistic dynamic train generation for ANY station pair across India!
  return [
    {
      number: '12841',
      name: `${sName} ➜ ${dName} ${lang === 'bn' ? 'সুপারফাস্ট এক্সপ্রেস' : lang === 'hi' ? 'सुपरफास्ट एक्सप्रेस' : 'Superfast Express'}`,
      type: 'Superfast',
      dep: '08:15 AM IST',
      arr: '09:30 PM IST',
      duration: lang === 'bn' ? '১৩ ঘণ্টা ১৫ মিনিট' : lang === 'hi' ? '13 घंटे 15 मिनट' : '13 hrs 15 mins',
      classes: [
        { code: '2A', status: 'WL 12', available: false, fare: 1850 },
        { code: '3A', status: 'WL 35', available: false, fare: 1290 },
        { code: 'SL', status: 'AVAILABLE 18', available: true, fare: 485 }
      ]
    },
    {
      number: '22405',
      name: `${sName} ➜ ${dName} ${lang === 'bn' ? 'বন্দে ভারত এক্সপ্রেস' : lang === 'hi' ? 'वंदे भारत एक्सप्रेस' : 'Vande Bharat Express'}`,
      type: 'Vande Bharat',
      dep: '06:00 AM IST',
      arr: '02:30 PM IST',
      duration: lang === 'bn' ? '০৮ ঘণ্টা ৩০ মিনিট' : lang === 'hi' ? '08 घंटे 30 मिनट' : '08 hrs 30 mins',
      classes: [
        { code: 'EC', status: 'AVAILABLE 6', available: true, fare: 2680 },
        { code: 'CC', status: 'WL 14', available: false, fare: 1420 }
      ]
    }
  ];
}

export function generateSplitTicketsForRoute(srcCode: string, dstCode: string, lang: Language) {
  const s = getStation(srcCode);
  const d = getStation(dstCode);

  const sName = lang === 'bn' ? s.nameBn : lang === 'hi' ? s.nameHi : s.nameEn;
  const dName = lang === 'bn' ? d.nameBn : lang === 'hi' ? d.nameHi : d.nameEn;

  // Strategic intermediate hub selection based on corridor
  let intermediateCode = 'DDU';
  if (srcCode === 'HWH' && dstCode === 'PURI') intermediateCode = 'KGP';
  else if (srcCode === 'NDLS' && (dstCode === 'CSMT' || dstCode === 'PUNE')) intermediateCode = 'BPL';
  else if (srcCode === 'HWH' && dstCode === 'MAS') intermediateCode = 'BBS';
  else if (srcCode === 'HWH' && dstCode === 'NJP') intermediateCode = 'MLDT';
  else if (srcCode === intermediateCode || dstCode === intermediateCode) intermediateCode = 'CNB';

  const mid = getStation(intermediateCode);
  const midName = lang === 'bn' ? mid.nameBn : lang === 'hi' ? mid.nameHi : mid.nameEn;

  return [
    {
      id: `split-${srcCode}-${mid.code}-${dstCode}`,
      title: lang === 'bn'
        ? `একই ট্রেনে আসন পরিবর্তন (${midName} জংশন হয়ে)`
        : lang === 'hi'
        ? `एक ही ट्रेन में सीट परिवर्तन (${midName} जंक्शन होकर)`
        : `Same-Train Seat Switch (via ${mid.nameEn} Jn)`,
      tag: lang === 'bn' ? '১০০% নিশ্চিত সিট (CNF)' : lang === 'hi' ? '100% कन्फर्म सीट (CNF)' : '100% Confirmed (CNF)',
      part1: {
        from: `${sName} (${s.code})`,
        to: `${midName} (${mid.code})`,
        cls: '2A',
        seat: lang === 'bn' ? 'কোচ A2, বার্থ 18 (লোয়ার বার্থ)' : lang === 'hi' ? 'कोच A2, बर्थ 18 (लोअर)' : 'Coach A2, Berth 18 (Lower)',
        time: '04:50 PM IST ➜ 12:50 AM IST',
        status: lang === 'bn' ? 'নিশ্চিত (CNF)' : lang === 'hi' ? 'कन्फर्म (CNF)' : 'Confirmed (CNF)',
        fare: 1840
      },
      part2: {
        from: `${midName} (${mid.code})`,
        to: `${dName} (${d.code})`,
        cls: '1A',
        seat: lang === 'bn' ? 'কোচ H1, কেবিন B বার্থ 03' : lang === 'hi' ? 'कोच H1, केबिन B बर्थ 03' : 'Coach H1, Cabin B Berth 03',
        time: '01:00 AM IST ➜ 10:05 AM IST',
        status: lang === 'bn' ? 'নিশ্চিত (CNF)' : lang === 'hi' ? 'कन्फर्म (CNF)' : 'Confirmed (CNF)',
        fare: 2320
      },
      haltMins: 10,
      totalFare: 4160,
      comparison: lang === 'bn'
        ? `সরাসরি টিকিট ওয়েটিংলিস্টে (WL)। ${midName} স্টেশনে শুধু বার্থ বদল করে সম্পূর্ণ যাত্রায় নিশ্চিত সিট বরাদ্দ!`
        : lang === 'hi'
        ? `सीधा टिकट वेटिंग में है। ${midName} पर सिर्फ बर्थ बदलकर पूरी यात्रा में 100% कन्फर्म सीट पाएं!`
        : `Direct tickets are in waitlist (WL). Simply switch berths at ${mid.nameEn} Jn to travel with 100% confirmed seats!`
    }
  ];
}
